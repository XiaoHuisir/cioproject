package com.example.myapplication.ui.acivity.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adaper.IndexAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.IndexPresenter;
import com.example.myapplication.ui.acivity.video.VideoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseActivity extends BaseActivity implements IndexConstract.View,IndexAdapter.IndexItemClick {

    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_title)
    ConstraintLayout layoutTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    IndexAdapter indexAdapter;
    List<IndexBean.DataBean.CurriculumDataBean> list;
    int curType;

    @Override
    protected IBasePresenter getPresenter() {
        return new IndexPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_course;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        indexAdapter = new IndexAdapter(list);
        indexAdapter.itemClick = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(indexAdapter);

    }

    @Override
    protected void initData() {
        curType = getIntent().getIntExtra("type",0);
        String title = getIntent().getStringExtra("title");
        txtTitle.setText(title);
        getIndex();
    }

    private void getIndex() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("curriculum", String.valueOf(2));
        map.put("type", String.valueOf(curType));
        map.put("page", "1");
        ((IndexPresenter) mPresenter).getIndex(map);
    }

    @Override
    public void click(String id) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", id);
        startActivity(intent);
    }

    @Override
    public void getIndexReturn(IndexBean result) {
        list.clear();
        list.addAll(result.getData().getCurriculum_data());
        indexAdapter.notifyDataSetChanged();
    }
}
