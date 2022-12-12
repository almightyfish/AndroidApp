package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

//import com.example.myapplication.dao.UserDao;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.test1;
import com.example.myapplication.Adapter.test2;

public class Index extends AppCompatActivity implements  View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnKeyListener{

    EditText editText;
    private FrameLayout mFragment;
    private RadioGroup mGroup;
    private RadioButton mR1;
    private RadioButton mR2;
    private RadioButton mR3;
    String username;
    private Button km,l1,mp,lijiang,dali,wenshan,baoshan,yuxi,lincang,puer,chuxiong,zhaotong,dehong,nujiang,honghe,qujing,diqing,xishuangbanna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        km =findViewById(R.id.kunming);
        lijiang =findViewById(R.id.lijiang);
        dali =findViewById(R.id.dali);
        wenshan =findViewById(R.id.wenshan);
        baoshan =findViewById(R.id.baoshan);
        yuxi =findViewById(R.id.yuxi);
        lincang =findViewById(R.id.lincang);
        puer =findViewById(R.id.puer);
        chuxiong =findViewById(R.id.chuxiong);
        zhaotong =findViewById(R.id.zhaotong);
        dehong =findViewById(R.id.dehong);
        nujiang =findViewById(R.id.nujiang);
        honghe =findViewById(R.id.honghe);
        qujing =findViewById(R.id.qujing);
        diqing =findViewById(R.id.diqing);
        xishuangbanna =findViewById(R.id.xishuangbanna);
        Button l1 = (Button)findViewById(R.id.choice1);
        km.setOnClickListener(this);
        lijiang.setOnClickListener(this);
        dali.setOnClickListener(this);
        wenshan.setOnClickListener(this);
        baoshan.setOnClickListener(this);
        yuxi.setOnClickListener(this);
        lincang.setOnClickListener(this);
        puer.setOnClickListener(this);
        chuxiong.setOnClickListener(this);
        zhaotong.setOnClickListener(this);
        dehong.setOnClickListener(this);
        nujiang.setOnClickListener(this);
        honghe.setOnClickListener(this);
        qujing.setOnClickListener(this);
        diqing.setOnClickListener(this);
        xishuangbanna.setOnClickListener(this);
        mp = findViewById(R.id.mapshow);
        editText = findViewById(R.id.editText);
        editText.setOnKeyListener(this);
        mp.setOnClickListener(this);
        initView();
        Bundle bundle=getIntent().getExtras();
        String request_content = bundle.getString("username");
        username =String.format(request_content);
    }

    private void initView() {
        mGroup = (RadioGroup)   findViewById(R.id.ra_group);
        mR1 = (RadioButton) findViewById(R.id.ra_1);
        mR2 = (RadioButton) findViewById(R.id.ra_2);
        mR3 = (RadioButton) findViewById(R.id.ra_3);
        //设置Group监听，也就是下方的RadioButton的状态监听
        mGroup.setOnCheckedChangeListener(this);
        //初始化默认第一个RadioButton为选中状态
        mR1.setChecked(true);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Intent intent;
        Bundle bundle1 = new Bundle();
        //Fragment的简单使用，获取管理者，开启事务，对应使用replace或hind，show方法
        switch (checkedId){
            case R.id.ra_1:
//                intent = new Intent(this,Index.class);
//                startActivity(intent);
                break;
            case R.id.ra_2:
                bundle1.putString("username",username);
                intent = new Intent(this,Transfer.class);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
            case R.id.ra_3:
                bundle1.putString("username",username);
                intent = new Intent(this,my.class);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        Intent intent;
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        switch (v.getId()){
            case R.id.kunming:
                intent = new Intent(this, test1.class);
                bundle.putString("request_content", "kunming");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.lijiang:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "lijiang");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.dali:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "dali");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.wenshan:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "wenshan");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.baoshan:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "baoshan");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.yuxi:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "yuxi");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.lincang:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "lincang");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.puer:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "puer");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.chuxiong:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "chuxiong");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.zhaotong:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "zhaotong");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.dehong:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "dehong");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.qujing:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "qujing");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.diqing:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "diqing");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.xishuangbanna:
                intent = new Intent(this,test1.class);
                bundle.putString("request_content", "xishuangbanna");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.mapshow:
                Intent intent1 = new Intent(this,Map.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        Intent intent;
        Bundle bundle;
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            intent = new Intent(this, test2.class);
            bundle = new Bundle();
            String s = editText.getText().toString();
            bundle.putString("request_content", s);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return false;
    }
}

