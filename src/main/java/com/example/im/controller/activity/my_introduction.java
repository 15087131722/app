package com.example.im.controller.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.example.im.controller.activity.model.Experience;
import com.example.im.controller.adapter.ExperienceAdapter;

import java.util.ArrayList;
import java.util.List;

public class my_introduction extends AppCompatActivity {

    private ImageView ivAvatar; // 头像ImageView
    private TextView tvName; // 姓名TextView
    private TextView education; // 职位TextView
    private TextView tvIntroduction; // 自我介绍TextView
    private RecyclerView rvExperience; // 工作经历RecyclerView

    private ExperienceAdapter experienceAdapter; // 工作经历适配器
    private List<Experience> experienceList; // 工作经历数据列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_introduction); // 设置布局文件

        // 初始化视图
        initView();

        // 加载数据
        loadData();
    }

    // 初始化视图
    private void initView() {
        ivAvatar = findViewById(R.id.iv_avatar); // 头像ImageView
        tvName = findViewById(R.id.tv_name); // 姓名TextView
        education = findViewById(R.id.tv_job_title); // 职位TextView
        tvIntroduction = findViewById(R.id.tv_introduction); // 自我介绍TextView
        rvExperience = findViewById(R.id.rv_experience); // 工作经历RecyclerView

        // 初始化 RecyclerView
        rvExperience.setLayoutManager(new LinearLayoutManager(this)); // 设置RecyclerView的布局管理器
        experienceList = new ArrayList<>(); // 初始化工作经历数据列表
        experienceAdapter = new ExperienceAdapter(experienceList); // 创建工作经历适配器
        rvExperience.setAdapter(experienceAdapter); // 设置RecyclerView的适配器
    }

    // 加载数据
    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    private void loadData() {
        // 这里可以从数据库、网络等来源加载数据，下面是示例数据
        tvName.setText("张三"); // 设置姓名
        education.setText("软件工程师"); // 设置职位
        tvIntroduction.setText("我是一个经验丰富的软件工程师，擅长Java和Android开发。"); // 设置自我介绍

        // 示例工作经历数据
        experienceList.add(new Experience("软件开发工程师", "2019-2021: 在某公司担任软件开发工程师，负责Android应用开发。"));
        experienceList.add(new Experience("高级软件工程师", "2021-至今: 在另一公司担任高级软件工程师，负责架构设计和开发。"));
        experienceAdapter.notifyDataSetChanged(); // 通知适配器数据已更新，刷新UI

        // 如果有头像URL，可以使用图片加载库如Glide或Picasso加载图片
        // 示例代码：
        // Glide.with(this).load(avatarUrl).into(ivAvatar);
    }
}
