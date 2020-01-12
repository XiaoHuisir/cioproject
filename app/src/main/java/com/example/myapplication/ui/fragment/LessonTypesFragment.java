package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.adaper.DaAgoAdapter;
import com.example.myapplication.adaper.TodayAdapter;
import com.example.myapplication.adaper.TodayBeansAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.PorfolioConstract;
import com.example.myapplication.presenter.mine.PorfolioPresenter;
import com.example.myapplication.ui.acivity.video.VideoActivity;
import com.example.myapplication.utils.NetDownResponse;
import com.example.myapplication.utils.NetRequsetUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LessonTypesFragment extends BaseFragment implements PorfolioConstract.View, TodayAdapter.TodayItemClick,
        TodayBeansAdapter.TodayBeansItemClick, DaAgoAdapter.TrainItemClick {


    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.recycler_traintypeslesson) //今天
            RecyclerView recyclerTraintypeslesson;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.recyc_seven_daylesson)//七天前
            RecyclerView recycSevenDaylesson;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    @BindView(R.id.recyc_day_agolesson) //更早
            RecyclerView recycDayAgolesson;
    @BindView(R.id.sv)
    NestedScrollView sv;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    int page = 1;

    private ArrayList<ToadayBean.DataBean.SevenDayBean> trainList;
    private TodayAdapter trainAdapter;
    private ArrayList<ToadayBean.DataBean.TodayBean> todayBeans;
    private TodayBeansAdapter todayBeansAdapter;
    private ArrayList<ToadayBean.DataBean.DayAgoBean> dayAgoBeans;
    private DaAgoAdapter daAgoAdapter;

    @Override
    protected IBasePresenter getPresenter() {
        return new PorfolioPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.lessontypes_fragment;
    }

    @Override
    protected void initView() {
        //今天
        todayBeans = new ArrayList<>();
        todayBeansAdapter = new TodayBeansAdapter(todayBeans);
        todayBeansAdapter.itemClick = this;
        recyclerTraintypeslesson.setLayoutManager(new LinearLayoutManager(context));
        recyclerTraintypeslesson.setAdapter(todayBeansAdapter);
        //七天
        trainList = new ArrayList<>();
        trainAdapter = new TodayAdapter(trainList);
        trainAdapter.itemClick = this;
        recycSevenDaylesson.setLayoutManager(new LinearLayoutManager(context));
        recycSevenDaylesson.setAdapter(trainAdapter);
        //更早
        dayAgoBeans = new ArrayList<>();
        daAgoAdapter = new DaAgoAdapter(dayAgoBeans);
        daAgoAdapter.itemClick = this;
        recycDayAgolesson.setLayoutManager(new LinearLayoutManager(context));
        recycDayAgolesson.setAdapter(daAgoAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int page = 1;
                ((PorfolioPresenter) mPresenter).getPorfolio(Constant.CURTYPE, page + "");
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout != null) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 1500);
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
                    jsonObject.put("type", Constant.CURTYPE);
                    jsonObject.put("page", page + "");

                    NetRequsetUtil.getInstance().netRequestPostJson("index/train/study_record",
                            jsonObject.toString(), new NetDownResponse() {
                                @Override
                                public void success(String str) {
                                    ToadayBean toadayBean = JSON.parseObject(str, ToadayBean.class);
                                    List<ToadayBean.DataBean.DayAgoBean> day_ago = toadayBean.getData().getDay_ago();
                                    if (day_ago.size() > 0) {
                                        for (ToadayBean.DataBean.DayAgoBean dayAgo : day_ago) {
                                            dayAgoBeans.add(dayAgo);
                                        }
                                        daAgoAdapter.notifyDataSetChanged();
                                        lin2.setVisibility(View.VISIBLE);
                                    } else {
                                        Toast.makeText(context, "没有更多了", Toast.LENGTH_SHORT).show();
                                        lin2.setVisibility(View.INVISIBLE);
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
    public void getPorfolioReturn(ToadayBean result) {
        if (result.getStatus() == 1) {
            if (result.getData() != null) {
                List<ToadayBean.DataBean.SevenDayBean> seven_day = result.getData().getSeven_day();
                if (seven_day.size() > 0) {
                    trainList.clear();
                    trainList.addAll(seven_day);
                    trainAdapter.notifyDataSetChanged();
                    lin1.setVisibility(View.VISIBLE);
                } else {
                    lin1.setVisibility(View.INVISIBLE);
                }
                List<ToadayBean.DataBean.DayAgoBean> day_ago = result.getData().getDay_ago();
                if (day_ago.size() > 0) {
                    dayAgoBeans.clear();
                    dayAgoBeans.addAll(day_ago);
                    daAgoAdapter.notifyDataSetChanged();
                    lin2.setVisibility(View.VISIBLE);
                } else {
                    lin2.setVisibility(View.INVISIBLE);
                }
                List<ToadayBean.DataBean.TodayBean> today = result.getData().getToday();
                if (today.size() > 0) {
                    todayBeans.clear();
                    todayBeans.addAll(today);
                    todayBeansAdapter.notifyDataSetChanged();
                    lin.setVisibility(View.VISIBLE);
                } else {
                    lin.setVisibility(View.INVISIBLE);
                }
            }

        }
    }


    @Override
    protected void initData() {

        ((PorfolioPresenter) mPresenter).getPorfolio(Constant.CURTYPE, page + "");
    }


    @Override
    public void trainclick(String id) {
        if (id != null) {
            Intent intent = new Intent();
            intent.setClass(context, VideoActivity.class);
            intent.putExtra("curriulum_id", id);
            startActivity(intent);
        } else {
            return;
        }
    }

    @Override
    public void click(String id) {
        if (id != null) {
            Intent intent = new Intent();
            intent.setClass(context, VideoActivity.class);
            intent.putExtra("curriulum_id", id);
            startActivity(intent);
        } else {
            return;
        }
    }

    @Override
    public void todaybeansclick(String id) {
        if (id != null) {
            Intent intent = new Intent();
            intent.setClass(context, VideoActivity.class);
            intent.putExtra("curriulum_id", id);
            startActivity(intent);
        } else {
            return;
        }
    }
}
