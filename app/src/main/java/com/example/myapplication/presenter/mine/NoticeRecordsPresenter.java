package com.example.myapplication.presenter.mine;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.NotcieRecordBean;
import com.example.myapplication.bean.NoticeListBean;
import com.example.myapplication.interfaces.contract.NoticeRecordsConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

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
