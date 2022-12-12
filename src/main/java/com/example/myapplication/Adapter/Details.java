package com.example.myapplication.Adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DB.InfoDB;
import com.example.myapplication.DB.favorDB;
import com.example.myapplication.R;
import com.example.myapplication.entity.Info;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {
    private RecyclerView vertical;
    private List<Info> list = new ArrayList<Info>();
    String decs;
    favorDB DB;
    String username;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.vertical);
        DB = new favorDB(this);
        vertical = findViewById(R.id.vertical);
        vertical.setLayoutManager(new LinearLayoutManager(this));
        MyVertical adapter = new MyVertical();
        vertical.setAdapter(adapter);
        Bundle bundle=getIntent().getExtras();
        String request_content = bundle.getString("request_content");
        username = bundle.getString("username");
        username = String.format(username);
        Log.e("Tag",request_content);
        decs =String.format(request_content);
        initData();
    }

    private void initData(){
        InfoDB Infodb = new InfoDB(this);
        System.out.println("1");
        list = Infodb.findByfy(decs);
        System.out.println(list);
    }

    class MyVertical extends RecyclerView.Adapter<MyVertical.verticalHolder>{

        @NonNull
        @Override
        public verticalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            verticalHolder holder = new verticalHolder(LayoutInflater.from(Details.this).inflate(R.layout.vertical_item_xiangqing, parent, false));

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull verticalHolder holder, int position) {
            Info object = list.get(position);
            holder.name.setText(object.getName());
            holder.img.setText(object.getImg());
            final long[] mCurTime = {0};
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long mLastTime = mCurTime[0];
                    mCurTime[0] = System.currentTimeMillis();
                    if (mCurTime[0] - mLastTime < 500) {
                        if(DB.findByname(decs)==-1) {
                            DB.delete(decs);
                            Toast.makeText(Details.this, "取消收藏成功", Toast.LENGTH_LONG).show();
                        }
                        else{
                            DB.add(username,decs);
                            Toast.makeText(Details.this, "收藏成功", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
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
                img = itemView.findViewById(R.id.img);
            }
        }
    }
}
