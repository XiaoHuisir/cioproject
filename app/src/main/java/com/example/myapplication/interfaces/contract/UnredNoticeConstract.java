package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.UnredNoticeBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

public interface UnredNoticeConstract {

    interface View extends IBaseView {
        void getUnredNoticeReturn(UnredNoticeBean bean);
    }

    interface Presenter extends IBasePresenter<UnredNoticeConstract.View> {
        void getUnredNotice();
    }
}
