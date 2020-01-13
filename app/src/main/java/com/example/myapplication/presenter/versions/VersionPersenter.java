package com.example.myapplication.presenter.versions;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.VerBean;
import com.example.myapplication.interfaces.contract.VersionConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

public class VersionPersenter extends BasePresenter<VersionConstract.View> implements VersionConstract.Persenter {
    @Override
    public void getVersionInfo() {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).getVersionInfo(Constant.token)
                .compose(RxUtils.<VerBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<VerBean>(mView) {
                    @Override
                    public void onNext(VerBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getVersionReturn(result);
                            }
                        }
                    }
                }));
    }

}
