package com.example.myapplication.ui.acivity.mine;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;

public class NoticeListAcitivity extends BaseActivity implements NoticeListConstract.View, NoticeListAdaper.NoticeItemClick {


    @BindView(R.id.image_break)
    ImageView imageBreak;
    @BindView(R.id.smar_not)
    SmartRefreshLayout smarNot;
    @BindView(R.id.rv_noticelist)
    RecyclerView rvNoticelist;
    private ArrayList<NoticeListBean.DataBean> noticelist;
    private NoticeListAdaper noticeListAdaper;
    boolean isRefresh;
    int page = 1;
    private List<NoticeListBean.DataBean> data;

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
        smarNot.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                page++;
                ((NoticeListPresenter) mPresenter).getNoticeList(String.valueOf(page));

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smarNot.finishRefresh(300);
                noticelist.clear();
                data.clear();
//                ((NoticeListPresenter) mPresenter).getNoticeList(String.valueOf(page));
//                noticelist.addAll(data);
                noticeListAdaper.notifyDataSetChanged();

                if (noticelist.size()<=0){
                    page=1;
                    ((NoticeListPresenter) mPresenter).getNoticeList(String.valueOf(page));
                    noticelist.addAll(data);
                    noticeListAdaper.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void initData() {
        isRefresh = false;
        ((NoticeListPresenter) mPresenter).getNoticeList(String.valueOf(page));
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
        smarNot.finishLoadMore(500);
        if (bean.getStatus() == 1) {
            data = bean.getData();
            if (data.size() > 0) {
//                noticelist.clear();
                noticelist.addAll(data);
                noticeListAdaper.notifyDataSetChanged();
            }
        }else {if (isRefresh) {
            page--;
            Toast.makeText(context,"没有更多数据",Toast.LENGTH_LONG).show();
            noticeListAdaper.notifyDataSetChanged();
        }}
    }


    @Override
    public void noticeClick(NoticeListBean.DataBean title) {
        if (title != null) {
            String content = title.getContent();
            String title1 = title.getTitle();
            int add_time = title.getAdd_time();
            Intent intent = new Intent();
            intent.setClass(context, NoticeDetailsActivity.class);
            intent.putExtra("contents", content);
            intent.putExtra("title1", title1);
            intent.putExtra("add_time", add_time);
            startActivity(intent);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        page = 1;
    }
}
