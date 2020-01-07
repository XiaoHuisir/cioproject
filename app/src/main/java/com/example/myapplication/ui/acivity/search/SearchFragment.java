package com.example.myapplication.ui.acivity.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adaper.SearchAdapter;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.SearchBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.SearchPresenter;
import com.example.myapplication.ui.acivity.video.VideoActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseFragment implements BaseAdapter.OnItemClickListener, IndexConstract.SearchView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    SearchAdapter searchAdapter;
    List<SearchBean.DataBean.CurriculumDataBean> list;

    int page = 1;
    int type = 0;

    public static SearchFragment instance(int type){
        SearchFragment fragment = new SearchFragment();
        fragment.type = type;
        return fragment;
    }


    @Override
    protected IBasePresenter getPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        searchAdapter = new SearchAdapter(list);
        searchAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(searchAdapter);
    }

    /**
     * 搜索
     * @param word
     */
    public void doSearch(String word){
        ((SearchPresenter) mPresenter).search(word, String.valueOf(type), String.valueOf(page));
    }


    @Override
    public void onItemClick(View v, int position) {
        int id = list.get(position).getId();
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public void searchResult(List<SearchBean.DataBean.CurriculumDataBean> result) {
        if(result.size() == 0){
            Toast.makeText(context, "没有所搜到相关数据", Toast.LENGTH_SHORT).show();
        }else{
            list.addAll(result);
            searchAdapter.notifyDataSetChanged();
        }
    }
}
