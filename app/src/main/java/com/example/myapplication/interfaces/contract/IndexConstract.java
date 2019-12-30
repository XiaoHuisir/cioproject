package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

import java.util.Map;

import retrofit2.http.FieldMap;

public interface IndexConstract {
    interface View extends IBaseView{
        void getIndexReturn(IndexBean result);
    }

    interface Presenter extends IBasePresenter<View>{
        void getIndex(@FieldMap Map<String,String> map);
    }
}
