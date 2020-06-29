package com.example.zhangwu25;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends BaseActivity {

    protected EditText lgNameEdtx,lgPwdEdtx;
    protected Button  lgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
    }

    @Override
    void initView() {
        lgNameEdtx = findViewById(R.id.lg_name_text);
        lgPwdEdtx = findViewById(R.id.lg_pwd_text);
        lgBtn = findViewById(R.id.lg_btn);
    }

    @Override
    void initData() {


        lgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lgName = lgNameEdtx.getText().toString().trim();
                String lgPwd = lgPwdEdtx.getText().toString().trim();

                //判断是否填写信息
                if(lgName == null && lgName.length()<1){
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_LONG).show();
                    return;
                }
                if(lgPwd == null && lgPwd.length()<1){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
                    return;
                }
                CacheUtil.setPermanent("lgName",lgName);
                //跳转界面
                Intent  goMainTent  = new  Intent(LoginActivity.this,MainActivity.class);
                startActivity(goMainTent);
                finish();

            }
        });

    }


}
