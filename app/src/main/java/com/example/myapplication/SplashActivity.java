package com.example.myapplication;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.VideoView;

import com.example.myapplication.app.Constant;
import com.example.myapplication.app.MyApp;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.VerBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.VersionConstract;
import com.example.myapplication.presenter.versions.VersionPersenter;
import com.example.myapplication.ui.acivity.login.LoginActivity;
import com.example.myapplication.utils.DownLoadUtils;
import com.example.myapplication.utils.SharedPreferencesUtil;
import com.example.myapplication.utils.SystemUtils;

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
    public void getVersionReturn(VerBean result) {
        if(result.getCode() == 10000){
            if(result.getCode() == 1){
                //popwindow提示
                if(!TextUtils.isEmpty(result.getData().getVersion().getApk_url())){
                    final String path = Constant.PATH_APK+"update.apk";
                    DownLoadUtils downLoadUtils = new DownLoadUtils();
                    downLoadUtils.downFile(result.getData().getVersion().getApk_url(), path, new DownLoadUtils.DownLoadListener() {
                        @Override
                        public void loading(int loaded, int total) {
                            if(loaded == total){
                                SystemUtils.installNormal(context,path);
                            }
                        }
                    });
                }
            }else{
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
        }
    }
}
