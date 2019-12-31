package com.example.myapplication.presenter.exercises;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.EvaluationSubmitBean;
import com.example.myapplication.bean.ExercisesBean;
import com.example.myapplication.interfaces.contract.ExercisesConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

public class ExercisesPresenter extends BasePresenter<ExercisesConstract.View> implements ExercisesConstract.Presenter {
    @Override
    public void getEvaluation(String curriculum_id) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).getEvaluation(Constant.token, curriculum_id)
                .compose(RxUtils.<ExercisesBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<ExercisesBean>(mView) {
                    @Override
                    public void onNext(ExercisesBean evaluationBean) {
                        if (evaluationBean != null) {
                            if (mView != null) {
                                mView.getEvaluationReturn(evaluationBean);
                            }
                        }
                    }
                })
        );
    }

    /**
     * 提交答题
     * @param curriculumId
     * @param answer
     */
    @Override
    public void submitEvaluation(String curriculumId, String answer) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).submitEvaluation(Constant.token, curriculumId,answer)
                .compose(RxUtils.<EvaluationSubmitBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<EvaluationSubmitBean>(mView) {
                    @Override
                    public void onNext(EvaluationSubmitBean evaluationBean) {
                        if (evaluationBean != null) {
                            if (mView != null) {
                                mView.submitEvaluationReturn(evaluationBean);
                            }
                        }
                    }
                })
        );
    }

}
