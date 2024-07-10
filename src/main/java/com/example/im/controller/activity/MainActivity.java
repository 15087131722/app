package com.example.im.controller.activity;


import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.R;
import com.example.im.controller.fragment.ShowJobListFragment;
import com.example.im.controller.fragment.ContractListFragment;
import com.example.im.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg_main;
    private ShowJobListFragment chatFragment;
    private ContractListFragment contractListFragment;
    private SettingFragment settingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        initView();

        initData();

        initListener();
    }

    private void initListener() {
        //RadioGroup的现在事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment=null;
                if (checkedId == R.id.rb_main_chat) {
                    fragment = chatFragment;
                } else if (checkedId == R.id.rb_main_contact) {
                    fragment = contractListFragment;
                } else if (checkedId == R.id.rb_main_setting) {
                    fragment = settingFragment;
                } else {
                    // 如果有其他的 checkedId，可以添加额外的处理逻辑
                }

                //实现fragment 的切换
                switchFragment(fragment);
            }
        });
        //默认会话列表页面
        rg_main.check(R.id.rb_main_chat);
    }
    //实现fragment切换的方法
    private void switchFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }

    private void initData() {
        //创建三个fragment对象
        chatFragment = new ShowJobListFragment();
        contractListFragment = new ContractListFragment();
        settingFragment = new SettingFragment();
    }

    private void initView() {
        rg_main=(RadioGroup)findViewById(R.id.rg_main);
        RadioButton rb_main_chat = findViewById(R.id.rb_main_chat);
        RadioButton rb_main_contact = findViewById(R.id.rb_main_contact);
        RadioButton rb_main_setting = findViewById(R.id.rb_main_setting);

        //定义底部标签图片大小和位置
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable_news = getResources().getDrawable(R.drawable.em_main_tab_conversation);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形,以左上角为原点
        drawable_news.setBounds(5, 13, 70, 80);
        //设置图片在文字的哪个方向
        rb_main_chat.setCompoundDrawables(null, drawable_news, null, null);

        //定义底部标签图片大小和位置
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable_live = getResources().getDrawable(R.drawable.em_main_tab_friends);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_live.setBounds(5, 13, 70, 80);
        //设置图片在文字的哪个方向
        rb_main_contact.setCompoundDrawables(null, drawable_live, null, null);

        //定义底部标签图片大小和位置
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable_tuijian = getResources().getDrawable(R.drawable.em_main_tab_me);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_tuijian.setBounds(5, 13, 70, 80);
        //设置图片在文字的哪个方向
        rb_main_setting.setCompoundDrawables(null, drawable_tuijian, null, null);

        //5 13 70 80
    }
}
