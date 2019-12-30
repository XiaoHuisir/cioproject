package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.myapplication.ui.fragment.HomeFragment;
import com.example.myapplication.ui.fragment.MineFragment;
import com.example.myapplication.ui.fragment.ClassifyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {


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

    MenuItem mSearchMenuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        initListener();

        showFragment(0);
    }

    private void initFragments() {
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }



    private void initListener() {
        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mSearchView.setQuery("",false);
                int position = tab.getPosition();
                showFragment(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
}
