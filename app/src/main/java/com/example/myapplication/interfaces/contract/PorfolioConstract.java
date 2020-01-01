package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.PorfolioBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

import java.util.Map;

import retrofit2.http.FieldMap;

public interface PorfolioConstract {

    interface View extends IBaseView {
        void getPorfolioReturn(ToadayBean result);
    }

    interface Presenter extends IBasePresenter<PorfolioConstract.View> {
        void getPorfolio(String type,String page);
    }
}
