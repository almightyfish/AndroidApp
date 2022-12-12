package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DB.InfoDB;

public class ADD extends AppCompatActivity {
    EditText name;
    EditText img;
    EditText address;
    Button btn;
    InfoDB info;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.add);
        name = findViewById(R.id.infoname);
        img = findViewById(R.id.img);
        address = findViewById(R.id.address);
        btn = findViewById(R.id.ad11);
        info= new InfoDB(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while(name.getText().toString().length()<1 || img.getText().toString().length()<1){
                    Toast.makeText(ADD.this,"请输入正确的账号和密码",Toast.LENGTH_LONG).show();
                }
                info.add(name.getText().toString(),img.getText().toString(),address.getText().toString());
                    Toast.makeText(ADD.this,"success",Toast.LENGTH_LONG).show();
            }
        });
    }
}
