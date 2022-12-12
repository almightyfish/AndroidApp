package com.example.myapplication.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DB.InfoDB;
import com.example.myapplication.R;
import com.example.myapplication.entity.Info;


import java.util.ArrayList;
import java.util.List;

public class test1 extends AppCompatActivity {

    private RecyclerView vertical;
    private static List<Info> list = new ArrayList<Info>();
    String decs;
    String username;
    private OnItemClickListener mOnItemClickListener;

    // 6.设置点击事件对象
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    // 4.定义点击事件回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

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
        String username1 = bundle.getString("username");
        username =String.format(username1);
        initData();
    }

    private void initData(){
        InfoDB Infodb = new InfoDB(this);
        list = Infodb.findByname(decs);
        System.out.println(list);
    }

    class MyVertical extends RecyclerView.Adapter<MyVertical.verticalHolder> implements View.OnClickListener{
        private OnItemClickListener mOnItemClickListener = null;
        private AdapterView.OnItemLongClickListener mOnItemLongClickListener = null;

        // 6.设置点击事件对象
        public void setOnItemClickListener(OnItemClickListener listener) {
            mOnItemClickListener = listener;
        }
        @NonNull
        @Override
        public verticalHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
            View view = null;
            verticalHolder holder = new verticalHolder(LayoutInflater.from(test1.this).inflate(R.layout.vertical_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull verticalHolder holder, int position) {
            Info object = list.get(position);
            holder.name.setText(object.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    Bundle bundle;
                    Toast.makeText(test1.this, "我被点击了", Toast.LENGTH_SHORT).show();
                    intent = new Intent(test1.this, Details.class);
                    bundle = new Bundle();
                    bundle.putString("request_content", object.getName());
                    bundle.putString("username", username);
                    System.out.println(bundle);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        @Override
        public void onClick(View v) {

        }
        class  verticalHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView name,img,address;
            private Button btn;

            public verticalHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                name = itemView.findViewById(R.id.name);
                btn = itemView.findViewById(R.id.btn);
            }
            @Override
            public void onClick(View v) {
                System.out.println("1");
                // 7.点击事件对象调用方法
                switch (v.getId()){
                    case R.id.btn:
                        Toast.makeText(test1.this, "yes",Toast.LENGTH_LONG).show();
                    default:
                        break;
                }

            }
        }
    }
}
