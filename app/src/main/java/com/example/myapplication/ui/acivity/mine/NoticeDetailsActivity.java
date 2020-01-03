package com.example.myapplication.ui.acivity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.interfaces.IBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeDetailsActivity extends BaseActivity {


    @BindView(R.id.iv_notice_break)
    ImageView ivNoticeBreak;
    @BindView(R.id.txt_not_name)
    TextView txtNotName;
    @BindView(R.id.txt_not_tiem)
    TextView txtNotTiem;
    @BindView(R.id.txt_not_data)
    TextView txtNotData;

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String contents = getIntent().getStringExtra("contents");
        String titles = getIntent().getStringExtra("titles");
        txtNotName.setText("发布人：" + titles);
        txtNotData.setText(contents);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_details;
    }


    @OnClick({R.id.iv_notice_break})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_notice_break:
                finish();
                break;
        }
    }
}
