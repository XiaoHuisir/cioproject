package com.example.iwapp.presenter.versions;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.VerBean;
import com.example.iwapp.interfaces.contract.VersionConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

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
