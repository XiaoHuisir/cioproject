package com.example.myapplication.ui.acivity.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseFragment implements BaseAdapter.OnItemClickListener, IndexConstract.SearchView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;

    SearchAdapter searchAdapter;
    List<SearchBean.DataBean.CurriculumDataBean> list;

    int page = 1;
    int type = 0;
    TabLayoutFun tabLayoutFun;
    String word;
    boolean isRefreshing;

    public static SearchFragment instance(int type,TabLayoutFun tabLayoutFun){
        SearchFragment fragment = new SearchFragment();
        fragment.type = type;
        fragment.tabLayoutFun = tabLayoutFun;
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

        smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                isRefreshing = true;
                ((SearchPresenter) mPresenter).search(word, String.valueOf(type), String.valueOf(page));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefresh.finishRefresh(500);
            }
        });
    }

    /**
     * 搜索
     * @param word
     */
    public void doSearch(String word){
        page = 1;
        this.word = word;
        isRefreshing = false;
        ((SearchPresenter) mPresenter).search(word, String.valueOf(type), String.valueOf(page));
    }

    /**
     * 清理搜索
     */
    public void clearSearch(){
        page = 1;
        this.word = "";
        list.clear();
        searchAdapter.notifyDataSetChanged();
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
        if(isRefreshing){
            smartRefresh.finishLoadMore(300);
            if(result.size() > 0){
                tabLayoutFun.setTabLayout(View.VISIBLE);
                list.addAll(result);
                searchAdapter.notifyDataSetChanged();
            }else{
                page--;
                Toast.makeText(context, "没有所搜到相关数据", Toast.LENGTH_SHORT).show();
            }
        }else{
            list.clear();
            if(result.size() == 0){
                //tabLayoutFun.setTabLayout(View.GONE);
                Toast.makeText(context, "没有所搜到相关数据", Toast.LENGTH_SHORT).show();
                searchAdapter.notifyDataSetChanged();
            }else{
                tabLayoutFun.setTabLayout(View.VISIBLE);
                list.addAll(result);
                searchAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void showError(String errorMsg) {
        if(isRefreshing){
            smartRefresh.finishLoadMore();
        }
    }

    @Override
    public void showError() {
        if(isRefreshing){
            smartRefresh.finishLoadMore();
        }
    }

    interface TabLayoutFun{
        void setTabLayout(int visible);
    }
}
