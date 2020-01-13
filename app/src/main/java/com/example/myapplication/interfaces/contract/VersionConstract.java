package com.example.myapplication.interfaces.contract;

import com.example.myapplication.bean.VerBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.IBaseView;

public interface VersionConstract {

    interface View extends IBaseView{
        void getVersionReturn(VerBean result);
    }

    interface Persenter extends IBasePresenter<View>{
        void getVersionInfo();
    }

}
