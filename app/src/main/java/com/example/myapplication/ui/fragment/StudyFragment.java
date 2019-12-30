package com.example.myapplication.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adaper.IndexAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.IndexPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class StudyFragment extends BaseFragment implements IndexConstract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int courseType = 1;

    IndexAdapter indexAdapter;
    List<IndexBean.DataBean.CurriculumDataBean> list;

    @Override
    protected IBasePresenter getPresenter() {
        return new IndexPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        indexAdapter = new IndexAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(indexAdapter);

        getIndex();
    }

    private void getIndex() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("curriculum", String.valueOf(1));
        map.put("type", String.valueOf(1));
        map.put("page", "1");
        ((IndexPresenter) mPresenter).getIndex(map);
    }


    @Override
    public void getIndexReturn(IndexBean result) {
        list.clear();
        list.addAll(result.getData().getCurriculum_data());
        indexAdapter.notifyDataSetChanged();
    }
}
