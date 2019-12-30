package com.example.myapplication.presenter.home;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

import java.util.Map;

public class IndexPresenter extends BasePresenter<IndexConstract.View> implements IndexConstract.Presenter {
    @Override
    public void getIndex(Map<String, String> map) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).getIndex(Constant.token, map)
                .compose(RxUtils.<IndexBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<IndexBean>(mView) {
                    @Override
                    public void onNext(IndexBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getIndexReturn(result);
                            }
                        }
                    }
                }));
    }
}
