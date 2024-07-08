package com.example.im.controller.fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.R;
import com.example.im.controller.activity.JobDetailActivity;
import com.example.im.controller.activity.MainActivity;
import com.example.im.controller.activity.RegisterActivity_User;
import com.example.im.controller.adapter.JobListAdapter;
import com.example.im.model.bean.JobInfo;

import java.util.List;

//会话列表页面
public class ShowJobListFragment extends Fragment {
    //extends EaseConversationListFragment {
   // View view = LayoutInflater.from(this.getContext()).inflate(R.layout.job_list, null);
    private LinearLayout ll_jobList;
    private LinearLayout ll_job_news;
    private List<JobInfo> mJobList ;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        //View view = View.inflate(getActivity(), R.layout.fragment_about_me, null);
//        //show(view);

        View view = View.inflate(getActivity(), R.layout.job_list, null);
        mJobList = new JobListAdapter().getTestJobList();
        ll_jobList = view.findViewById(R.id.ll_job_list);
        ll_job_news = View.inflate(getActivity(),R.layout.job_item,null).findViewById(R.id.ll_job_news);
        showJobList();
        return view;
    }
    private void showJobList(){
        //ll_jobList.removeAllViews();

        ll_jobList.removeAllViews();
        for (int i = 0; i < mJobList.size(); i++) {
            JobInfo jobInfo = mJobList.get(i);
            View view = LayoutInflater.from(this.getContext()).inflate(R.layout.job_item, null);
            TextView tv_name = view.findViewById(R.id.job_name);
            TextView tv_salary = view.findViewById(R.id.job_salary);
            Button jobbutton = view.findViewById(R.id.job_bt);

//        // 给商品行添加点击事件。点击商品行跳到商品的详情页
        jobbutton.setOnClickListener(v -> {
            //跳转到user注册界面，成功
            /**
             * TODO 跳转页面
             */
            Intent intent = new Intent(getActivity(), JobDetailActivity.class);
            intent.putExtra("jobinfo", jobInfo);

            startActivity(intent);
        });
            tv_name.setText(jobInfo.getJobName());
            tv_salary.setText(jobInfo.getSalary());
            ll_jobList.addView(view);
        }
    }


    // @Override
//    public void initView(Bundle savedInstanceState) {
//
//        //setContentView(R.layout.activity_register_hr);
//
//        super.initView(savedInstanceState);
////
////        //添加搜索布局和标题栏
////        addSearchView();
////
////        //设置默认头像
////        conversationListLayout.setAvatarDefaultSrc(ContextCompat.getDrawable(mContext,R.drawable.toux));
////        //设置头像样式：0为默认，1为圆形，2为方形(设置方形时，需要配合设置avatarRadius，默认的avatarRadius为50dp)
////        conversationListLayout.setAvatarShapeType(RECTANGLE);
////        //设置圆角半径
////        conversationListLayout.setAvatarRadius((int) EaseCommonUtils.dip2px(mContext, 5));
//        View view = LayoutInflater.from(mContext).inflate(R.layout.job_list_container,null);
//        @SuppressLint("MissingInflatedId") TextView hr_name = view.findViewById(R.id.hr_name);
//
//        show();
//
//    }

//    private void show(View views){
//
//
//        //移除
//        HRInfo hra = new HRInfo("123");
//        hra.setHxid("456");
//        hra.setPhoto("789");
//        List<HRInfo> list = new ArrayList<>();
//        list.add(hra);
//        HRInfo hrb = new HRInfo("1234");
//        hrb.setHxid("4567");
//        hrb.setPhoto("78910");
//        for(HRInfo hri : list){
//            View view = LayoutInflater.from(mContext).inflate(R.layout.job_list,null);
//
//            @SuppressLint("MissingInflatedId") TextView hr_name = view.findViewById(R.id.hr_name);
//            @SuppressLint("MissingInflatedId") TextView job_name = view.findViewById(R.id.job_name);
//            @SuppressLint("MissingInflatedId") TextView job_salary = view.findViewById(R.id.job_salary);
//
//            hr_name.setText(hri.getName());
//            job_name.setText(hri.getHxid());
//            job_salary.setText(hri.getPhoto());
//
//            job_salary.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    //尝试
//                    //跳转到HR页面
//                    Intent intent = new Intent(getActivity(), RegisterActivity_HR.class);
//
//                    startActivity(intent);
//
//                    //finish();
//                }
//            });
//
//
//            llRoot.addView(view);
//
//        }
//
//    }

//    private void addSearchView() {
//        //添加搜索会话布局
//        View searchView = LayoutInflater.from(mContext).inflate(R.layout.contract_search, null);
//        llRoot.addView(searchView,0);
//
//        //获取控件
//        EaseTitleBar titleBar=findViewById(R.id.fragment_title_bar);
//
//        //设置标题
//        titleBar.setTitle("会话列表");
//
//        //添加右侧图标
//        titleBar.setRightImageResource(R.drawable.em_contact_menu_add);
//    }

    /**
     * 逻辑：点击第position个条目触发该条目的监听器
     *
     * @param view
     * @param position 该参数是条目的位置，从上往下，从零开始计数
     */

    //设置条目的点击事件
//    @Override
//    public void onItemClick(View view, int position) {
//        super.onItemClick(view, position);
//
//        Intent intent = new Intent(getActivity(), ChatActivity.class);
//
//        //获取到该条目的所有数据
//        EaseConversationInfo conversationInfo = conversationListLayout.getItem(position);
//        //拿到该条目的会话信息
//        EMConversation conversation = (EMConversation) conversationInfo.getInfo();
//
//        //传递参数   会话信息id =hxid
//        intent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID, conversation.conversationId());
//
//        //会话类型,是否是群聊
//        if (conversationInfo.isGroup()) {
//            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
//        }
//
//        startActivity(intent);
//    }
//
//    //刷新消息

}
