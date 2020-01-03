package com.example.myapplication.presenter.mine;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.MyfilelistBean;
import com.example.myapplication.interfaces.contract.DownfileConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

import java.util.Map;

public class DownFilePresenter extends BasePresenter<DownfileConstract.View> implements DownfileConstract.Presenter {

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
