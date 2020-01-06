package com.example.myapplication.ui.acivity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.LoginContract;
import com.example.myapplication.presenter.login.LoginPresenter;
import com.example.myapplication.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_pw)
    EditText edPw;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_show)
    Button btnShow;
    private String mobile;
    private String password;
    private int code;


    @Override
    protected IBasePresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
//        edPhone.setFocusable(false);
//        edPw.setFocusable(false);
        edPw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//设置密码不可见

    }

    @OnClick({R.id.btn_login, R.id.btn_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mobile = edPhone.getText().toString();
                password = edPw.getText().toString();
                edPhone.setInputType(EditorInfo.TYPE_CLASS_PHONE);//设置数字键盘显示
//                edPw.setInputType(EditorInfo.TYPE_CLASS_PHONE);//设置数字键盘显示
                Constant.mobiles = mobile;
                Constant.passwords = password;
                if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(password)) {
                    Toast.makeText(context, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                    if (!TextUtils.isEmpty(mobile) && TextUtils.isEmpty(password)) {
                        Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
                    }
                    if (TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)) {
                        Toast.makeText(context, "请输入用户名", Toast.LENGTH_SHORT).show();
                    }

                    return;
                }
//                if (!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)) {
                ((LoginPresenter) mPresenter).login(mobile, password);
//                    if (code!=10000){
//                    Toast.makeText(context, "账号密码不正确", Toast.LENGTH_SHORT).show();}
//                }
                break;
            case R.id.btn_show:

                if (btnShow.isSelected()) {
                    btnShow.setSelected(false);
                    edPw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//设置密码不可见
                } else {
                    btnShow.setSelected(true);
                    edPw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置密码可见                }

                    break;
                }
        }
    }


    @Override
    public void loginReturn(LoginBean result) {
        code = result.getCode();
        if (code == 10000) {
            SharedPreferencesUtil.addUserToken(context, result.getData().getUserToken());
            Constant.token = result.getData().getUserToken();
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(context, "登录成功", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "账号密码不正确", Toast.LENGTH_SHORT).show();
        }
    }
}
