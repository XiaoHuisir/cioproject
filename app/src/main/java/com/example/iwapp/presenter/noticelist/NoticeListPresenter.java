package com.example.iwapp.presenter.noticelist;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.NoticeListBean;
import com.example.iwapp.interfaces.contract.NoticeListConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

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
