package com.example.myapplication.ui.fragment;

import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.PorfolioBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.PorfolioConstract;
import com.example.myapplication.presenter.mine.PorfolioPresenter;

import java.util.HashMap;

public class TrainTypesFragment extends BaseFragment  implements PorfolioConstract.View {
    @Override
    protected IBasePresenter getPresenter() {
        return new PorfolioPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.traintypes_fragment;
    }

    @Override
    public void getPorfolioReturn(ToadayBean result) {
//         "status": 1,
            if (result.getStatus()==1){
                Toast.makeText(context,"成功",Toast.LENGTH_LONG)
                        .show();
            }
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("page","1");
        map.put("type","1");

        ((PorfolioPresenter)mPresenter).getPorfolio("2","1");
    }
}
