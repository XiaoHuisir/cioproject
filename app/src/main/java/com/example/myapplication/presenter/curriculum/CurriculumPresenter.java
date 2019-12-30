package com.example.myapplication.presenter.curriculum;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.contract.CurriculumConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

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

}
