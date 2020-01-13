package com.example.myapplication.ui.acivity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.TokenBean;
import com.example.myapplication.bean.UserInfoUpdateBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.presenter.usercenter.UpdateUserInfoPresenter;

import java.nio.file.SecureDirectoryStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetNameActivity extends BaseActivity implements UsercenterConstract.UpdateView {

    @BindView(R.id.txt_save)
    TextView txtSave;
    @BindView(R.id.ed_alter)
    EditText edAlter;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_return)
    ImageView imgReturn;

    String nickname;
    String zw;
    int type;
    String curEditor;
    String returnValue = "";

    @Override
    protected IBasePresenter getPresenter() {
        return new UpdateUserInfoPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_name;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        nickname = getIntent().getStringExtra("nickname");
        zw = getIntent().getStringExtra("zw");
        type = getIntent().getIntExtra("type", 0);

        if (type == 100) {
            txtTitle.setText("姓名");
            if (!TextUtils.isEmpty(nickname)) {
                edAlter.setText(nickname);
            }
        } else if (type == 102) {
            txtTitle.setText("职务");
            if (!TextUtils.isEmpty(zw)) {
                edAlter.setText(zw);
            }
        }
    }

    @OnClick({R.id.layout_save, R.id.img_return})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_save:
                saveName();
//                Intent intent1 = new Intent();
//                intent1.putExtra("value", returnValue);
//                setResult(type, intent1);
//                finish();
                break;
            case R.id.img_return:
                returnPage();
                break;
        }
    }

    private void saveName() {
        curEditor = edAlter.getText().toString();
        if (TextUtils.isEmpty(curEditor)){
            Toast.makeText(context,"姓名不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if (!TextUtils.isEmpty(curEditor)) {
            if (type == 100) {
                if (!curEditor.equals(nickname)) {
                    ((UsercenterConstract.UpdatePresenter) mPresenter).updateUserInfo(curEditor,"","");

                }
            } else if (type == 102) {
                if (!curEditor.equals(zw)) {
                    Map<String,String> map = new HashMap<String, String>();
                    ((UsercenterConstract.UpdatePresenter) mPresenter).updateUserInfo("",curEditor,"");
                }
            }
        }

    }

    @Override
    public void updateUserInfoReturn(UserInfoUpdateBean result) {
        if (result.getCode() == 10000) {
            returnValue = edAlter.getText().toString();
            Toast.makeText(this, "修改信息成功", Toast.LENGTH_SHORT).show();
            returnPage();
        } else {
            Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getTokenReturn(TokenBean result) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            returnPage();
            return false;
        }
        return false;
    }


    private void returnPage(){
        Intent intent = new Intent();
        intent.putExtra("value", returnValue);
        setResult(type, intent);
        finish();
    }
}
