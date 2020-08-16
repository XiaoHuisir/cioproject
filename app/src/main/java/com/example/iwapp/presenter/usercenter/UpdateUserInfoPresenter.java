package com.example.iwapp.presenter.usercenter;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.TokenBean;
import com.example.iwapp.bean.UserInfoUpdateBean;
import com.example.iwapp.interfaces.usercenter.UsercenterConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

public class UpdateUserInfoPresenter extends BasePresenter<UsercenterConstract.UpdateView> implements UsercenterConstract.UpdatePresenter {

    @Override
    public void updateUserInfo(String nickname,String zw,String avatar) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).updateUserInfo(Constant.token, nickname,zw,avatar)
                .compose(RxUtils.<UserInfoUpdateBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<UserInfoUpdateBean>(mView) {
                    @Override
                    public void onNext(UserInfoUpdateBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.updateUserInfoReturn(result);
                            }
                        }
                    }
                }));
    }

    @Override
    public void getToken() {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).getToken(Constant.token)
                .compose(RxUtils.<TokenBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<TokenBean>(mView) {
                    @Override
                    public void onNext(TokenBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getTokenReturn(result);
                            }
                        }
                    }
                }));
    }
}
