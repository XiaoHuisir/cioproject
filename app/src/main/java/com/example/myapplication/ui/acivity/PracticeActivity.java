package com.example.myapplication.ui.acivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adaper.NotOkcieAdapter;
import com.example.myapplication.adaper.NotcieAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.NotcieRecordBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.NoticeRecordsConstract;
import com.example.myapplication.presenter.mine.NoticeRecordsPresenter;
import com.example.myapplication.ui.acivity.exercises.EexerciseDetailAcivity;
import com.example.myapplication.ui.acivity.exercises.ExercisesResultActivity;
import com.example.myapplication.ui.acivity.video.VideoActivity;
import com.example.myapplication.ui.fragment.CourseFragment;
import com.example.myapplication.utils.NetDownResponse;
import com.example.myapplication.utils.NetRequsetUtil;
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
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.nv)
    NestedScrollView sv;
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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                ((NoticeRecordsPresenter) mPresenter).getNoticeRecord(String.valueOf(page));
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((NoticeRecordsPresenter) mPresenter).getNoticeRecord(String.valueOf(page));
                        if (swipeRefreshLayout != null){
                        swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                },1500);
            }
        });

        sv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {

                }
                if (scrollY < oldScrollY) {

                }

                if (scrollY == 0) {

                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    page = page + 1;


                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("page",  page + "");

                    NetRequsetUtil.getInstance().netRequestPostJson("index/train/evaluation_record",
                            jsonObject.toString(), new NetDownResponse() {
                                @Override
                                public void success(String str) {
                                    NotcieRecordBean notcieRecordBean = JSON.parseObject(str, NotcieRecordBean.class);
                                    //通过
                                    List<NotcieRecordBean.DataBean.PassDataBean> pass_data = notcieRecordBean.getData().getPass_data();
                                    if (pass_data.size() > 0) {
                                        for (NotcieRecordBean.DataBean.PassDataBean pass_datum : pass_data) {
                                            okpasslist.add(pass_datum);
                                        }
                                        notOkcieAdapter.notifyDataSetChanged();
                                    }else {
                                        Toast.makeText(activity, "没有更多了", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void errowithresponse(String str) {
                                }

                                @Override
                                public void erro() {
                                }

                                @Override
                                public void finish() {

                                }
                            });
                }
            }

        });
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
        intent.setClass(context, EexerciseDetailAcivity.class);
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
        intent.setClass(context, EexerciseDetailAcivity.class);
        intent.putExtra("evaluat_id", id);
        startActivity(intent);
    }
}
