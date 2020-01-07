package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.EvaluatShowResultBean;
import com.example.myapplication.bean.EvaluationSubmitBean;
import com.example.myapplication.bean.ExercisesBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ExercisesConstract {
    interface View extends IBaseView {
        void getEvaluationReturn(ExercisesBean bean);

        void submitEvaluationReturn(EvaluationSubmitBean result);

    }

    interface Presenter extends IBasePresenter<ExercisesConstract.View> {
        void getEvaluation(String curriculum_id);

        void submitEvaluation(JSONObject jsonObject);
    }

    interface EvaluatResultView extends IBaseView{
        void getEvaluatResultReturn(EvaluatShowResultBean result);
    }

    interface EvaluatResultPresenter extends IBasePresenter<EvaluatResultView>{
        void getEvaluatResult(String evaluatId);
    }
}
