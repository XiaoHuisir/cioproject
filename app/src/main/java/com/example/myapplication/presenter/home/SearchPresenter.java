package com.example.myapplication.presenter.home;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.SearchBean;
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
                .map(new Function<SearchBean, List<IndexBean.DataBean.CurriculumDataBean>>() {
                    @Override
                    public List<IndexBean.DataBean.CurriculumDataBean> apply(SearchBean searchBean) throws Exception {
                        List<IndexBean.DataBean.CurriculumDataBean> list = new ArrayList<>();
                        for(SearchBean.DataBean.CurriculumDataBean item:searchBean.getData().getCurriculum_data()){
                            IndexBean.DataBean.CurriculumDataBean bean = new IndexBean.DataBean.CurriculumDataBean();
                            bean.setId(item.getId());
                            bean.setTitle(item.getTitle());
                            bean.setTeacher(item.getTeacher());
                            bean.setGs(item.getGs());
                            bean.setLog(item.getLog());
                            bean.setUp_new(item.getUp_new());
                            bean.setLen(item.getLen());
                            bean.setJd(item.getJd());
                            list.add(bean);
                        }
                        return list;
                    }
                })
                .subscribeWith(new CommonSubscriber<List<IndexBean.DataBean.CurriculumDataBean>>(mView) {
                    @Override
                    public void onNext(List<IndexBean.DataBean.CurriculumDataBean> result) {
                        mView.searchResult(result);
                    }
                }));
    }
}
