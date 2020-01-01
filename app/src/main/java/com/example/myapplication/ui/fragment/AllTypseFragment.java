package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adaper.IndexAdapter;
import com.example.myapplication.adaper.TodayAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.PorfolioBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.PorfolioConstract;
import com.example.myapplication.presenter.mine.PorfolioPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AllTypseFragment extends BaseFragment implements PorfolioConstract.View {
    @BindView(R.id.recycler_alltypes)
    RecyclerView recyclerAlltypes;
    private ArrayList<ToadayBean.DataBean.SevenDayBean> todayBeans;
    private TodayAdapter todayAdapter;


    @Override
    protected IBasePresenter getPresenter() {
        return new PorfolioPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.alltypse_fragment;
    }


    @Override
    protected void initView() {
        todayBeans = new ArrayList<>();
        todayAdapter = new TodayAdapter(todayBeans);
        recyclerAlltypes.setLayoutManager(new LinearLayoutManager(context));
        recyclerAlltypes.setAdapter(todayAdapter);
    }

    @Override
    public void getPorfolioReturn(ToadayBean result) {
        if (result.getStatus() == 1) {
            List<ToadayBean.DataBean.SevenDayBean> seven_day = result.getData().getSeven_day();
            if (seven_day!=null){
            todayBeans.clear();
            todayBeans.addAll(seven_day);
            todayAdapter.notifyDataSetChanged();}
        }
    }

    @Override
    protected void initData() {
        ((PorfolioPresenter) mPresenter).getPorfolio(Constant.CURTYPE ,"1");
    }


}
