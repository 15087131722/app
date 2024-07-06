package com.example.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.im.model.bean.UserInfo;
import com.example.im.model.db.JobMessageDB;
import com.example.im.model.db.UserAccountDB;

public class JobMessageDao {

//    private final JobMessageDB mHelper;
//
//    public JobMessageDao(Context context) {
//        mHelper = new JobMessageDB(context);
//    }
//
//    //添加用户到数据库
//    public void addJobMessage(JobMessageInfo jobMessageInfo){
//        //获取数据库对象
//        SQLiteDatabase db = mHelper.getReadableDatabase();
//
//        //执行添加操作
//        ContentValues values = new ContentValues();
//        values.put(JobMessageTable.COL_HXID,jobMessageInfo.getHxid());
//        values.put(JobMessageTable.COL_JOBID,jobMessageInfo.getName());
//        values.put(JobMessageTable.COL_NICK,jobMessageInfo.getNick());
//        values.put(JobMessageTable.COL_PHOTO,jobMessageInfo.getPhoto());
//
//        db.replace(UserAccountTable.TAB_NAME,null,values);
//
//    }
//
//    //根据环信id获取所有用户信息
//    public UserInfo getAccountByHxid(String hxId){
//        //获取是数据库对象
//        SQLiteDatabase db = mHelper.getReadableDatabase();
//
//        //执行查询语句
//        String sql="select * from " + UserAccountTable.TAB_NAME + " where "+UserAccountTable.COL_HXID +"=?";
//        Cursor cursor = db.rawQuery(sql, new String[]{hxId});
//
//        UserInfo userInfo = null;
//        if(cursor.moveToNext()){
//            userInfo = new UserInfo();
//
//            //封装对象
//            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
//            userInfo.setName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
//            userInfo.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
//            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));
//        }
//        //关闭资源
//        cursor.close();
//
//        //返回数据
//        return userInfo;
//    }

}
