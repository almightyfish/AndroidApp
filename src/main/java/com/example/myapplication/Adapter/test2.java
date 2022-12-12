package com.example.myapplication.Adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DB.InfoDB;
import com.example.myapplication.R;
import com.example.myapplication.entity.Info;


import java.util.ArrayList;
import java.util.List;

public class test2 extends AppCompatActivity {

    private RecyclerView vertical;
    private List<Info> list = new ArrayList<Info>();
    String decs;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.vertical);
        vertical = findViewById(R.id.vertical);
        vertical.setLayoutManager(new LinearLayoutManager(this));
        MyVertical adapter = new MyVertical();
        vertical.setAdapter(adapter);
        Bundle bundle=getIntent().getExtras();
        String request_content = bundle.getString("request_content");
        Log.e("Tag",request_content);
        decs =String.format(request_content);
        initData();
    }

    private void initData(){
        InfoDB Infodb = new InfoDB(this);
        System.out.println("1");
        list = Infodb.findByimg(decs);
        System.out.println(list);
    }

    class MyVertical extends RecyclerView.Adapter<MyVertical.verticalHolder>{

        @NonNull
        @Override
        public verticalHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

            verticalHolder holder = new verticalHolder(LayoutInflater.from(test2.this).inflate(R.layout.vertical_item, parent, false));

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull verticalHolder holder, int position) {
            Info object = list.get(position);
            holder.name.setText(object.getName());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class  verticalHolder extends RecyclerView.ViewHolder{
            TextView name,img,address;


            public verticalHolder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);

            }
        }
    }
}
