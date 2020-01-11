package com.example.myapplication.ui.acivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adaper.NotOkcieAdapter;
import com.example.myapplication.adaper.NotcieAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.NotcieRecordBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.NoticeRecordsConstract;
import com.example.myapplication.presenter.mine.NoticeRecordsPresenter;
import com.example.myapplication.ui.acivity.exercises.ExercisesResultActivity;
import com.example.myapplication.ui.acivity.video.VideoActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// public NotokItemClick itemokClick;
//    public NottongokItemClick
public class PracticeActivity extends BaseActivity implements NoticeRecordsConstract.View
        , NotcieAdapter.NottongItemClick, NotcieAdapter.NotcielinnotItemClick, NotOkcieAdapter.NotokItemClick
        , NotOkcieAdapter.NottongokItemClick {
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.rv_notcieRecord)
    RecyclerView rvNotcieRecord;
    int page = 1;
    @BindView(R.id.rv_oktcieRecord)
    RecyclerView rvOktcieRecord;
    //    @BindView(R.id.reltoobl)
//    SmartRefreshLayout relToobl;
    private ArrayList<NotcieRecordBean.DataBean.NoPassDataBean> notpasslist;
    private NotcieAdapter notcieAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<NotcieRecordBean.DataBean.PassDataBean> okpasslist;
    private NotOkcieAdapter notOkcieAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int nopass_count;
    private int pass_count;

    @Override
    protected IBasePresenter getPresenter() {
        return new NoticeRecordsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_practice;
    }

    @Override
    protected void initView() {
        //未通过
        notpasslist = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context);
        rvNotcieRecord.setLayoutManager(layoutManager);
        notcieAdapter = new NotcieAdapter(notpasslist);
        notcieAdapter.itemClick = this;
        notcieAdapter.tongitemClick = this;

        rvNotcieRecord.setAdapter(notcieAdapter);
        //通过
        okpasslist = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(context);
        rvOktcieRecord.setLayoutManager(linearLayoutManager);
        notOkcieAdapter = new NotOkcieAdapter(okpasslist);
        notOkcieAdapter.itemokClick=this;
        notOkcieAdapter.tongokitemClick=this;
        rvOktcieRecord.setAdapter(notOkcieAdapter);
    }

    @Override
    public void getNoticeRecordReturn(NotcieRecordBean bean) {

        if (bean.getCode() == 10000) {
            pass_count = bean.getData().getPass_count();
            nopass_count = bean.getData().getNopass_count();
            //未通过
            List<NotcieRecordBean.DataBean.NoPassDataBean> no_pass_data = bean.getData().getNo_pass_data();
            if (no_pass_data.size() > 0) {
                notpasslist.clear();
                notpasslist.addAll(no_pass_data);
                notcieAdapter.notifyDataSetChanged();
            }

            //通过
            List<NotcieRecordBean.DataBean.PassDataBean> pass_data = bean.getData().getPass_data();
            if (pass_data.size() > 0) {
                okpasslist.clear();
                okpasslist.addAll(pass_data);
                notOkcieAdapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick({R.id.iv_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
        }
    }


    @Override
    protected void initData() {
        ((NoticeRecordsPresenter) mPresenter).getNoticeRecord(String.valueOf(page));
    }


    @Override
    public void clicks(String id) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", id);
        startActivity(intent);

    }

    @Override
    public void tongclick(String id) {
        Intent intent = new Intent();
        intent.setClass(context, ExercisesResultActivity.class);
        intent.putExtra("evaluat_id", id);
        startActivity(intent);
    }

    @Override
    public void clickok(String id) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", id);
        startActivity(intent);
    }

    @Override
    public void tongclickok(String id) {
        Intent intent = new Intent();
        intent.setClass(context, ExercisesResultActivity.class);
        intent.putExtra("evaluat_id", id);
        startActivity(intent);
    }
}
