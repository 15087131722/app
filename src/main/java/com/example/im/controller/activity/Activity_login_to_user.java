package com.example.im.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.im.MyApplication;
import com.example.im.model.Model;
import com.example.im.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class Activity_login_to_user extends AppCompatActivity {
    private EditText et_login_name;
    private EditText et_login_pwd;
    private Button bt_login_regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        initView();
        initListener();
    }
    private void initView() {
        et_login_name=(EditText) findViewById(R.id.login_name_user);
        et_login_pwd = (EditText) findViewById(R.id.login_pwd_user);
        bt_login_regist = findViewById(R.id.bt_login_to_user);
    }
    private void initListener() {
        //注册按钮的点击事件处理
        bt_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication)getApplication()).setUserOrHR("USER");
                login();

            }
        });
    }
    private void login() {
        //1 获取输入的用户名和密码
        String loginName= et_login_name.getText().toString();
        String loginPwd = et_login_pwd.getText().toString();

        //2 校验输入的用户名和密码
        if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPwd)) {
            Toast.makeText(Activity_login_to_user.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        //具体的登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {

//                try {
//                    EMClient.getInstance().changeAppkey("1167240702169552#demo");
//                } catch (HyphenateException e) {
//                    throw new RuntimeException(e);
//                }

                //去环信服务器登录
                EMClient.getInstance().login(loginName+"User", loginPwd, new EMCallBack() {
                    //登录成功后的处理
                    @Override
                    public void onSuccess() {
                        //对模型层数据的处理
                        Model.getInstance().loginSuccess(new UserInfo(loginName));

                        //保存用户账号信息到本地数据库
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginName));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //提示登录成功
                                Toast.makeText(Activity_login_to_user.this, "登录成功", Toast.LENGTH_SHORT).show();

                                //跳转到主页面
                                Intent intent = new Intent(Activity_login_to_user.this, MainActivity.class);

                                startActivity(intent);

                                finish();
                            }
                        });

                    }

                    //登录失败后的处理
                    @Override
                    public void onError(int i, String s) {
                        //提示登录失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Activity_login_to_user.this, "登录失败"+s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    //登录过程中的处理
                    @Override
                    public void onProgress(int progress, String status) {

                    }
                });
            }
        });
    }

}
