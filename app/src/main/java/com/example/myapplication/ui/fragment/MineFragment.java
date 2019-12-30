package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.presenter.home.IndexPresenter;
import com.example.myapplication.presenter.usercenter.UserCenterPresenter;

import java.util.HashMap;
import java.util.Map;

public class MineFragment extends BaseFragment implements UsercenterConstract.View {


    @Override
    protected IBasePresenter getPresenter() {
        return new UserCenterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        ((UserCenterPresenter) mPresenter).usercenter(Constant.mobiles, Constant.passwords);
    }

    @Override
    public void UserCenterReturn(UserCenterBean result) {

    }

}
