package com.example.im.model.dao;

import android.util.Log;

import com.example.im.model.bean.JobInfo;
import com.example.im.utils.QiNiuLoad;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobDao {
    public static void add_job(JobInfo jobInfo) throws IOException {
        List<JobInfo> mJobList;
        String jsData = QiNiuLoad.download("jobList");
        mJobList = Arrays.asList(new Gson().fromJson(jsData, JobInfo[].class));

        List<JobInfo> jobList = new ArrayList<>();
        for(JobInfo job : mJobList){
            jobList.add(job);
        }
        jobList.add(jobInfo);

        QiNiuLoad.upload(new Gson().toJson(jobList),"jobList");
    }

    public static void delete_job(JobInfo jobInfo) throws IOException {
        List<JobInfo> mJobList;
        String jsData = QiNiuLoad.download("jobList");
        mJobList = Arrays.asList(new Gson().fromJson(jsData, JobInfo[].class));

        List<JobInfo> jobList = new ArrayList<>();
        for(JobInfo job : mJobList){
            if(jobInfo.equals(job)){
            Log.i("222", job.toString());continue;}
            jobList.add(job);
        }
        QiNiuLoad.upload(new Gson().toJson(jobList),"jobList");
    }

}
