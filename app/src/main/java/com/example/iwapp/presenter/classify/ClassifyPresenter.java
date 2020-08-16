package com.example.iwapp.presenter.classify;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.TypeIndexBean;
import com.example.iwapp.interfaces.contract.TypeIndexConstact;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

public class ClassifyPresenter extends BasePresenter<TypeIndexConstact.View> implements TypeIndexConstact.Presenter {


    @Override
    public void getTypeIndex(String type, String page) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).getTypeIndex(Constant.token,type,page)
                .compose(RxUtils.<TypeIndexBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<TypeIndexBean>(mView) {
                    @Override
                    public void onNext(TypeIndexBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getTypeIndexReturn(result);
                            }
                        }
                    }
                }));
    }
}
