package com.example.zhangwu25;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.zhangwu25.R;

public class SignNameActivity extends BaseActivity{

    protected EditText signNameEdtx;
    protected TextView saveTx,backTx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_name);
        initView();
        initData();
    }

    @Override
    void initView() {
        signNameEdtx = findViewById(R.id.sign_name_text);
        saveTx = findViewById(R.id.save_tx);
        backTx  = findViewById(R.id.back_tx);
    }

    @Override
    void initData() {
        saveTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String signName = signNameEdtx.getText().toString().trim();
                CacheUtil.setPermanent("signNmae",signName);
                Intent  goMainTent  = new  Intent(SignNameActivity.this,MainActivity.class);
                startActivity(goMainTent);
                finish();
            }
        });
        backTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}