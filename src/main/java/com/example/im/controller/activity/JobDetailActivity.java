package com.example.im.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.im.MyApplication;
import com.example.im.model.Model;
import com.example.im.model.bean.JobInfo;
import com.example.im.model.dao.JobDao;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.exceptions.HyphenateException;

import java.io.IOException;

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
        if (((MyApplication)getApplication()).getUserOrHR().equals("HR")){
            //((LinearLayout)findViewById(R.id.ll_job_detail)).removeView(bt_get_hr);
            //实现job招聘的删除
            bt_get_hr.setText("删除");
            bt_get_hr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        JobDao.delete_job(jobInfo);
                        finish();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }else {
            bt_get_hr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(JobDetailActivity.this, ChatActivity.class);

                    //传递参数
                    intent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID, jobInfo.getName_HR());

                    startActivity(intent);
                }
            });

        }
    }
}
