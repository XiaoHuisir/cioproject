package com.example.myapplication.interfaces.usercenter;

import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.interfaces.contract.LoginContract;
import com.example.myapplication.presenter.usercenter.UserCenterPresenter;

public interface UsercenterConstract {

    interface View extends IBaseView {
        void UserCenterReturn(UserCenterBean result);
    }

    interface Presenter extends IBasePresenter<UsercenterConstract.View> {
        void usercenter(String mobile, String password);

    }

}
