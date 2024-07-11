package com.example.im.controller.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.R;
import com.example.im.controller.activity.AboutActivity;
import com.example.im.controller.activity.LoginActivity;
import com.example.im.controller.activity.my_introduction;
import com.example.im.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class SettingFragment extends Fragment {
    private Button bt_setting_logout; // 退出登录按钮
    private TextView userId_view; // 用户ID显示控件
    private TextView nickName_view; // 用户昵称显示控件

    private Button bt_about;

    private ConstraintLayout cl_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 加载布局文件 fragment_about_me.xml
        View view = inflater.inflate(R.layout.fragment_about_me, container, false);

        // 初始化视图控件
        initView(view);

        return view;
    }

    // 初始化视图控件
    private void initView(View view) {
        nickName_view = view.findViewById(R.id.tv_nickName); // 获取用户昵称显示控件
        userId_view = view.findViewById(R.id.tv_userId); // 获取用户ID显示控件
        bt_setting_logout = view.findViewById(R.id.bt_setting_logout); // 获取退出登录按钮
        bt_about = view.findViewById(R.id.bt_about);        // 获取关于按钮

        // 获取 ConstraintLayout
        cl_user = view.findViewById(R.id.cl_user);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 初始化数据
        initData();
    }

    // 初始化数据
    @SuppressLint("SetTextI18n")
    private void initData() {
        // 在 TextView 上显示当前用户账号
        nickName_view.setText("账号：" + EMClient.getInstance().getCurrentUser());

        // 设置关于按钮的点击事件
        bt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到关于页面的逻辑
                Intent intent = new Intent(getActivity(), AboutActivity.class); // 假设 AboutActivity 是你要跳转的页面类
                startActivity(intent);
            }
        });

        // 添加点击事件监听器
        cl_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到目标 Activity
                Intent intent = new Intent(getActivity(), my_introduction.class);
                startActivity(intent);
            }
        });

        // 设置退出登录按钮的点击事件
        bt_setting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在全局线程池中执行退出登录操作
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        // 调用环信 SDK 的退出登录方法
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                // 成功退出登录后的操作
                                // 关闭数据库连接
                                Model.getInstance().getDbManager().close();

                                // 在 UI 线程中更新 UI 显示
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 弹出退出成功的提示
                                        Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();

                                        // 跳转到登录页面
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);

                                        // 结束当前 Activity
                                        getActivity().finish();
                                    }
                                });
                            }

                            @Override
                            public void onError(int code, String error) {
                                // 退出登录失败的操作
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 弹出退出失败的提示
                                        Toast.makeText(getActivity(), "退出失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onProgress(int progress, String status) {
                                // 退出登录过程中的进度回调，可以不做处理
                            }
                        });
                    }
                });
            }
        });
    }
}
