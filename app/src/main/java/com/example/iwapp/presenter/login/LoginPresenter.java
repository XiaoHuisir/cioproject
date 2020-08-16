package com.example.iwapp.presenter.login;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.LoginBean;
import com.example.iwapp.interfaces.contract.LoginContract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {


    @Override
    public void login(String mobile, String password) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).login(mobile, password)
                .compose(RxUtils.<LoginBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(mView) {
                    @Override
                    public void onNext(LoginBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.loginReturn(result);
                            }
                        }
                    }
                }));
    }

}
