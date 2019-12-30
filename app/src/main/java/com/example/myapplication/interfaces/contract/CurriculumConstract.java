package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

public interface CurriculumConstract {

    interface View extends IBaseView{
        void getCurriculumReturn(CurriculumBean bean);
    }

    interface Presenter extends IBasePresenter<View>{
        void getCurriculum(String curriculum_id);
    }

}
