package com.example.myapplication.interfaces;

/**
 * Created by dell on 2019/5/30.
 */

public interface IBasePresenter<V extends IBaseView> {
    void attchView(V view);
    void detachView();
}
