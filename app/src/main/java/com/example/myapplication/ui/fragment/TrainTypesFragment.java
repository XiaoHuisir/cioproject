package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adaper.TrainAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.PorfolioConstract;
import com.example.myapplication.presenter.mine.PorfolioPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TrainTypesFragment extends BaseFragment implements PorfolioConstract.View {
    @BindView(R.id.txt_train)
    TextView txtTrain;
    @BindView(R.id.recycler_traintypes)
    RecyclerView recyclerTraintypes;
    private ArrayList<ToadayBean.DataBean.SevenDayBean> trainList;
    private TrainAdapter trainAdapter;


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
        trainAdapter = new TrainAdapter(trainList);
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


}
