package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DBUtils extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "user";

    public DBUtils(@Nullable Context context) {
        super(context, "Android.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql;
        sql = "create table IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar, password varchar)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public String add(String name, String password){
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("password",password);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME,"name",cv);
        return "success";
    }

    public int findByname(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = String.format("select * from user where name='%s'",name);
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToNext()){
            return 1;
        }
        else{
            return -1;
        }
    }

    public void delete(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{id.toString()});
    }

    public int show(){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = String.format("select * from  user ");
        Cursor c = db.rawQuery(sql,null);
        if(c!=null){
            return 1;
        }
        else{
            return -1;
        }
    }

    public int findBynameAndpassword(String name, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = String.format("select * from  user where name='%s' and password='%s'",name,password);
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToNext()){
            return 1;
        }
        else{
            return -1;
        }
    }
}


