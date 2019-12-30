package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.TypeIndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TypeIndexConstact {

    interface View extends IBaseView{
        void getTypeIndexReturn(TypeIndexBean bean);
    }

    interface Presenter extends IBasePresenter<View>{
        void getTypeIndex(String type,String page);
    }




}
