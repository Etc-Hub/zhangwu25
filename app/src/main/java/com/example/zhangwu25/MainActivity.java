package com.example.zhangwu25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zhangwu25.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private String lgName,jsonStr;
    private String signNmae="";
    protected TextView lgNameTx,signNameTx;
    protected ListView mainListView;
    protected MyAdapter myAdapter;
    protected List<DouBanEntity.ListBean> listData =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取登陆界面传递过来的用户名
        lgName = CacheUtil.getPermanent("lgName",String.class,"");
        signNmae = CacheUtil.getPermanent("signNmae",String.class,"");
        jsonStr = CacheUtil.getPermanent("json",String.class,"");
        initView();
        initData();
    }

    @Override
    void initView() {
        lgNameTx = findViewById(R.id.main_name_tx);
        signNameTx = findViewById(R.id.main_sign_name_tx);
        mainListView  =findViewById(R.id.main_list);
        signNameTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent te = new Intent(MainActivity.this,SignNameActivity.class);
                startActivity(te);
            }
        });
    }

    @Override
    void initData() {
        lgNameTx.setText(lgName);
        if(signNmae.length()>0){
            signNameTx.setText(signNmae);
        }else{
            signNameTx.setText("点击设置个性签名");
        }
        JSONObject userJson = JSONObject.parseObject(jsonStr);
        DouBanEntity data = JSON.toJavaObject(userJson,DouBanEntity.class);
        listData.clear();
        listData.addAll(data.getList());
        myAdapter = new MyAdapter(MainActivity.this, R.layout.activity_item, listData, new MyAdapter.OnBackInfo() {
            @Override
            public void onClickItem(int postion) {
                listData.remove(postion);
                myAdapter.notifyDataSetChanged();
            }
        });
        mainListView.setAdapter(myAdapter);
    }
}
