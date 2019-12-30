package com.example.myapplication.presenter.classify;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.TypeIndexBean;
import com.example.myapplication.interfaces.contract.TypeIndexConstact;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

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
