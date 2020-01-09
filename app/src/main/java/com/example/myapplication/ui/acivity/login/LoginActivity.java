package com.example.myapplication.ui.acivity.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.app.Constant;
import com.example.myapplication.app.MyApp;
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
        //判断是否有登录过
        String username = SharedPreferencesUtil.getUserName(MyApp.mApp);
        String pw = SharedPreferencesUtil.getPw(MyApp.mApp);
        if (!TextUtils.isEmpty(username)) {
            edPhone.setText(username);
            if (!TextUtils.isEmpty(pw)) {
                edPw.setText(pw);
            }
        }
//        edPhone.setText("18701420943");
//        edPw.setText("18701420943");
//        edPhone.setFocusable(false);
//        edPw.setFocusable(false);

//        edPw.setSelection(edPw.getText().length());
        edPw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//设置密码不可见

    }

    @OnClick({R.id.btn_login, R.id.btn_show, R.id.ed_pw, R.id.ed_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_pw:

//                edPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
//                edPhone.setInputType(EditorInfo.TYPE_CLASS_PHONE);//设置数字键盘显示
                break;
            case R.id.btn_login:
                mobile = edPhone.getText().toString();
                password = edPw.getText().toString();
                closeKeyboard();
                closeKeyboardedPhone();

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
                ((LoginPresenter) mPresenter).login(mobile, password);
                break;
            case R.id.btn_show:

                if (btnShow.isSelected()) {
                    btnShow.setSelected(false);
                    edPw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//设置密码不可见
                    edPw.setSelection(edPw.getText().length());//设置光标的位置到末尾
                } else {
                    btnShow.setSelected(true);
                    edPw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置密码可见                }
                    edPw.setSelection(edPw.getText().length());//设置光标的位置到末尾
                    break;
                }


                edPw.setSelection(edPw.getText().length());//设置光标的位置到末尾

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
            finish();
        } else {
            Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    //关闭指定文本输入框的软键盘
    private void closeKeyboard() {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edPw.getWindowToken(), 0);
    }

    private void closeKeyboardedPhone() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edPhone.getWindowToken(), 0);
    }

}
