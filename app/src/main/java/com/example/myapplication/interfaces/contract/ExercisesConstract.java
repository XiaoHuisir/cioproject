package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.ExercisesBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

public interface ExercisesConstract {
    interface View extends IBaseView {
        void getEvaluationReturn(ExercisesBean bean);
    }

    interface Presenter extends IBasePresenter<ExercisesConstract.View> {
        void getEvaluation(String curriculum_id);
    }
}
