package com.example.im.controller.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.im.MyApplication;
import com.example.im.model.bean.JobInfo;
import com.example.im.utils.QiNiuLoad;

import java.io.IOException;

//登录页面
public class JobSentActivity extends AppCompatActivity {

    private EditText job_sent_name;
    private EditText job_sent_salary;
    private EditText job_sent_detail;
    private Button bt_job_sent_sent;
    private Button bt_job_sent_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_sent);
        Intent intent = getIntent();
        job_sent_name = findViewById(R.id.job_sent_name);
        job_sent_salary = findViewById(R.id.job_sent_salary);
        job_sent_detail = findViewById(R.id.job_sent_detail);
        bt_job_sent_sent = findViewById(R.id.bt_job_sent_sent);
        bt_job_sent_back = findViewById(R.id.bt_job_sent_back);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);*/


        //初始化监听
        initListener();

    }

    private void initListener() {
        bt_job_sent_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobInfo jobInfo =new JobInfo(((MyApplication)getApplication()).getName(),
                        job_sent_name.getText().toString(),
                        job_sent_detail.getText().toString(),
                        job_sent_salary.getText().toString());
                try {
                    QiNiuLoad.add_job(jobInfo);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        bt_job_sent_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//        //用户按钮的点击事件处理
////        bt_user.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                ((MyApplication) getApplication()).setUserOrHR("USER");
////                setContentView(R.layout.activity_start_choose_login_or_regist);
////                ((TextView) findViewById(R.id.title_start)).setText("User");
////                bt_login = findViewById(R.id.bt_login);
////                bt_regist = findViewById(R.id.bt_regist);
////                et_name = findViewById(R.id.et_name);
////                et_pwd = findViewById(R.id.et_pwd);
////                bt_login.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        login();
////                    }
////                });
////                bt_regist.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        regist();
////                    }
////                });
////            }
////
////        });
//
//
//    }

}
