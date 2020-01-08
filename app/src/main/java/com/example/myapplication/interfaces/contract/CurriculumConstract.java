package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

import java.util.Map;

public interface CurriculumConstract {

    interface View extends IBaseView{
        void getCurriculumReturn(CurriculumBean bean);

        void downFileReturn(DownFileBean result);
    }

    interface Presenter extends IBasePresenter<View>{
        void getCurriculum(String curriculum_id);

        void downFile(Map<String,String> map);
    }

}
