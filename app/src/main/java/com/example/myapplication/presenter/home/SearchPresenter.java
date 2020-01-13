package com.example.myapplication.presenter.home;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.SearchBean;
import com.example.myapplication.bean.UnredNoticeBean;
import com.example.myapplication.bean.VerBean;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class SearchPresenter extends BasePresenter<IndexConstract.SearchView> implements IndexConstract.SearchPresenter {
    @Override
    public void search(String keyword, String type, String page) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).search(Constant.token, keyword,type,page)
                .compose(RxUtils.<SearchBean>rxScheduler())
                .map(new Function<SearchBean, List<SearchBean.DataBean.CurriculumDataBean>>() {
                    @Override
                    public List<SearchBean.DataBean.CurriculumDataBean> apply(SearchBean searchBean) throws Exception {
                        return searchBean.getData().getCurriculum_data();
                    }
                })
                .subscribeWith(new CommonSubscriber<List<SearchBean.DataBean.CurriculumDataBean>>(mView) {
                    @Override
                    public void onNext(List<SearchBean.DataBean.CurriculumDataBean> result) {
                        mView.searchResult(result);
                    }
                }));
    }

    @Override
    public void getUnredNotice() {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).unreadNotice(Constant.token)
                .compose(RxUtils.<UnredNoticeBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<UnredNoticeBean>(mView) {
                    @Override
                    public void onNext(UnredNoticeBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getUnredNoticeReturn(result);
                            }
                        }
                    }
                }));
    }

    @Override
    public void getVersion() {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).getVersionInfo(Constant.token)
                .compose(RxUtils.<VerBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<VerBean>(mView) {
                    @Override
                    public void onNext(VerBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getVersionReturn(result);
                            }
                        }
                    }
                }));
    }


}
