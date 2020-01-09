package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adaper.TodayAdapter;

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

public class TrainTypesFragment extends BaseFragment implements PorfolioConstract.View, TodayAdapter.TodayItemClick {
    @BindView(R.id.txt_train)
    TextView txtTrain;
    @BindView(R.id.recycler_traintypes) //今天
            RecyclerView recyclerTraintypes;
    @BindView(R.id.recyc_seven_day)     //七天前
            RecyclerView recycSevenDay;
    @BindView(R.id.recyc_day_ago)      //更早
            RecyclerView recycDayAgo;

    private ArrayList<ToadayBean.DataBean.SevenDayBean> trainList;
    private TodayAdapter trainAdapter;


    @Override
    protected IBasePresenter getPresenter() {
        return new PorfolioPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.traintypes_fragment;
    }

    @Override
    protected void initView() {
        trainList = new ArrayList<>();
        trainAdapter = new TodayAdapter(trainList);
        trainAdapter.itemClick = this;
        recyclerTraintypes.setLayoutManager(new LinearLayoutManager(context));
        recyclerTraintypes.setAdapter(trainAdapter);
    }

    @Override
    public void getPorfolioReturn(ToadayBean result) {

        if (result.getStatus() == 1) {
            List<ToadayBean.DataBean.SevenDayBean> seven_day = result.getData().getSeven_day();
            if (seven_day != null) {
                trainList.clear();
                trainList.addAll(seven_day);
                trainAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    protected void initData() {

        ((PorfolioPresenter) mPresenter).getPorfolio(Constant.CURTYPE, "1");
    }

    @Override
    public void click(String id) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", id);
        startActivity(intent);
    }
}
