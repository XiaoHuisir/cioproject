package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.bean.MyfilelistBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

import java.util.Map;

import retrofit2.http.FieldMap;

public interface MyfilelistConstract {

    interface  View extends  IBaseView{
        void  getMyfilelistReturn(MyfilelistBean result);
        void getdownfileReturn(DownFileBean result);
    }
    interface Presenter extends IBasePresenter<MyfilelistConstract.View> {
        void getMyfilelist(String type);
        void getdownfile(@FieldMap Map<String,String> map);
    }


}
