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
    private RadioGroup rg_main; // 底部导航的 RadioGroup 控件
    private ShowJobListFragment chatFragment; // 对话列表 Fragment
    private ContractListFragment contractListFragment; // 联系人列表 Fragment
    private SettingFragment settingFragment; // 设置 Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 设置当前 Activity 的布局为 activity_main.xml

        // 允许在主线程中访问网络，通常不推荐这样做，仅为演示目的
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        initView(); // 初始化视图控件
        initData(); // 初始化数据
        initListener(); // 初始化事件监听器
    }

    // 初始化事件监听器
    private void initListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                // 根据选择的 RadioButton 设置要显示的 Fragment
                if (checkedId == R.id.rb_main_chat) {
                    fragment = chatFragment;
                } else if (checkedId == R.id.rb_main_contact) {
                    fragment = contractListFragment;
                } else if (checkedId == R.id.rb_main_setting) {
                    fragment = settingFragment;
                } else {
                    // 如果有其他的 checkedId，可以添加额外的处理逻辑
                }

                // 切换显示 Fragment
                switchFragment(fragment);
            }
        });

        // 默认选中会话列表页面
        rg_main.check(R.id.rb_main_chat);
    }

    // 实现 Fragment 切换的方法
    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main, fragment).commit();
    }

    // 初始化数据
    private void initData() {
        // 创建三个 Fragment 对象
        chatFragment = new ShowJobListFragment();
        contractListFragment = new ContractListFragment();
        settingFragment = new SettingFragment();
    }

    // 初始化视图控件
    private void initView() {
        rg_main = findViewById(R.id.rg_main); // 获取底部导航的 RadioGroup 控件
        RadioButton rb_main_chat = findViewById(R.id.rb_main_chat);
        RadioButton rb_main_contact = findViewById(R.id.rb_main_contact);
        RadioButton rb_main_setting = findViewById(R.id.rb_main_setting);

        // 设置底部导航标签的图标和文字位置
        // 对话列表标签
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable drawable_news = getResources().getDrawable(R.drawable.em_main_tab_conversation);
        drawable_news.setBounds(5, 13, 70, 80); // 设置图标位置
        rb_main_chat.setCompoundDrawables(null, drawable_news, null, null); // 设置图标在文字上方显示

        // 联系人列表标签
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable drawable_live = getResources().getDrawable(R.drawable.em_main_tab_friends);
        drawable_live.setBounds(5, 13, 70, 80); // 设置图标位置
        rb_main_contact.setCompoundDrawables(null, drawable_live, null, null); // 设置图标在文字上方显示

        // 设置标签标签
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable drawable_tuijian = getResources().getDrawable(R.drawable.em_main_tab_me);
        drawable_tuijian.setBounds(5, 13, 70, 80); // 设置图标位置
        rb_main_setting.setCompoundDrawables(null, drawable_tuijian, null, null); // 设置图标在文字上方显示
    }
}
