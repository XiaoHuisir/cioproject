package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.SearchPresenter;
import com.example.myapplication.ui.fragment.HomeFragment;
import com.example.myapplication.ui.fragment.MineFragment;
import com.example.myapplication.ui.fragment.ClassifyFragment;
import com.example.myapplication.ui.fragment.StudyFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements IndexConstract.SearchView {


    @BindView(R.id.tl)
    TabLayout mTl;
    @BindView(R.id.fl)
    FrameLayout mFl;
    @BindView(R.id.searchView)
    SearchView mSearchView;
    @BindView(R.id.layout_search)
    RelativeLayout layoutSearch;


    private FragmentManager manager;
    Fragment homeFragment;
    Fragment classifyFragment;
    Fragment mineFragment;

    int curType;

    MenuItem mSearchMenuItem;

    private void initFragment() {
        manager = getSupportFragmentManager();
        mTl.addTab(mTl.newTab().setText("首页").setIcon(R.drawable.home));
        mTl.addTab(mTl.newTab().setText("分类").setIcon(R.drawable.classify));
        mTl.addTab(mTl.newTab().setText("我的").setIcon(R.drawable.mine));
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        mineFragment = new MineFragment();

        mSearchView.setIconifiedByDefault(false);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("搜索");
        mSearchView.setFocusable(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(TextUtils.isEmpty(s)){
                    return false;
                }
                if(curType == 0){
                    ((SearchPresenter)mPresenter).search(s,String.valueOf(Constant.CourseType),String.valueOf(1));
                }else if(curType == 1){
                    ((SearchPresenter)mPresenter).search(s,String.valueOf(0),String.valueOf(1));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }


    @Override
    protected IBasePresenter getPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();

        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mSearchView.setQuery("",false);
                int position = tab.getPosition();
                curType = position;
                showFragment(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        showFragment(0);
    }

    @Override
    protected void initData() {

    }


    private void showFragment(int type){
        FragmentTransaction transaction = manager.beginTransaction();
        switch (type){
            case 0:
                transaction.replace(R.id.fl,homeFragment).commit();
                layoutSearch.setVisibility(View.VISIBLE);
                break;
            case 1:
                transaction.replace(R.id.fl,classifyFragment).commit();
                layoutSearch.setVisibility(View.VISIBLE);
                break;
            case 2:
                transaction.replace(R.id.fl,mineFragment).commit();
                layoutSearch.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void searchResult(List<IndexBean.DataBean.CurriculumDataBean> result) {
        if(curType == 0){
            ((HomeFragment)homeFragment).searchResult(result);
        }else if(curType == 1){
            ((ClassifyFragment)classifyFragment).searchResult(result);
        }
    }

}
