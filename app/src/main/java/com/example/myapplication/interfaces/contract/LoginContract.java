package com.example.myapplication.interfaces.contract;


import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

public interface LoginContract {
    interface View extends IBaseView {
        void loginReturn(LoginBean result);
    }
    interface Presenter extends IBasePresenter<View> {
        void login(String mobile,String password);
    }
}
