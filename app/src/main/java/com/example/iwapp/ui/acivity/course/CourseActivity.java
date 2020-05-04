package com.example.iwapp.ui.acivity.course;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.iwapp.R;
import com.example.iwapp.adaper.IndexAdapter;
import com.example.iwapp.base.BaseActivity;
import com.example.iwapp.bean.IndexBean;
import com.example.iwapp.interfaces.IBasePresenter;
import com.example.iwapp.interfaces.contract.IndexConstract;
import com.example.iwapp.presenter.home.IndexPresenter;
import com.example.iwapp.ui.acivity.video.VideoActivity;
import com.example.iwapp.utils.NetDownResponse;
import com.example.iwapp.utils.NetRequsetUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseActivity extends BaseActivity implements IndexConstract.View, IndexAdapter.IndexItemClick {

    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_title)
    RelativeLayout layoutTitle;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    int page = 1;


    IndexAdapter indexAdapter;
    List<IndexBean.DataBean.CurriculumDataBean> list;
    int curType;

    @OnClick({R.id.img_return})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                finish();
                break;
        }
    }

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
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(indexAdapter);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                page = page + 1;


                JSONObject jsonObject = new JSONObject();
                jsonObject.put("curriculum", String.valueOf(2));
                jsonObject.put("type", String.valueOf(curType));
                jsonObject.put("page",  page + "");

                NetRequsetUtil.getInstance().netRequestPostJson("index/train/index",
                        jsonObject.toString(), new NetDownResponse() {
                            @Override
                            public void success(String str) {
                                IndexBean indexBean = JSON.parseObject(str, IndexBean.class);
                                List<IndexBean.DataBean.CurriculumDataBean> curriculum_data = indexBean.getData().getCurriculum_data();
                                if ( curriculum_data.size()> 0 ){
                                    for (IndexBean.DataBean.CurriculumDataBean curriculum_datum : curriculum_data) {
                                        list.add(curriculum_datum);
                                        indexAdapter.notifyDataSetChanged();
                                    }

                                    recyclerView.loadMoreComplete();
                                }else {
                                    Toast.makeText(activity, "没有更多了", Toast.LENGTH_SHORT).show();
                                    recyclerView.setNoMore(true);
                                }
                            }

                            @Override
                            public void errowithresponse(String str) {
                                recyclerView.loadMoreComplete();
                            }

                            @Override
                            public void erro() {
                                recyclerView.loadMoreComplete();
                            }

                            @Override
                            public void finish() {

                            }
                        });


            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                swipeRefreshLayout.setRefreshing(true);
                getIndex();
            }
        });



    }

    @Override
    protected void initData() {
        curType = getIntent().getIntExtra("type", 0);
        String title = getIntent().getStringExtra("title");
        txtTitle.setText(title);
        getIndex();
    }

    private void getIndex() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("curriculum", String.valueOf(2));
        map.put("type", String.valueOf(curType));
        map.put("page", page + "");
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
        swipeRefreshLayout.setRefreshing(false);
    }
}
