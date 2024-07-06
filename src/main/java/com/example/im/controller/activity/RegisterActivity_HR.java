package com.example.im.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.R;
import com.example.im.model.Model;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegisterActivity_HR extends AppCompatActivity {
    private EditText et_login_name;
    private EditText et_login_pwd;
    private Button bt_login_regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_hr);

        initView();
        initListener();
    }
    private void initView() {
        et_login_name=(EditText) findViewById(R.id.enroll_name_HR);
        et_login_pwd = (EditText) findViewById(R.id.enroll_pwd_HR);
        bt_login_regist = findViewById(R.id.bt_enroll_HR);
    }
    private void initListener() {
        //注册按钮的点击事件处理
        bt_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });
    }
    private void regist() {
        //1 获取输入的用户名和密码
        String registName = et_login_name.getText().toString();
        String registPwd = et_login_pwd.getText().toString();

        //2 校验输入的用户名和密码
        if (TextUtils.isEmpty(registName) || TextUtils.isEmpty(registPwd)) {
            Toast.makeText(RegisterActivity_HR.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        //3 去服务器注册
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        EMClient.getInstance().changeAppkey("1162240705154748#demo");
                    } catch (HyphenateException e) {
                        throw new RuntimeException(e);
                    }
                    //去环信服务器注册账号
                    EMClient.getInstance().createAccount(registName,registPwd);

                    //更新页面显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity_HR.this,"注册成功",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity_HR.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity_HR.this,"注册失败"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}