package com.example.myapplication.presenter.usercenter;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.bean.UserInfoUpdateBean;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

public class UpdateUserInfoPresenter extends BasePresenter<UsercenterConstract.UpdateView> implements UsercenterConstract.UpdatePresenter {
    @Override
    public void updateUserInfo(String nickname, String zw, String avatar) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).updateUserInfo(Constant.token, nickname, zw,avatar)
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
}
