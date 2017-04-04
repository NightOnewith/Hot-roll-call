package com.zijin.hot_roll_call.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zijin.hot_roll_call.common.Users;
import com.zijin.hot_roll_call.database.UserDB;

import java.util.ArrayList;

/**
 * Created by yin on 2017/4/1.
 */
public class UsersDao {
    private final UserDB userDB;
    private ArrayList<Users> usersArrayList;

    public UsersDao(Context context) {
        userDB = new UserDB(context);
    }

    //添加
    public void add(String name, String number, String mac, String time) {
        SQLiteDatabase db = userDB.getReadableDatabase();
        db.execSQL("insert into user_table(name,number,mac,time) values(?,?,?,?)", new Object[]{name, number, mac, time});
        db.close();
    }

    //删除
    public void delete(String mac) {
        SQLiteDatabase db = userDB.getReadableDatabase();
        db.execSQL("delete from user_table where mac=?", new Object[]{mac});
        db.close();
    }

    //修改
    public void update(String mac) {
        SQLiteDatabase db = userDB.getReadableDatabase();
        db.execSQL("update user_table set name=?,number=? where mac=?", new Object[]{mac});
        db.close();
    }

    //查询
    public ArrayList<Users> query() {
        usersArrayList = new ArrayList<>();
        SQLiteDatabase db = userDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name,number,mac,time from user_table", null);
        if (null != cursor && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Users users = new Users();

                String name = cursor.getString(0);
                String number = cursor.getString(1);
                String mac = cursor.getString(2);
                String time = cursor.getString(3);

                users.setName(name);
                users.setNumber(number);
                users.setMac(mac);
                users.setTime(time);
                usersArrayList.add(users);
            }
            cursor.close();
        }
        db.close();
        return usersArrayList;
    }
}
