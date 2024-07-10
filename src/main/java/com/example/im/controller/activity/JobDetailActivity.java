package com.example.im.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.im.model.bean.JobInfo;

public class JobDetailActivity extends AppCompatActivity {
    private TextView tv_salary; // 声明一个文本视图对象
    private TextView tv_detail; // 声明一个文本视图对象
    private Button bt_go_back; // 回主界面
    private Button bt_get_hr; // 获取HR联系方式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_detail);
        Intent intent = getIntent();
        JobInfo jobInfo = (JobInfo) intent.getSerializableExtra("jobinfo");
        tv_salary = findViewById(R.id.tv_job_salary);
        tv_detail = findViewById(R.id.tv_job_detail);
        bt_go_back = findViewById(R.id.bt_go_back);
        bt_get_hr = findViewById(R.id.bt_get_HR);

        tv_salary.setText(jobInfo.getSalary());
        tv_detail.setText(jobInfo.getDetail());
        bt_go_back.setOnClickListener(v -> finish());
        bt_get_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
