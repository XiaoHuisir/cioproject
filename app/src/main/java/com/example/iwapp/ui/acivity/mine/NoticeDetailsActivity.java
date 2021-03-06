package com.example.iwapp.ui.acivity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseActivity;
import com.example.iwapp.interfaces.IBasePresenter;
import com.example.iwapp.utils.DateUtil;

import butterknife.BindView;
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
        String titles = getIntent().getStringExtra("title1");
        int add_time = getIntent().getIntExtra("add_time", 0);
        txtNotName.setText("标题：" + titles);
        txtNotData.setText(contents);
        String time = DateUtil.stampToDate(String.valueOf(add_time));
        txtNotTiem.setText("收件时间："+time);
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
