package com.example.myapplication.ui.acivity.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adaper.SearchAdapter;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.SearchBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.ui.acivity.video.VideoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseFragment implements BaseAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    SearchAdapter searchAdapter;
    List<SearchBean.DataBean.CurriculumDataBean> list;

    public static SearchFragment instance(List<SearchBean.DataBean.CurriculumDataBean> list){
        SearchFragment fragment = new SearchFragment();
        fragment.list = list;
        return fragment;
    }


    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        searchAdapter = new SearchAdapter(list);
        searchAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(searchAdapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        int id = list.get(position).getId();
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", String.valueOf(id));
        startActivity(intent);
    }
}
