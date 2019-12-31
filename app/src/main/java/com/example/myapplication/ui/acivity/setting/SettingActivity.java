package com.example.myapplication.ui.acivity.setting;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.ui.acivity.SetNameActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.txt_ture)
    TextView txtture;

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick({R.id.txt_ture})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_ture: //设置名称
                Intent intent = new Intent();
                intent.setClass(context, SetNameActivity.class);
                startActivity(intent);
                break;

        }
    }
}
