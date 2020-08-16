package com.example.iwapp.presenter.usercenter;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.UnredNoticeBean;
import com.example.iwapp.bean.UserCenterBean;
import com.example.iwapp.interfaces.usercenter.UsercenterConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

public class UserCenterPresenter extends BasePresenter<UsercenterConstract.View> implements UsercenterConstract.Presenter {

    @Override
    public void usercenter() {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).usercenter(Constant.token)
                .compose(RxUtils.<UserCenterBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<UserCenterBean>(mView) {
                    @Override
                    public void onNext(UserCenterBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.UserCenterReturn(result);
                            }
                        }
                    }
                }));

    }

    @Override
    public void getUnredNotice() {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).unreadNotice(Constant.token)
                .compose(RxUtils.<UnredNoticeBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<UnredNoticeBean>(mView) {
                    @Override
                    public void onNext(UnredNoticeBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getUnredNoticeReturn(result);
                            }
                        }
                    }
                }));
    }

}
