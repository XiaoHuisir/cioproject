package com.example.iwapp.presenter.mine;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.NotcieRecordBean;
import com.example.iwapp.interfaces.contract.NoticeRecordsConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

public class NoticeRecordsPresenter extends BasePresenter<NoticeRecordsConstract.View> implements NoticeRecordsConstract.Presenter {


    @Override
    public void getNoticeRecord(String page) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).notice_records(Constant.token, page)
                .compose(RxUtils.<NotcieRecordBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<NotcieRecordBean>(mView) {
                    @Override
                    public void onNext(NotcieRecordBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getNoticeRecordReturn(result);
                            }
                        }
                    }
                }));
    }
}
