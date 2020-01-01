package com.example.myapplication.presenter.mine;

import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.MyfilelistBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.contract.MyfilelistConstract;
import com.example.myapplication.utils.CommonSubscriber;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.RxUtils;

public class MyfilelistPresenter extends BasePresenter<MyfilelistConstract.View>  implements  MyfilelistConstract.Presenter {


    @Override
    public void getMyfilelist(String type) {
        addSubscribe(HttpUtils.getMyServer(Constant.BaseUrl).myfilelist(Constant.token,type)
                .compose(RxUtils.<MyfilelistBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<MyfilelistBean>(mView) {
                    @Override
                    public void onNext(MyfilelistBean result) {
                        if (result != null) {
                            if (mView != null) {
                                mView.getMyfilelistReturn(result);
                            }
                        }
                    }
                }));
    }
}
