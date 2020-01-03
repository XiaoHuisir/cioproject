package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

import java.util.Map;

import retrofit2.http.FieldMap;

public interface DownfileConstract {

    interface View extends IBaseView {
        void getdownfileReturn(DownFileBean result);
    }

    interface Presenter extends IBasePresenter<DownfileConstract.View> {
        void getdownfile(@FieldMap Map<String,String> map);
    }
}
