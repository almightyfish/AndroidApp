package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 账号密码登录
 */

public class Login extends AppCompatActivity {
    private EditText edt_Username, edt_PassWord;
    private Button btn_Login, btn_Forget, btn_Register;
    boolean isFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_Username = this.findViewById(R.id.edt_UserName);
        edt_PassWord = this.findViewById(R.id.edt_Password);
        btn_Login = this.findViewById(R.id.btn_Login);
        btn_Forget = this.findViewById(R.id.btn_Forget);
        btn_Register = this.findViewById(R.id.btn_Register);
        //用户名
        edt_Username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //当点击用户名输入框时，清空密码框
                    edt_PassWord.setText("");
                } else {
                    String username = edt_Username.getText().toString();
                    if (username.length() < 4) {
                        Toast.makeText(Login.this, "用户名长度必须大于4,请重新输入", Toast.LENGTH_SHORT).show();
                        edt_Username.setText("");

                    }
                }
            }
        });
        //密码框
        edt_PassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    String password = edt_PassWord.getText().toString();
                    if (password.length() < 6 || password.length() > 12) {
                        Toast.makeText(Login.this, "密码长度必须为6-12位,请重新输入", Toast.LENGTH_LONG).show();
                        edt_PassWord.setText("");

                    }
                }
            }
        });
        //打开数据库
        SQLiteDatabase database = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
        //若不存在user表，则创建user表
        String createSQL = "create table IF NOT EXISTS user(username text,password text,password2 text,phone text)";
        database.execSQL(createSQL);//执行创表语句
        //登录事件监听
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                isFlag = false;
                String username = edt_Username.getText().toString();
                String password = edt_PassWord.getText().toString();
                //判断输入是否为空，若为空，给出提示
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(Login.this, "请输入账号或密码！", Toast.LENGTH_SHORT).show();
                    isFlag = true;
                }
                //查询账号密码，若数据库表为空，提示用户注册

                Cursor cursor = database.query("user", new String[]{"username,password"}, null, null, null, null, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(Login.this, "请先注册账号！", Toast.LENGTH_SHORT).show();
                    isFlag=true;
                }
                //若数据库表不为空，查看用户输入的账号密码是否与数据库表中的相匹配，若匹配，登录成功，跳转到主界面
                // 否则无法登录，给出账号密码错误提示
                else {
                    while (cursor.moveToNext()) {

                        if (username.equals(cursor.getString(cursor.getColumnIndex("username"))) && password.equals(cursor.getString(cursor.getColumnIndex("password")))) {
                            Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("username",username);
                            Intent intent = new Intent(Login.this, Index.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            isFlag = true;
                            break;

                        }
                    }
                    if (isFlag == false) {
                        Toast.makeText(Login.this, "账号或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }


            }


        });
        //修改密码监听事件，点击忘记密码，跳转到修改密码界面
        btn_Forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Forget_Password.class);
                startActivity(intent);

            }
        });
        //注册监听事件，点击新用户，跳转到注册界面
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}

