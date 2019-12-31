package com.example.myapplication.ui.acivity.setting;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.interfaces.IBasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    private static final int EDIT_NAME = 100;  //修改名字
    private static final int EDIT_AVATAR = 101; //修改头像
    private static final int EDIT_ZW = 102; //职务

    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_zw)
    TextView txtZw;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.txt_zwName)
    TextView txtZwName;

    String avatar;
    String nickname;
    String zw;

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        avatar = getIntent().getStringExtra("avatar");
        nickname = getIntent().getStringExtra("nickname");
        zw = getIntent().getStringExtra("zw");

        if(!TextUtils.isEmpty(avatar)){
            Glide.with(context).load(avatar).into(ivHeader);
        }
        if(!TextUtils.isEmpty(nickname)){
            txtNickname.setText(nickname);
        }
        if(!TextUtils.isEmpty(zw)){
            txtZw.setText(zw);
        }
    }

    @OnClick({R.id.txt_nickname,R.id.txt_zwName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_nickname: //设置名称
                Intent intent = new Intent();
                intent.setClass(context, SetNameActivity.class);
                intent.putExtra("type",EDIT_NAME);
                intent.putExtra("nickname",nickname);
                intent.putExtra("zw",zw);
                startActivityForResult(intent,EDIT_NAME);
                break;
            case R.id.txt_zwName:
                Intent intentZw = new Intent();
                intentZw.setClass(context, SetNameActivity.class);
                intentZw.putExtra("type",EDIT_ZW);
                intentZw.putExtra("nickname",nickname);
                intentZw.putExtra("zw",zw);
                startActivityForResult(intentZw,EDIT_ZW);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == EDIT_NAME){
            String name = data.getStringExtra("value");
            if(!TextUtils.isEmpty(name)){
                txtNickname.setText(name);
            }
        }else if(requestCode == EDIT_ZW){
            String zw = data.getStringExtra("value");
            if(!TextUtils.isEmpty(zw)){
                txtZwName.setText(zw);
            }
        }else if(requestCode == EDIT_AVATAR){

        }

    }
}
