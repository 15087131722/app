package com.example.im.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.R;
import com.example.im.model.bean.JobInfo;
import com.example.im.utils.QiNiuLoad;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobListAdapter extends BaseAdapter {
    private Context mContext; // 声明一个上下文对象
    private List<JobInfo> mJobList; // 声明一个行星信息列表

    public JobListAdapter() {
        mJobList = new ArrayList<>();
    }

    public JobListAdapter(Context Context, List<JobInfo> mJobList) {
        this.mContext = mContext;
        this.mJobList = mJobList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) { // 转换视图为空
            holder = new ViewHolder(); // 创建一个新的视图持有者
            // 根据布局文件item_list.xml生成转换视图对象
            convertView = LayoutInflater.from(mContext).inflate(R.layout.job_list, null);
            holder.job_salary = convertView.findViewById(R.id.job_salary);
            convertView.setTag(holder); // 将视图持有者保存到转换视图当中
        } else { // 转换视图非空
            // 从转换视图中获取之前保存的视图持有者
            holder = (ViewHolder) convertView.getTag();
        }
        JobInfo jobInfo = mJobList.get(position);
        holder.hr_name.setText(jobInfo.getName_HR());
        return convertView;
    }

    public List<JobInfo> getTestJobList() throws IOException {
//        mJobList.add(new JobInfo("1", "1_name", "1_detail", "10"));
//        mJobList.add(new JobInfo("2", "2_name", "2_detail", "20"));
//        mJobList.add(new JobInfo("3", "3_name", "3_detail", "30"));
//        mJobList.add(new JobInfo("4", "4_name", "4_detail", "40"));
//        mJobList.add(new JobInfo("5", "5_name", "5_detail", "50"));
//        String js= new Gson().toJson(mJobList);

//        try {
//            mJobList.add(new JobInfo(js, js,
//                    "10_detail", new QiNiuLoad().upload(js)));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        js= new Gson().toJson(mJobList);
//        mJobList= Arrays.asList(new Gson().fromJson(js, JobInfo[].class));
        String jsData = QiNiuLoad.download("jobList");
        mJobList= Arrays.asList(new Gson().fromJson(jsData, JobInfo[].class));
        return mJobList;
    }

    public void addJobList(JobInfo jobInfo) throws IOException {
        String jsData = QiNiuLoad.download("jobList");
        mJobList= Arrays.asList(new Gson().fromJson(jsData, JobInfo[].class));
        mJobList.add(jobInfo);
        QiNiuLoad.upload(new Gson().toJson(mJobList),"jobList");
    }
    public final class ViewHolder {
        public ImageView hr_icon;
        public TextView hr_name;
        public TextView job_name;
        public TextView job_salary;
        public TextView job_detail;
    }

    @Override
    public int getCount() {
        return mJobList.size();
    }

    @Override
    public Object getItem(int position) {
        return mJobList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}