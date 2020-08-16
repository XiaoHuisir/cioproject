package com.example.iwapp.presenter.curriculum;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.CurriculumBean;
import com.example.iwapp.bean.DownFileBean;
import com.example.iwapp.interfaces.contract.CurriculumConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

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
