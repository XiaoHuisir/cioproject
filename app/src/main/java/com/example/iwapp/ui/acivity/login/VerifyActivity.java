package com.example.iwapp.ui.acivity.login;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseActivity;
import com.example.iwapp.interfaces.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VerifyActivity extends BaseActivity implements View.OnFocusChangeListener {

    @BindView(R.id.fan)
    ImageView mFan;
    @BindView(R.id.enclosed)
    TextView mEnclosed;
    @BindView(R.id.ll_verification)
    LinearLayout mLlVerification;
    @BindView(R.id.anew)
    TextView mAnew;
    @BindView(R.id.bt_landing)
    Button mBtLanding;
    @BindView(R.id.edit_first1)
    EditText first1;
    @BindView(R.id.edit_first2)
    EditText first2;
    @BindView(R.id.edit_first)
    EditText first;
    @BindView(R.id.edit_second)
    EditText second;
    @BindView(R.id.edit_third)
    EditText third;
    @BindView(R.id.edit_fourth)
    EditText fourth;
    private List<EditText> mEdits = new ArrayList<EditText>();
    private OnInputFinishListener mInputListener;
    private Context context;


    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verification;
    }

    @Override
    protected void initView() {
        mEdits.add(first);
        mEdits.add(first1);
        mEdits.add(first2);
        mEdits.add(second);
        mEdits.add(third);
        mEdits.add(fourth);
        Intent intent = getIntent();

        String verif = mEdits.toString();
        first.setFocusable(true);
        first.addTextChangedListener(new MyTextWatcher());
        first1.addTextChangedListener(new MyTextWatcher());
        first2.addTextChangedListener(new MyTextWatcher());
        second.addTextChangedListener(new MyTextWatcher());
        third.addTextChangedListener(new MyTextWatcher());
        fourth.addTextChangedListener(new MyTextWatcher());

        first.setOnFocusChangeListener(this);
        first1.setOnFocusChangeListener(this);
        first2.setOnFocusChangeListener(this);
        second.setOnFocusChangeListener(this);
        third.setOnFocusChangeListener(this);
        fourth.setOnFocusChangeListener(this);
    }


  /*  public void setEnabled(boolean enabled) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.setEnabled(enabled);
        }
    }*/

    @Override
    public void onFocusChange(View view, boolean focus) {
        if (focus) {
            focus();
        }
    }

    public void setmInputListener(OnInputFinishListener mInputListener) {
        this.mInputListener = mInputListener;
    }



    public void setContext(Context context) {
        this.context = context;
    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() != 0) {
                focus();
            }
        }
    }

    private void focus() {
        EditText editText;
        //利用for循环找出前面还没被输入字符的EditText
        for (int i = 0; i < mEdits.size(); i++) {
            editText = mEdits.get(i);
            if (editText.getText().length() < 1) {
                editText.requestFocus();
                return;
            } else {
                editText.setCursorVisible(false);
            }
        }
     /*   EditText lastEditText = mEdits.get(mEdits.size() - 1);
        if (lastEditText.getText().length() > 0) {
            //收起软键盘 并不允许编辑 同时将输入的文本提交
            getResponse();
            lastEditText.setCursorVisible(false);
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }*/
    }

    public void getResponse() {
        Log.e("CodeView", "ok");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mEdits.size(); i++) {
            sb.append(mEdits.get(i).getText().toString());
        }
        if (mInputListener != null) {
            mInputListener.onFinish(sb.toString());
        }
    }

    //对外封装一个重置或直接填写验证码的方法
    public void setText(String text) {
        if (text.length() == mEdits.size()) {
            StringBuilder sb = new StringBuilder(text);
            first.setText(sb.substring(0, 1));
            second.setText(sb.substring(1, 2));
            third.setText(sb.substring(2, 3));
            fourth.setText(sb.substring(3, 4));
        } else {
            first.setText("");
            second.setText("");
            third.setText("");
            fourth.setText("");
            first.setCursorVisible(true);
            first.requestFocus();
        }
    }

    //一个监听输入结束的接口，以便外部回调结束后执行的方法
    public interface OnInputFinishListener {
        void onFinish(String code);
    }
    @Override
    public void showError(String errorMsg) {

    }
}