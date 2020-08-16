package com.example.iwapp.presenter.mine;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.ToadayBean;
import com.example.iwapp.interfaces.contract.PorfolioConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

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
