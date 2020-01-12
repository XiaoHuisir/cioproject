package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.adaper.DaAgoAdapter;
import com.example.myapplication.adaper.TodayAdapter;
import com.example.myapplication.adaper.TodayBeansAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.PorfolioConstract;
import com.example.myapplication.presenter.mine.PorfolioPresenter;
import com.example.myapplication.ui.acivity.video.VideoActivity;

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
                }else {
                    lin1.setVisibility(View.INVISIBLE);
                }
                List<ToadayBean.DataBean.DayAgoBean> day_ago = result.getData().getDay_ago();
                if (day_ago.size() > 0) {
                    dayAgoBeans.clear();
                    dayAgoBeans.addAll(day_ago);
                    daAgoAdapter.notifyDataSetChanged();
                    lin2.setVisibility(View.VISIBLE);
                }else {
                    lin2.setVisibility(View.INVISIBLE);
                }
                List<ToadayBean.DataBean.TodayBean> today = result.getData().getToday();
                if (today.size() > 0) {
                    todayBeans.clear();
                    todayBeans.addAll(today);
                    todayBeansAdapter.notifyDataSetChanged();
                    lin.setVisibility(View.VISIBLE);
                }else {
                    lin.setVisibility(View.INVISIBLE);
                }
            }

        }
    }


    @Override
    protected void initData() {

        ((PorfolioPresenter) mPresenter).getPorfolio(Constant.CURTYPE, "1");
    }


    @Override
    public void trainclick(String id) {
        if (id!=null){
            Intent intent = new Intent();
            intent.setClass(context, VideoActivity.class);
            intent.putExtra("curriulum_id", id);
            startActivity(intent);
        }else {
            return;
        }
    }

    @Override
    public void click(String id) {
        if (id!=null){
            Intent intent = new Intent();
            intent.setClass(context, VideoActivity.class);
            intent.putExtra("curriulum_id", id);
            startActivity(intent);
        }else {
            return;
        }
    }

    @Override
    public void todaybeansclick(String id) {
        if (id!=null){
            Intent intent = new Intent();
            intent.setClass(context, VideoActivity.class);
            intent.putExtra("curriulum_id", id);
            startActivity(intent);
        }else {
            return;
        }
    }
}
