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

import com.example.myapplication.DB.favorDB;
import com.example.myapplication.R;
import com.example.myapplication.entity.favorEntity;

import java.util.ArrayList;
import java.util.List;

public class favorAdapter extends AppCompatActivity {
    private RecyclerView vertical;
    private static List<favorEntity> list = new ArrayList<favorEntity>();
    String decs;

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
        String request_content = bundle.getString("username");
        Log.e("Tag",request_content);
        decs =String.format(request_content);
        initData();
    }

    private void initData(){
        favorDB DB = new favorDB(this);
        list = DB.findByusername(decs);
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
        public verticalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = null;
            verticalHolder holder = new verticalHolder(LayoutInflater.from(favorAdapter.this).inflate(R.layout.vertical_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyVertical.verticalHolder holder, int position) {
            favorEntity object = list.get(position);
            holder.name.setText(object.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    Bundle bundle;
                    Toast.makeText(favorAdapter.this, "我被点击了", Toast.LENGTH_SHORT).show();
                    intent = new Intent(favorAdapter.this, Details.class);
                    bundle = new Bundle();
                    bundle.putString("request_content", object.getName());
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
            }
            @Override
            public void onClick(View v) {
                System.out.println("1");
                // 7.点击事件对象调用方法
                switch (v.getId()){
                    case R.id.btn:
                        Toast.makeText(favorAdapter.this, "yes",Toast.LENGTH_LONG).show();
                    default:
                        break;
                }

            }
        }
    }
}
