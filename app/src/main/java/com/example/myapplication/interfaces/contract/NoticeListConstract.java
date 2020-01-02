package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.NoticeListBean;
import com.example.myapplication.bean.UnredNoticeBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

public interface NoticeListConstract {


    interface View extends IBaseView {
        void getNoticeListReturn(NoticeListBean bean);
    }

    interface Presenter extends IBasePresenter<NoticeListConstract.View> {
        void getNoticeList(String  page);
    }
}
