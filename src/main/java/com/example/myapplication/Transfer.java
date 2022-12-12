package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Transfer extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup mGroup;
    private RadioButton mR1;
    private RadioButton mR2;
    private RadioButton mR3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        initView();
    }

    private void initView() {
        mGroup = (RadioGroup)   findViewById(R.id.ra_group);
        mR1 = (RadioButton) findViewById(R.id.ra_1);
        mR2 = (RadioButton) findViewById(R.id.ra_2);
        mR3 = (RadioButton) findViewById(R.id.ra_3);
        //设置Group监听，也就是下方的RadioButton的状态监听
        mGroup.setOnCheckedChangeListener(this);
        //初始化默认第一个RadioButton为选中状态
        mR2.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Intent intent;
        //Fragment的简单使用，获取管理者，开启事务，对应使用replace或hind，show方法
        switch (checkedId){
            case R.id.ra_1:
                intent = new Intent(this,Index.class);
                startActivity(intent);
                break;
            case R.id.ra_2:
                break;
            case R.id.ra_3:
                intent = new Intent(this,my.class);
                startActivity(intent);
                break;
        }
    }
}