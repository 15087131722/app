package com.example.im.controller.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.R;

public class RegisterActivity extends Activity {

    private Button bt_register_hr;
    private Button bt_register_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initListener();
    }

    private void initView() {
        bt_register_hr = findViewById(R.id.bt_register_HR);
        bt_register_user = findViewById(R.id.bt_register_user);
    }

    private void initListener() {
        //HR按钮的点击事件处理
        bt_register_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到HR页面
                Intent intent = new Intent(RegisterActivity.this, RegisterActivity_HR.class);

                startActivity(intent);

                finish();
            }
        });

        //user按钮的点击事件处理
        bt_register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到user页面
                Intent intent = new Intent(RegisterActivity.this, RegisterActivity_User.class);

                startActivity(intent);

                finish();
            }
        });
    }
}