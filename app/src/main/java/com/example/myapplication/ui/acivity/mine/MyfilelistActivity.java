package com.example.myapplication.ui.acivity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adaper.MyfilelistAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.bean.MyfilelistBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.MyfilelistConstract;
import com.example.myapplication.presenter.mine.MyfilelistPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyfilelistActivity extends BaseActivity implements MyfilelistConstract.View, MyfilelistAdapter.MyfilelistClick {


    @BindView(R.id.iv_myreturn)
    ImageView ivMyreturn;
    @BindView(R.id.recycler_my)
    RecyclerView recyclerMy;
    private MyfilelistAdapter myfilelistAdapter;
    private ArrayList<MyfilelistBean.DataBean> dataBeanslist;
    private int curriculum_id;
    private String file_name;
    private String file_size;
    private String file_url;


    @Override
    protected IBasePresenter getPresenter() {
        return new MyfilelistPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myfilelist;
    }

    @Override
    protected void initView() {
        dataBeanslist = new ArrayList<>();
        recyclerMy.setLayoutManager(new LinearLayoutManager(context));
        myfilelistAdapter = new MyfilelistAdapter(dataBeanslist, this);
        recyclerMy.setAdapter(myfilelistAdapter);

    }

    @Override
    protected void initData() {
        ((MyfilelistPresenter) mPresenter).getMyfilelist("1");


    }

    @Override
    public void getMyfilelistReturn(MyfilelistBean result) {
        if (result.getStatus() == 1) {
            List<MyfilelistBean.DataBean> data = result.getData();
            dataBeanslist.clear();
            dataBeanslist.addAll(data);
            myfilelistAdapter.notifyDataSetChanged();
        }

    }

    //下载文件 TODO
    @Override
    public void getdownfileReturn(DownFileBean result) {
        Toast.makeText(context, "" + result.getStatus(), Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.iv_myreturn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_myreturn:
                finish();
                break;
        }
    }


    @Override
    public void myfilelistClick(MyfilelistBean.DataBean datas) {
        if (datas != null) {
            curriculum_id = datas.getCurriculum_id();
            file_name = datas.getFile_name();
            file_size = datas.getFile_size();
            file_url = datas.getFile_url();

            HashMap<String, String> map = new HashMap<>();
            map.put("curriculum_id", String.valueOf(curriculum_id));
            map.put("file_name", file_name);
            map.put("file_size", file_size);
            map.put("file_url", file_url);
            ((MyfilelistPresenter) mPresenter).getdownfile(map);
        }
    }
}

