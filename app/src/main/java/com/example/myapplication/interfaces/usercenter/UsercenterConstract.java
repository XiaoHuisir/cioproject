package com.example.myapplication.interfaces.usercenter;

import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.bean.UnredNoticeBean;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.bean.UserInfoUpdateBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.interfaces.contract.LoginContract;
import com.example.myapplication.interfaces.contract.UnredNoticeConstract;
import com.example.myapplication.presenter.usercenter.UserCenterPresenter;

public interface UsercenterConstract {

    interface View extends IBaseView {
        void UserCenterReturn(UserCenterBean result);
        void getUnredNoticeReturn(UnredNoticeBean bean);
    }

    interface Presenter extends IBasePresenter<UsercenterConstract.View> {
        void usercenter();
        void getUnredNotice();

    }


    interface UpdateView extends IBaseView{
        void updateUserInfoReturn(UserInfoUpdateBean result);
    }

    interface UpdatePresenter extends IBasePresenter<UpdateView>{
        void updateUserInfo(String nickname,String zw,String avatar);
    }



}
