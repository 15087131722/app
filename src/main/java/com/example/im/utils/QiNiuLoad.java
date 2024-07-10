package com.example.im.utils;

import android.util.Log;

import com.example.im.model.bean.JobInfo;
import com.google.gson.Gson;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.Api;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.util.Auth;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class QiNiuLoad {
    static String accessKey = "HflIHhuOR1UplNmvOAJkxgVMSRrY4do9bqM7R_i7";
    static String secretKey = "DY6sHc4Jj1KVVtK6t6ep4mAoejbmlLCUOhFsCdDU";
    static String bucket = "dmu-test-job-list";//空间名
    static String domainOfBucket = "sg904tiwt.hb-bkt.clouddn.com";
    static String key = "jobList";

    public static String upload(String data_json, String key) throws IOException {

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);

        Configuration config = new Configuration.Builder()
                .connectTimeout(90)              // 链接超时。默认90秒
                .useHttps(true)                  // 是否使用https上传域名
                .useConcurrentResumeUpload(true) // 使用并发上传，使用并发上传时，除最后一块大小不定外，其余每个块大小固定为4M，
                .resumeUploadVersion(Configuration.RESUME_UPLOAD_VERSION_V2) // 使用新版分片上传
                .concurrentTaskCount(3)          // 并发上传线程数量为3
                .responseTimeout(90)             // 服务器响应超时。默认90秒
                .build();
        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        UploadManager uploadManager = new UploadManager(config);
        uploadManager.put(data_json.getBytes(StandardCharsets.UTF_8), key, upToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                //res 包含 hash、key 等信息，具体字段取决于上传策略的设置
                if (info.isOK()) {
                    Log.i("qiniu", "Upload Success");
                } else {
                    Log.i("qiniu", "Upload Fail");
                    //如果失败，这里可以把 info 信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);

        //刷新cdn缓存
        DownloadUrl url = new DownloadUrl(domainOfBucket, false, key);
        // 带有效期
        long deadline = System.currentTimeMillis()/1000 + 3600;
        String urlString = url.buildURL(auth, deadline);
        refresh(urlString);

        return "";
    }

    public static String download(String key) throws IOException {
        String encodedFileName = URLEncoder.encode("jobList", "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);

        DownloadUrl url = new DownloadUrl(domainOfBucket, false, key);
        // 带有效期
        long deadline = System.currentTimeMillis()/1000 + expireInSeconds;
        String urlString = url.buildURL(auth, deadline);

        return get(urlString);
    }
    public static String get(String url1) {
        try {
            URL url = new URL(url1);
            HttpURLConnection Connection = (HttpURLConnection) url.openConnection();
            Connection.setRequestMethod("GET");
            Connection.setConnectTimeout(3000);
            Connection.setReadTimeout(3000);
            int responseCode = Connection.getResponseCode();
            if (responseCode == Connection.HTTP_OK) {
                InputStream inputStream = Connection.getInputStream();
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[4096];
                int length = 0;
                while ((length = inputStream.read(bytes)) != -1) {
                    arrayOutputStream.write(bytes, 0, length);
                    arrayOutputStream.flush();//强制释放缓冲区
                }
                String s = arrayOutputStream.toString();
                return s;
            } else {
                return "-1";
            }
        } catch (Exception e) {
            return "-1";
        }
    }

    public static void add_job(JobInfo jobInfo) throws IOException {
        List<JobInfo> mJobList = new ArrayList<>();
        String jsData = QiNiuLoad.download("jobList");
        Log.i("222", jsData);
        mJobList = Arrays.asList(new Gson().fromJson(jsData, JobInfo[].class));

        List<JobInfo> jobList = new ArrayList<>();
        for(JobInfo job : mJobList){
            jobList.add(job);
        }
        jobList.add(jobInfo);

        for(JobInfo job : jobList){
            Log.i("222", job.toString());
        }
        Log.i("222", jobInfo.toString());
//        mJobList = new ArrayList<>();
//        mJobList.add(new JobInfo("1", "1_name", "1_detail", "10"));
//        mJobList.add(new JobInfo("2", "2_name", "2_detail", "20"));
//        mJobList.add(new JobInfo("3", "3_name", "3_detail", "30"));
//        mJobList.add(new JobInfo("4", "4_name", "4_detail", "40"));
//        mJobList.add(new JobInfo("5", "5_name", "5_detail", "50"));
       //mJobList.add(jobInfo);
        QiNiuLoad.upload(new Gson().toJson(jobList),"jobList");
    }

    public static void refresh(String url) throws QiniuException {
        String [] urls = {url};
        Auth auth = Auth.create(accessKey, secretKey);
        CdnManager c = new CdnManager(auth);
        CdnResult.RefreshResult response = c.refreshUrls(urls);
    }
}
