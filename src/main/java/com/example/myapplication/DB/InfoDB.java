package com.example.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.Info;

import java.util.ArrayList;
import java.util.List;

public class InfoDB extends SQLiteOpenHelper {


    public InfoDB(@Nullable Context context) {
        super(context, "Android1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql;
        sql = "create table IF NOT EXISTS info (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar, img varchar,address varchar)";
        sqLiteDatabase.execSQL(sql);
    }

    public List<Info> findByname(String name){
        List<Info>list = new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = String.format("select * from info where address='%s'",name);
        Cursor c = db.rawQuery(sql,null);

        int id = c.getColumnIndex("id");
        int name1 = c.getColumnIndex("name");
        int img = c.getColumnIndex("img");
        int address = c.getColumnIndex("address");

        while(c.moveToNext()) {
            Info info = new Info(c.getInt(id),c.getString(name1),c.getString(img),c.getString(address));
            list.add(info);
        }
        return list;
    }

    public String add(String name, String img, String address){
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("img",img);
        cv.put("address",address);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert("info","name",cv);
        return "success";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void findAll(){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = "select * from info";
        Cursor c = db.rawQuery(sql,null);

        int id = c.getColumnIndex("id");
        int name1 = c.getColumnIndex("name");
        int img = c.getColumnIndex("img");
        int address = c.getColumnIndex("address");

        while(c.moveToNext()) {
            Info info = new Info(c.getInt(id),c.getString(name1),c.getString(img),c.getString(address));
            System.out.println(info);
        }
        db.close();
    }

    public List<Info> findByimg(String img){
        List<Info>list = new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        String s = "%" + img + "%";
        String sql ="select * from info where img like ?";
        Cursor c = db.rawQuery(sql,new String[]{s});

        int id = c.getColumnIndex("id");
        int name1 = c.getColumnIndex("name");
        int img1 = c.getColumnIndex("img");
        int address = c.getColumnIndex("address");

        while(c.moveToNext()) {
            Info info = new Info(c.getInt(id),c.getString(name1),c.getString(img1),c.getString(address));
            list.add(info);
        }
        return list;
    }

    public List<Info> findByfy(String name){
        List<Info>list = new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = String.format("select * from info where name='%s'",name);
        Cursor c = db.rawQuery(sql,null);

        int id = c.getColumnIndex("id");
        int name1 = c.getColumnIndex("name");
        int img1 = c.getColumnIndex("img");
        int address = c.getColumnIndex("address");

        while(c.moveToNext()) {
            Info info = new Info(c.getInt(id),c.getString(name1),c.getString(img1),c.getString(address));
            list.add(info);
        }
        return list;
    }


}
