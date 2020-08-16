package com.example.iwapp.presenter.exercises;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.EvaluatShowResultBean;
import com.example.iwapp.interfaces.contract.ExercisesConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

public class EvaluatResultPresenter extends BasePresenter<ExercisesConstract.EvaluatResultView> implements ExercisesConstract.EvaluatResultPresenter {
    @Override
    public void getEvaluatResult(String evaluatId) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).showEvaluationResult(Constant.token,evaluatId)
                .compose(RxUtils.<EvaluatShowResultBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<EvaluatShowResultBean>(mView) {
                    @Override
                    public void onNext(EvaluatShowResultBean evaluationBean) {
                        if (evaluationBean != null) {
                            if (mView != null) {
                                mView.getEvaluatResultReturn(evaluationBean);
                            }
                        }
                    }
                })
        );
    }

}
