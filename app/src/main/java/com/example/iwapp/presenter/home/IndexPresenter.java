package com.example.iwapp.presenter.home;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.IndexBean;
import com.example.iwapp.interfaces.contract.IndexConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

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
