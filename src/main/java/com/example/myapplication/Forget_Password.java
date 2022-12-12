package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
 * 修改密码
 */
public class Forget_Password extends AppCompatActivity {
    private EditText edt_UserName, edt_Phone, edt_NewPassWord, edt_NewPassWord2;
    private Button btn_Confirm;
    private boolean isFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        //获取实例对象
        edt_UserName = this.findViewById(R.id.edt_UserName);
        edt_Phone = this.findViewById(R.id.edt_Phone);
        edt_NewPassWord = this.findViewById(R.id.edt_NewPassWord);
        edt_NewPassWord2 = this.findViewById(R.id.edt_NewPassWord2);
        btn_Confirm = this.findViewById(R.id.btn_Confirm);

        //用户名
        edt_UserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    String username = edt_UserName.getText().toString();
                    if (username.length() < 4) {
                        Toast.makeText(Forget_Password.this, "用户名长度必须大于4,请重新输入", Toast.LENGTH_SHORT).show();
                        edt_UserName.setText("");
                    }
                }
            }
        });

        //手机号，用作用户修改密码验证条件
        edt_Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {


                } else {
                    String phone = edt_Phone.getText().toString();
                    if (!(phone.length() == 11)) {
                        Toast.makeText(Forget_Password.this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                        edt_Phone.setText("");
                    }


                }
            }
        });

        //新密码
        edt_NewPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    String newPassword = edt_NewPassWord.getText().toString();
                    if (newPassword.length() < 6 || newPassword.length() > 12) {
                        Toast.makeText(Forget_Password.this, "密码长度必须为6-12位,请重新输入", Toast.LENGTH_LONG).show();
                        edt_NewPassWord.setText("");
                    }
                }
            }
        });
        //确认新密码
        edt_NewPassWord2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    String newPassword = edt_NewPassWord.getText().toString();
                    String newPassword2 = edt_NewPassWord2.getText().toString();

                    if (!(newPassword.equals(newPassword2))) {
                        Toast.makeText(Forget_Password.this, "两次密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
                        edt_NewPassWord2.setText("");

                    } else if (newPassword2.length() < 6 || newPassword2.length() > 12) {
                        Toast.makeText(Forget_Password.this, "密码长度必须为6-12位", Toast.LENGTH_LONG).show();
                        edt_NewPassWord2.setText("");
                    }

                }
            }
        });
        //确认修改密码，若用户名与此手机号匹配，则可以修改密码，否则不允许修改密码
        SQLiteDatabase database = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {

                String username = edt_UserName.getText().toString();
                String password = edt_NewPassWord.getText().toString();
                String password2 = edt_NewPassWord2.getText().toString();
                String phone = edt_Phone.getText().toString();
                //判断用户输入是否为空，若有一个输入框为空，则给出提示信息
                if (username.equals("") || password.equals("") || password2.equals("") || phone.equals("")) {
                    Toast.makeText(Forget_Password.this, "请输入完整信息", Toast.LENGTH_SHORT).show();

                }else if (!(password.equals(password2))){
                    Toast.makeText(Forget_Password.this, "两次密码不一致", Toast.LENGTH_SHORT).show();

                }else {

                    Cursor cursor = database.query("user", new String[]{"username,phone"}, null, null, null, null, null);
                    while (cursor.moveToNext()) {
                        //判断用户名和手机号是否匹配
                        //首先利用查询语句，查询username和phone，若手机号和用户名匹配，则允许修改密码。
                        if ((username.equals(cursor.getString(cursor.getColumnIndex("username")))
                                && phone.equals(cursor.getString(cursor.getColumnIndex("phone"))))) {
                            ContentValues values = new ContentValues();
                            //将数据放入values中
                            String Username = edt_UserName.getText().toString();
                            values.put("password", password);
                            values.put("password2", password2);
                            //用update()方法将values中的数据更新对应的用户名密码
                            database.update("user", values, "username=?", new String[]{username});
                            Toast.makeText(Forget_Password.this, "修改密码成功,请登录！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Forget_Password.this, Login.class);
                            isFlag = true;
                            startActivity(intent);
                            database.close();
                            break;

                        }
                        isFlag = false;
                    }
                }
                if (isFlag == false) {
                    Toast.makeText(Forget_Password.this, "用户名与手机号不匹配，修改失败", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
