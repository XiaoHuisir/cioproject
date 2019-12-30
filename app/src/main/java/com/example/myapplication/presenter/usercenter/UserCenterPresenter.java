package com.example.myapplication.presenter.usercenter;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.ExercisesBean;
import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.interfaces.contract.ExercisesConstract;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

public class UserCenterPresenter extends BasePresenter<UsercenterConstract.View> implements UsercenterConstract.Presenter {

    @Override
    public void usercenter(String mobile, String password) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).usercenter(Constant.token, mobile, password)
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

}
