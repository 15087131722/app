package com.example.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.im.model.bean.HRInfo;
import com.example.im.model.db.HRAccountDB;

public class HRAccountDao {
    private final HRAccountDB mHelper;

    public HRAccountDao(Context context) {
        mHelper = new HRAccountDB(context);
    }

    //添加用户到数据库
    public void addAccount(HRInfo user){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行添加操作
        ContentValues values = new ContentValues();
        values.put(HRAccountTable.COL_HXID,user.getHxid());
        values.put(HRAccountTable.COL_NAME,user.getName());
        values.put(HRAccountTable.COL_NICK,user.getNick());
        values.put(HRAccountTable.COL_PHOTO,user.getPhoto());

        db.replace(HRAccountTable.TAB_NAME,null,values);

    }
    //根据环信id获取所有用户信息
    public HRInfo getAccountByHxid(String hxId){
        //获取是数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行查询语句
        String sql="select * from " + HRAccountTable.TAB_NAME + " where "+HRAccountTable.COL_HXID +"=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});

        HRInfo HRInfo = null;
        if(cursor.moveToNext()){
            HRInfo = new HRInfo();

            //封装对象
            HRInfo.setHxid(cursor.getString(cursor.getColumnIndex(HRAccountTable.COL_HXID)));
            HRInfo.setName(cursor.getString(cursor.getColumnIndex(HRAccountTable.COL_NAME)));
            HRInfo.setNick(cursor.getString(cursor.getColumnIndex(HRAccountTable.COL_NICK)));
            HRInfo.setPhoto(cursor.getString(cursor.getColumnIndex(HRAccountTable.COL_PHOTO)));
        }
        //关闭资源
        cursor.close();

        //返回数据
        return HRInfo;
    }
}
