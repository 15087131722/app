package com.example.im.controller.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.R;

import java.io.File;
import java.io.IOException;

public class Login_User_or_HR_Activity extends Activity {

    private Button bt_login_hr;
    private Button bt_login_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_or_hr);
        initView();
        initListener();
    }

    private void initView() {

        bt_login_hr = findViewById(R.id.bt_login_hr);
        bt_login_user = findViewById(R.id.bt_login_user);

    }

    private void initListener() {
        //HR按钮的点击事件处理
        bt_login_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到HR页面
                Intent intent = new Intent(Login_User_or_HR_Activity.this, Activity_login_to_hr.class);

                startActivity(intent);

                finish();
            }
        });

        //user按钮的点击事件处理
        bt_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到user页面
                Intent intent = new Intent(Login_User_or_HR_Activity.this, Activity_login_to_user.class);

                startActivity(intent);

                finish();
            }
        });
    }
}
