package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.bean.MyfilelistBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

public interface MyfilelistConstract {

    interface  View extends  IBaseView{
        void  getMyfilelistReturn(MyfilelistBean result);
    }
    interface Presenter extends IBasePresenter<MyfilelistConstract.View> {
        void getMyfilelist(String type);
    }


}
