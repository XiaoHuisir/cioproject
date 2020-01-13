package com.example.myapplication.presenter.mine;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.MyfilelistBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.contract.MyfilelistConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

import java.util.Map;

public class MyfilelistPresenter extends BasePresenter<MyfilelistConstract.View> implements MyfilelistConstract.Presenter {


    @Override
    public void getMyfilelist(int page) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).myfilelist(Constant.token, page)
                .compose(RxUtils.<MyfilelistBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<MyfilelistBean>(mView) {
                    @Override
                    public void onNext(MyfilelistBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getMyfilelistReturn(result);
                            }
                        }
                    }
                }));
    }

    @Override
    public void getdownfile(Map<String, String> map) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).downfile(Constant.token, map)
                .compose(RxUtils.<DownFileBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<DownFileBean>(mView) {
                    @Override
                    public void onNext(DownFileBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getdownfileReturn(result);
                            }
                        }
                    }
                }));
    }
}
