package com.example.iwapp.presenter.mine;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.DownFileBean;
import com.example.iwapp.bean.MyfilelistBean;
import com.example.iwapp.interfaces.contract.MyfilelistConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

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
