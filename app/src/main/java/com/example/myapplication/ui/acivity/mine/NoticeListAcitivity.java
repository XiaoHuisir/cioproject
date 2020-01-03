package com.example.myapplication.ui.acivity.mine;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.NoticeListBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.adaper.NoticeListAdaper;
import com.example.myapplication.interfaces.contract.NoticeListConstract;
import com.example.myapplication.presenter.noticelist.NoticeListPresenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;

public class NoticeListAcitivity extends BaseActivity implements NoticeListConstract.View, NoticeListAdaper.NoticeItemClick {


    @BindView(R.id.image_break)
    ImageView imageBreak;
    @BindView(R.id.rv_noticelist)
    RecyclerView rvNoticelist;
    private ArrayList<NoticeListBean.DataBean> noticelist;
    private NoticeListAdaper noticeListAdaper;

    @Override
    protected IBasePresenter getPresenter() {
        return new NoticeListPresenter();
    }

    @Override
    protected void initView() {
        noticelist = new ArrayList<>();
        rvNoticelist.setLayoutManager(new LinearLayoutManager(context));
        noticeListAdaper = new NoticeListAdaper(noticelist, this);
        rvNoticelist.setAdapter(noticeListAdaper);
    }

    @Override
    protected void initData() {
        ((NoticeListPresenter) mPresenter).getNoticeList("1");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_list_acitivity;
    }


    @OnClick({R.id.image_break})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_break:
                finish();
                break;
        }
    }

    @Override
    public void getNoticeListReturn(NoticeListBean bean) {
        if (bean.getStatus() == 1) {
            List<NoticeListBean.DataBean> data = bean.getData();
            noticelist.clear();
            noticelist.addAll(data);
            noticeListAdaper.notifyDataSetChanged();
        }
    }


    @Override
    public void noticeClick(String conten,String  addtime,String title) {
        if (conten != null|addtime!=null|title!=null) {
            Toast.makeText(context, "notice详情id" + conten, Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(context,NoticeDetailsActivity.class);
            intent.putExtra("contents",conten);
            intent.putExtra("add_times",addtime);
            intent.putExtra("titles",title);
            startActivity(intent);
        }
    }
}
