package com.example.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.im.model.bean.HRInfo;
import com.example.im.model.bean.UserInfo;
import com.example.im.model.db.HRAccountDB;

public class HRAccountDao {
    private final HRAccountDB mHelper;

    public HRAccountDao(Context context) {
        mHelper = new HRAccountDB(context);
    }

    //添加用户到数据库
    public void addAccount(UserInfo user){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行添加操作
        ContentValues values = new ContentValues();
        values.put(UserAccountTable.COL_HXID,user.getHxid());
        values.put(UserAccountTable.COL_NAME,user.getName());
        values.put(UserAccountTable.COL_NICK,user.getNick());
        values.put(UserAccountTable.COL_PHOTO,user.getPhoto());

        db.replace(UserAccountTable.TAB_NAME,null,values);

    }
    //根据环信id获取所有用户信息
    public HRInfo getAccountByHxid(String hxId){
        //获取是数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行查询语句
        String sql="select * from " + UserAccountTable.TAB_NAME + " where "+UserAccountTable.COL_HXID +"=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});

        HRInfo HRInfo = null;
        if(cursor.moveToNext()){
            HRInfo = new HRInfo();

            //封装对象
            HRInfo.setHxid(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            HRInfo.setName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            HRInfo.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            HRInfo.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));
        }
        //关闭资源
        cursor.close();

        //返回数据
        return HRInfo;
    }
}
