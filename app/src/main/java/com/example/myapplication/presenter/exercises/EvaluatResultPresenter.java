package com.example.myapplication.presenter.exercises;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.EvaluatShowResultBean;
import com.example.myapplication.bean.EvaluationSubmitBean;
import com.example.myapplication.interfaces.contract.ExercisesConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

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
