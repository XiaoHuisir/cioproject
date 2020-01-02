package com.example.myapplication.presenter.noticelist;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.MyfilelistBean;
import com.example.myapplication.bean.NoticeListBean;
import com.example.myapplication.interfaces.contract.NoticeListConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

public class NoticeListPresenter extends BasePresenter<NoticeListConstract.View> implements NoticeListConstract.Presenter {
    @Override
    public void getNoticeList(String page) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).noticeList(Constant.token, page)
                .compose(RxUtils.<NoticeListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<NoticeListBean>(mView) {
                    @Override
                    public void onNext(NoticeListBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getNoticeListReturn(result);
                            }
                        }
                    }
                }));
    }
}
