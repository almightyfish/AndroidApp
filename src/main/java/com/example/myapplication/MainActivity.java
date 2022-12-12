package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    TextView id,name,password;
    Button btn,add;
    DBUtils dbUtils;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.btn);
        listView = findViewById(R.id.listview);
        add = findViewById(R.id.add11);
        dbUtils = new DBUtils(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length()<1 || password.getText().toString().length()<1){
                    Toast.makeText(MainActivity.this,"请输入正确的账号和密码",Toast.LENGTH_LONG).show();
                }
                else if(dbUtils.findBynameAndpassword(name.getText().toString(),password.getText().toString())==1){
                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Index.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("request_content", "kunming");
//                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"请输入正确的账号和密码",Toast.LENGTH_LONG).show();
                }

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while(name.getText().toString().length()<1 || password.getText().toString().length()<1){
                    Toast.makeText(MainActivity.this,"请输入正确的账号和密码",Toast.LENGTH_LONG).show();
                }
                dbUtils.add(name.getText().toString(),password.getText().toString());
            }
        });
    }
}