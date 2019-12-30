package com.example.myapplication.presenter.login;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.interfaces.contract.LoginContract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {


    @Override
    public void login(String mobile, String password) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).login(mobile,password)
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
