package com.example.iwapp.presenter.home;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.SearchBean;
import com.example.iwapp.bean.UnredNoticeBean;
import com.example.iwapp.bean.VerBean;
import com.example.iwapp.interfaces.contract.IndexConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

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
