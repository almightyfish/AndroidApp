package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 用户注册
 */
public class Register extends AppCompatActivity {

    private Spinner sp_Provinces;
    private Spinner sp_Cities;
    private RadioGroup rdg_Sex;
    private RadioButton rb_Man, rb_Woman;
    private Button btn_Register;
    private EditText edt_UserName, edt_Password, edt_Password2, edt_Phone;
    private boolean isFlag = true;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //用户名及密码
        edt_UserName = this.findViewById(R.id.edt_UserName);
        edt_Password = this.findViewById(R.id.edt_Password);
        edt_Password2 = this.findViewById(R.id.edt_Password2);

        //手机号
        edt_Phone = this.findViewById(R.id.edt_Phone);

        //出生地
        sp_Provinces = this.findViewById(R.id.sp_Provinces);
        sp_Cities = this.findViewById(R.id.sp_Cities);

        //性别选择
        rdg_Sex = this.findViewById(R.id.rdg_Sex);
        rb_Man = this.findViewById(R.id.rb_Man);
        rb_Woman = this.findViewById(R.id.rb_Woman);

        //确认注册按钮
        btn_Register = this.findViewById(R.id.btn_Register);

        //用户名输入框失焦处理
        edt_UserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFlag = true;
                } else {
                    isFlag = true;
                    String username = edt_UserName.getText().toString();
                    if (username.length() < 4) {
                        Toast.makeText(Register.this, "用户名长度必须大于4,请重新输入", Toast.LENGTH_SHORT).show();
                        edt_UserName.setText("");
                    }
                }
            }
        });
        //密码输入框失焦处理
        edt_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFlag=true;
                } else {
                    isFlag = true;
                    String password = edt_Password.getText().toString();
                    if (password.length() < 6 || password.length() > 12) {
                        Toast.makeText(Register.this, "密码长度必须为6-12位,请重新输入", Toast.LENGTH_LONG).show();
                        edt_Password.setText("");
                    }
                }
            }
        });
        //确认密码输入框失焦处理
        edt_Password2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFlag=true;
                } else {
                    isFlag = true;
                    String password = edt_Password.getText().toString();
                    String password2 = edt_Password2.getText().toString();

                    if (!(password.equals(password2))) {
                        Toast.makeText(Register.this, "两次密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
                        edt_Password2.setText("");

                    } else if (password2.length() < 6 || password2.length() > 12) {
                        Toast.makeText(Register.this, "密码长度必须为6-12位", Toast.LENGTH_LONG).show();
                        edt_Password2.setText("");
                    }

                }
            }

        });
        //手机号
        edt_Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFlag=true;
                } else {
                    isFlag = true;
                    String phone = edt_Phone.getText().toString();
                    if (!(phone.length() == 11)) {
                        Toast.makeText(Register.this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                        edt_Phone.setText("");
                    }
                }
            }
        });
        //性别处理
        rdg_Sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_Man:
                        Toast.makeText(Register.this, "男", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rb_Woman:
                        Toast.makeText(Register.this, "女", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        //出生地处理
        ArrayAdapter adapter = ArrayAdapter.createFromResource(Register.this, R.array.provinces, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_Provinces.setAdapter(adapter);
        sp_Provinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override//省市联动
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    ArrayAdapter cityAdapter = ArrayAdapter.createFromResource(Register.this, R.array.yunnan, android.R.layout.simple_spinner_item);
                    sp_Cities.setAdapter(cityAdapter);
                } else if (position == 1) {
                    ArrayAdapter cityAdapter = ArrayAdapter.createFromResource(Register.this, R.array.shanxi, android.R.layout.simple_spinner_item);
                    sp_Cities.setAdapter(cityAdapter);
                } else if (position == 2) {
                    ArrayAdapter cityAdapter = ArrayAdapter.createFromResource(Register.this, R.array.fj, android.R.layout.simple_spinner_item);

                    sp_Cities.setAdapter(cityAdapter);
                } else if (position == 3) {
                    ArrayAdapter cityAdapter = ArrayAdapter.createFromResource(Register.this, R.array.guangxi, android.R.layout.simple_spinner_item);

                    sp_Cities.setAdapter(cityAdapter);
                } else if (position == 4) {
                    ArrayAdapter cityAdapter = ArrayAdapter.createFromResource(Register.this, R.array.guangdong, android.R.layout.simple_spinner_item);

                    sp_Cities.setAdapter(cityAdapter);
                } else if (position == 5) {
                    ArrayAdapter cityAdapter = ArrayAdapter.createFromResource(Register.this, R.array.guizhou, android.R.layout.simple_spinner_item);
                    sp_Cities.setAdapter(cityAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //打开数据库或创建数据库
        SQLiteDatabase database = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
        String createSQL = "create table IF NOT EXISTS user(username text,password text,password2 text,phone text)";
        database.execSQL(createSQL);
        //确认注册处理
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                //获取用户输入信息
                String username = edt_UserName.getText().toString();
                String password = edt_Password.getText().toString();
                String password2 = edt_Password2.getText().toString();
                String phone = edt_Phone.getText().toString();
                //判断用户输入是否为空，若有一个输入框为空，则给出提示信息

                if (username.equals("") || password.equals("") || password2.equals("") || phone.equals("")) {
                    Toast.makeText(Register.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                    isFlag = false;


                }
                //判断用户是否存在，若已有此账号，则不允许重复注册，否则允许注册
                Cursor cursor = database.query("user", new String[]{"username"}, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    if (username.equals(cursor.getString(cursor.getColumnIndex("username")))) {
                        Toast.makeText(Register.this, "该账户已存在", Toast.LENGTH_SHORT).show();
                        isFlag = false;//
                    }
                }

                if(!password.equals(password2)) {

                    Toast.makeText(Register.this, "两次密码不一致", Toast.LENGTH_SHORT).show();

                    //若允许注册，则将用户输入的信息，插入到数据库表（user）中,插入成功跳转到登录界面
                }else if (!(phone.length() == 11)){
                    Toast.makeText(Register.this, "请输入11位手机号！", Toast.LENGTH_SHORT).show();
                }
                else if (isFlag ) {
                    ContentValues values = new ContentValues();
                    //将数据放入values中
                    values.put("username", username);
                    values.put("password", password);
                    values.put("password2", password2);
                    values.put("phone", phone);
                    //用insert()方法将values中的数据插入到user表中
                    database.insert("user", null, values);
                    Toast.makeText(Register.this, "注册成功,请登录！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                    database.close();
                }


            }
        });
    }


}

