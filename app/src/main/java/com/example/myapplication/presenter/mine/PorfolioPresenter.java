package com.example.myapplication.presenter.mine;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.PorfolioBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.contract.PorfolioConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

import java.util.Map;

public class PorfolioPresenter extends BasePresenter<PorfolioConstract.View> implements PorfolioConstract.Presenter {
    @Override
    public void getPorfolio(String type, String page) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).porfolio(Constant.token, type,page)
                .compose(RxUtils.<ToadayBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<ToadayBean>(mView) {
                    @Override
                    public void onNext(ToadayBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getPorfolioReturn(result);
                            }
                        }
                    }
                }));
    }
//    @Override
//    public void getPorfolio(String ,String page) {
//        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).porfolio(Constant.token, map)
//                .compose(RxUtils.<PorfolioBean>rxScheduler())
//                .subscribeWith(new CommonSubscriber<PorfolioBean>(mView) {
//                    @Override
//                    public void onNext(PorfolioBean result) {
//                        if (result != null) {
//                            if (mView != null) {
//                                mView.getPorfolioReturn(result);
//                            }
//                        }
//                    }
//                }));
//    }
}
