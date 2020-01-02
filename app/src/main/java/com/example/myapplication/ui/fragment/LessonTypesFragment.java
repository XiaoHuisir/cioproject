package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adaper.LessonAdapter;
import com.example.myapplication.adaper.TodayAdapter;
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

public class LessonTypesFragment extends BaseFragment implements PorfolioConstract.View {
    @BindView(R.id.txt_lesson)
    TextView txtLesson;
    @BindView(R.id.recycler_lessontypes)
    RecyclerView recyclerLessontypes;
    private LessonAdapter lessonAdapter;
    private ArrayList<ToadayBean.DataBean.SevenDayBean> sevenDayBeans;


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

        sevenDayBeans = new ArrayList<>();
        lessonAdapter = new LessonAdapter(sevenDayBeans);
        recyclerLessontypes.setLayoutManager(new LinearLayoutManager(context));
        recyclerLessontypes.setAdapter(lessonAdapter);
    }
    @Override
    public void getPorfolioReturn(ToadayBean result) {
        if (result.getStatus() == 1) {
            List<ToadayBean.DataBean.SevenDayBean> seven_day = result.getData().getSeven_day();
            if (seven_day!=null){
                sevenDayBeans.clear();
                sevenDayBeans.addAll(seven_day);
                lessonAdapter.notifyDataSetChanged();}
        }
    }



    @Override
    protected void initData() {

        ((PorfolioPresenter) mPresenter).getPorfolio(Constant.CURTYPE, "1");
    }


}