package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.myapplication.app.Constant;
import com.example.myapplication.app.MyApp;
import com.example.myapplication.ui.acivity.login.LoginActivity;
import com.example.myapplication.utils.SharedPreferencesUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    /**
     * 处理初始化操作
     */
    private void initView(){
        String token = SharedPreferencesUtil.getToken(MyApp.mApp);
        Intent intent = new Intent();
        /*if(TextUtils.isEmpty(token)){
            intent.setClass(this, LoginActivity.class);
        }else{
            Constant.token = token;
            intent.setClass(this,MainActivity.class);
        }*/
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
