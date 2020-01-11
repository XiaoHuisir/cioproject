package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.NotcieRecordBean;
import com.example.myapplication.bean.NoticeListBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

public interface NoticeRecordsConstract {

    interface View extends IBaseView {
        void getNoticeRecordReturn(NotcieRecordBean bean);
    }

    interface Presenter extends IBasePresenter<NoticeRecordsConstract.View> {
        void getNoticeRecord(String  page);
    }
}
