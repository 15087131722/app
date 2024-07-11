package com.example.im.controller.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.example.im.MyApplication;
import com.example.im.controller.activity.model.Experience;
import com.example.im.controller.adapter.ExperienceAdapter;
import com.example.im.model.bean.user_info;
import com.example.im.utils.QiNiuLoad;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class my_introduction extends AppCompatActivity {

    private ImageView ivAvatar; // 头像ImageView
    private EditText etName; // 姓名EditText
    private EditText etJobTitle; // 职位EditText
    private EditText etIntroduction; // 自我介绍EditText
    private EditText etExperience; // 学历EditText
    private RecyclerView rvExperience; // 工作经历RecyclerView
    private Button btnSave; // 保存按钮

    private ExperienceAdapter experienceAdapter; // 工作经历适配器
    private List<Experience> experienceList; // 工作经历数据列表
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = ((MyApplication)getApplication()).getName();
        if (((MyApplication)getApplication()).getUserOrHR().equals("USER")) {
            setContentView(R.layout.user_introduction); // 设置用户布局文件
        } else if (((MyApplication)getApplication()).getUserOrHR().equals("HR")){
//            setContentView(R.layout.hr_introduction); // 设置HR布局文件
        }
        // 初始化视图
        initView();

        // 加载数据
        loadData();
    }

    // 初始化视图
    private void initView() {
        if (((MyApplication)getApplication()).getUserOrHR().equals("USER")){
            ivAvatar = findViewById(R.id.iv_avatar); // 头像ImageView
            etName = findViewById(R.id.et_name); // 姓名EditText
            etJobTitle = findViewById(R.id.et_job_title); // 职位EditText
            etIntroduction = findViewById(R.id.et_introduction); // 自我介绍EditText
            etExperience = findViewById(R.id.et_experience); // 学历EditText
            rvExperience = findViewById(R.id.rv_experience); // 工作经历RecyclerView
            btnSave = findViewById(R.id.btn_save); // 保存按钮

            // 初始化 RecyclerView
            rvExperience.setLayoutManager(new LinearLayoutManager(this)); // 设置RecyclerView的布局管理器
            experienceList = new ArrayList<>(); // 初始化工作经历数据列表
            experienceAdapter = new ExperienceAdapter(experienceList); // 创建工作经历适配器
            rvExperience.setAdapter(experienceAdapter); // 设置RecyclerView的适配器

            // 设置保存按钮的点击事件
            btnSave.setOnClickListener(v -> saveData());
        } else if (((MyApplication)getApplication()).getUserOrHR().equals("HR")){
//            // HR视图初始化
//            etName = findViewById(R.id.hr_et_name); // 姓名EditText
//            etJobTitle = findViewById(R.id.hr_et_job_title); // 职位EditText
//            etIntroduction = findViewById(R.id.hr_et_introduction); // 自我介绍EditText
//            btnSave = findViewById(R.id.hr_btn_save); // 保存按钮
//
//            // 设置保存按钮的点击事件
//            btnSave.setOnClickListener(v -> saveHRData());
        }
    }

    // 加载数据
    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    private void loadData() {
        try {
            String userInfoJson = QiNiuLoad.download(username); // 从七牛云下载 UserInfo 数据
            if (!"-1".equals(userInfoJson)) {
                user_info userInfo = new Gson().fromJson(userInfoJson, user_info.class);

                // 更新界面显示
                etName.setText(userInfo.getName());
                etJobTitle.setText(userInfo.getJobTitle());
                etIntroduction.setText(userInfo.getIntroduction());

                // 如果是用户界面，加载工作经历列表到 RecyclerView 中
                if (((MyApplication)getApplication()).getUserOrHR().equals("USER")) {
                    experienceList.clear();
                    experienceList.addAll(userInfo.getExperienceList());
                    experienceAdapter.notifyDataSetChanged();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 保存用户数据的方法
    private void saveData() {
        String name = etName.getText().toString().trim();
        String jobTitle = etJobTitle.getText().toString().trim();
        String introduction = etIntroduction.getText().toString().trim();

        List<Experience> experiences = experienceAdapter.getExperiences();

        // 创建 UserInfo 对象
        user_info userInfo = new user_info(name, jobTitle, introduction, "", ""); // avatarUrl 可以为空字符串或者不传递，根据需要传递
        userInfo.setExperienceList(experiences); // 设置工作经历列表

        // 将 UserInfo 对象上传至七牛云
        try {
            QiNiuLoad.upload(new Gson().toJson(userInfo), username); // 上传 UserInfo 对象至七牛云，假设使用 "UserInfo" 作为 key
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 保存HR数据的方法
    private void saveHRData() {
        String name = etName.getText().toString().trim();
        String jobTitle = etJobTitle.getText().toString().trim();
        String introduction = etIntroduction.getText().toString().trim();

        // 创建 HRInfo 对象或者使用你的 HR 数据模型
        // Example:
        // HRInfo hrInfo = new HRInfo(name, jobTitle, introduction);

        // 处理保存逻辑，例如：
        // hrDatabase.saveHRData(hrInfo);
    }
}
