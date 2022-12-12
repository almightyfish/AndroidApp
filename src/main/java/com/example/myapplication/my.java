package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.Adapter.favorAdapter;
import com.example.myapplication.DB.favorDB;

public class my extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup mGroup;
    private RadioButton mR1;
    private RadioButton mR2;
    private RadioButton mR3;
    String username;
    favorDB DB;

    private TextView textView;
    private TextView user;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        textView = findViewById(R.id.favor);
        user = findViewById(R.id.username);
        DB = new favorDB(this);
        Bundle bundle=getIntent().getExtras();
        username = bundle.getString("username");
        username = String.format(username);;
        System.out.println("username:"+username);
        user.setText(username);
        textView.setOnClickListener(new View.OnClickListener(){
            Bundle bundle1 = new Bundle();
            Intent intent;
            Bundle bundle;
            @Override
            public void onClick(View v) {
                bundle1.putString("username",username);
                intent = new Intent(my.this, favorAdapter.class);
                intent.putExtras(bundle1);
                startActivity(intent);
        }
    });
    }

    private void initView() {
        mGroup = (RadioGroup)   findViewById(R.id.ra_group);
        mR1 = (RadioButton) findViewById(R.id.ra_1);
        mR2 = (RadioButton) findViewById(R.id.ra_2);
        mR3 = (RadioButton) findViewById(R.id.ra_3);
        //设置Group监听，也就是下方的RadioButton的状态监听
        mGroup.setOnCheckedChangeListener(this);
        //初始化默认第一个RadioButton为选中状态
        mR3.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Intent intent;
        Bundle bundle1 = new Bundle();
        switch (checkedId){
            case R.id.ra_1:
                intent = new Intent(this,Index.class);
                bundle1.putString("username",username);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
            case R.id.ra_2:
                bundle1.putString("username",username);
                intent = new Intent(this,Transfer.class);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
            case R.id.ra_3:
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }
}