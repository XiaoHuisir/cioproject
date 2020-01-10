package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adaper.RecordAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.SearchBean;
import com.example.myapplication.bean.UnredNoticeBean;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.presenter.home.SearchPresenter;
import com.example.myapplication.ui.acivity.mine.NoticeListAcitivity;
import com.example.myapplication.ui.acivity.search.SearchActivity;
import com.example.myapplication.ui.fragment.HomeFragment;
import com.example.myapplication.ui.fragment.MineFragment;
import com.example.myapplication.ui.fragment.ClassifyFragment;
import com.example.myapplication.ui.fragment.StudyFragment;
import com.example.myapplication.utils.NumView;

import org.greenrobot.greendao.annotation.JoinEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements View.OnClickListener,
        IndexConstract.SearchView , RecordAdapter.RecordItemClick {


    @BindView(R.id.tl)
    TabLayout mTl;
    @BindView(R.id.fl)
    FrameLayout mFl;
    @BindView(R.id.txt_search)
    TextView txtSearch;
    @BindView(R.id.layout_search)
    RelativeLayout layoutSearch;
    @BindView(R.id.layout_msg)
    RelativeLayout layoutMsg;
    @BindView(R.id.num_wx)
    NumView numWx;
    private FragmentManager manager;
    Fragment homeFragment;
    Fragment classifyFragment;
    Fragment mineFragment;
    int curType;
    private int numNot;
    boolean indxler = false;

    private void initFragment() {
        manager = getSupportFragmentManager();
        mTl.addTab(mTl.newTab().setText("课程").setIcon(R.drawable.home));
        mTl.addTab(mTl.newTab().setText("分类").setIcon(R.drawable.classify));
        mTl.addTab(mTl.newTab().setText("我的").setIcon(R.drawable.mine));
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        mineFragment = new MineFragment();


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
        txtSearch.setOnClickListener(this);
        layoutMsg.setOnClickListener(this);
        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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
        ((SearchPresenter)mPresenter).getUnredNotice();
    }


    private void showFragment(int type) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (type) {
            case 0:
                transaction.replace(R.id.fl, homeFragment).commit();
                layoutSearch.setVisibility(View.VISIBLE);
                break;
            case 1:
                transaction.replace(R.id.fl, classifyFragment).commit();
                layoutSearch.setVisibility(View.VISIBLE);
                break;
            case 2:
                transaction.replace(R.id.fl, mineFragment).commit();
                layoutSearch.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_search:
                Intent intent = new Intent();
                intent.setClass(context, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_msg:
                //打开消息界面
//                numWx.setNum(0);
                numWx.setNum(0);
                indxler=true;
                Intent notice = new Intent();
                notice.setClass(context, NoticeListAcitivity.class);
                startActivity(notice);

                break;
        }
    }

    @Override
    public void click(String id) {

    }


    @Override
    public void searchResult(List<SearchBean.DataBean.CurriculumDataBean> result) {

    }

    @Override
    public void getUnredNoticeReturn(UnredNoticeBean bean) {
        if (bean.getStatus() == 1) {

            String notice_num = bean.getData().getNotice_num();
            Constant.NUM_VIEW="111";
           int noticenum = Integer.valueOf(notice_num).intValue();
//            int notice_nums = Integer.parseInt(notice_num);

            if (noticenum == 0) {
                numWx.setNum(0);
            } else {
                numWx.setNum(noticenum);
            }
        }
    }
}
