package com.example.myapplication.presenter.curriculum;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.contract.CurriculumConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

import java.util.Map;

public class CurriculumPresenter extends BasePresenter<CurriculumConstract.View> implements CurriculumConstract.Presenter {
    @Override
    public void getCurriculum(String curriculum_id) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).getCurriculum(Constant.token,curriculum_id)
                .compose(RxUtils.<CurriculumBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CurriculumBean>(mView) {
                    @Override
                    public void onNext(CurriculumBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getCurriculumReturn(result);
                            }
                        }
                    }
                }));
    }

    /**
     * 下载文件
     * @param map
     */
    @Override
    public void downFile(Map<String, String> map) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).downfile(Constant.token,map)
                .compose(RxUtils.<DownFileBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<DownFileBean>(mView) {
                    @Override
                    public void onNext(DownFileBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.downFileReturn(result);
                            }
                        }
                    }
                }));
    }

}
