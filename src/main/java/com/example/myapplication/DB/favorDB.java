package com.example.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.Info;
import com.example.myapplication.entity.favorEntity;

import java.util.ArrayList;
import java.util.List;

public class favorDB extends SQLiteOpenHelper {

    public favorDB(@Nullable Context context) {
        super(context, "favor1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "create table IF NOT EXISTS favor (id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar, name varchar)";
        db.execSQL(sql);
    }

    public List<favorEntity> findByusername(String name){
        List<favorEntity>list = new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = String.format("select * from favor where username='%s'",name);
        Cursor c = db.rawQuery(sql,null);

        int id = c.getColumnIndex("id");
        int name1 = c.getColumnIndex("name");
        while(c.moveToNext()) {
            favorEntity favor = new favorEntity(c.getInt(id),name,c.getString(name1));
            list.add(favor);
        }
        return list;
    }

    public int findByname(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = String.format("select * from favor where name='%s'",name);
        Cursor c = db.rawQuery(sql,null);
        System.out.println(c);
        if(c.moveToNext()){
            return -1;
        }
        else{
            return 1;
            }
    }

    public String add(String username, String name){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("name",name);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert("favor","username",cv);
        return "success";
    }

    public void delete(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = String.format("delete from favor where name='%s'",name);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
