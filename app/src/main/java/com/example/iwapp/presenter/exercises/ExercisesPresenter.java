package com.example.iwapp.presenter.exercises;

import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BasePresenter;
import com.example.iwapp.bean.EvaluationSubmitBean;
import com.example.iwapp.bean.ExercisesBean;
import com.example.iwapp.interfaces.contract.ExercisesConstract;
import com.example.iwapp.utils.CommonSubscriber;
import com.example.iwapp.utils.HttpUtils;
import com.example.iwapp.utils.RxUtils;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

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
     *
     */
    @Override
    public void submitEvaluation(JSONObject jsonObject) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),jsonObject.toString());
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).submitEvaluation(Constant.token,body)
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
