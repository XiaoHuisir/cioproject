package com.example.iwapp;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.VideoView;

import com.example.iwapp.app.Constant;
import com.example.iwapp.app.MyApp;
import com.example.iwapp.base.BaseActivity;
import com.example.iwapp.bean.VerBean;
import com.example.iwapp.interfaces.IBasePresenter;
import com.example.iwapp.interfaces.contract.VersionConstract;
import com.example.iwapp.presenter.versions.VersionPersenter;
import com.example.iwapp.ui.acivity.login.LoginActivity;
import com.example.iwapp.utils.SharedPreferencesUtil;

public class SplashActivity extends BaseActivity implements VersionConstract.View {

    VideoView videoView;

    /**
     * 处理初始化操作
     */
    @Override
    public void initView(){

    }

    @Override
    protected IBasePresenter getPresenter() {
        return new VersionPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        String token = SharedPreferencesUtil.getToken(MyApp.mApp);
        Intent intent = new Intent();
        if(TextUtils.isEmpty(token)){
            intent.setClass(this, LoginActivity.class);
        }else{
            Constant.token = token;
            intent.setClass(this,MainActivity.class);
        }
        startActivity(intent);
        finish();

    }

    @Override
    public void getVersionReturn(VerBean result) {

    }
}
