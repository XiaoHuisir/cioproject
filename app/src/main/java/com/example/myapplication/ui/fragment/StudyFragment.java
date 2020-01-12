package com.example.myapplication.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adaper.IndexAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.IndexPresenter;
import com.example.myapplication.ui.acivity.video.VideoActivity;
import com.example.myapplication.utils.NetDownResponse;
import com.example.myapplication.utils.NetRequsetUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class StudyFragment extends BaseFragment implements IndexConstract.View, IndexAdapter.IndexItemClick {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.banner_learn)
    Banner banners;
    @BindView(R.id.swipeRefresh_study)
    SwipeRefreshLayout swipeRefreshStudy;
    @BindView(R.id.nv)
    NestedScrollView sv;

    int page = 1;

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
        indexAdapter.itemClick = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(indexAdapter);
        getIndex();
        swipeRefreshStudy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getIndex();
            }
        });

        sv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {

                }
                if (scrollY < oldScrollY) {

                }

                if (scrollY == 0) {

                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    page = page + 1;


                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("curriculum", String.valueOf(1));
                    jsonObject.put("type", "");
                    jsonObject.put("page", page + "");

                    NetRequsetUtil.getInstance().netRequestPostJson("index/train/index",
                            jsonObject.toString(), new NetDownResponse() {
                                @Override
                                public void success(String str) {
                                    IndexBean indexBean = JSON.parseObject(str, IndexBean.class);
                                    List<IndexBean.DataBean.CurriculumDataBean> curriculum_data = indexBean.getData().getCurriculum_data();
                                    if (curriculum_data.size() > 0) {
                                        for (IndexBean.DataBean.CurriculumDataBean curriculum_datum : curriculum_data) {
                                            list.add(curriculum_datum);
                                            indexAdapter.notifyDataSetChanged();
                                        }
                                    } else {
                                        try {
                                            Toast.makeText(StudyFragment.this.getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                                        }catch (Exception e){

                                        }
                                    }
                                }

                                @Override
                                public void errowithresponse(String str) {
                                }

                                @Override
                                public void erro() {
                                }

                                @Override
                                public void finish() {

                                }
                            });
                }
            }

        });

    }

    private void getIndex() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("curriculum", String.valueOf(1));
        map.put("type", "");
        map.put("page", String.valueOf(page));
        ((IndexPresenter) mPresenter).getIndex(map);


    }


    @Override
    public void getIndexReturn(IndexBean result) {
        setBanner(result);
        list.clear();
        list.addAll(result.getData().getCurriculum_data());
        indexAdapter.notifyDataSetChanged();
        swipeRefreshStudy.setRefreshing(false);

        if (result.getData().getLb_data() == null || result.getData().getLb_data().size() == 0) {
            banners.setVisibility(View.GONE);
        } else {
            banners.setVisibility(View.VISIBLE);
        }

    }

    private void setBanner(final IndexBean result) {
        List<IndexBean.DataBean.LbDataBean> lb_data = result.getData().getLb_data();
        if (lb_data.size() > 0) {
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < lb_data.size(); i++) {
                String image = lb_data.get(i).getImage();
                strings.add(image);
            }
            banners.setImages(strings)
                    .setImageLoader(new MyLoader())
                    .setDelayTime(2000)
                    .isAutoPlay(true)
                    .setIndicatorGravity(BannerConfig.CENTER)
                    .setBannerAnimation(Transformer.Accordion).start();

            banners.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    int curriculum_id1 = result.getData().getLb_data().get(position).getCurriculum_id();
                    Intent intent = new Intent();
                    intent.setClass(context, VideoActivity.class);
                    intent.putExtra("curriulum_id", String.valueOf(curriculum_id1));
                    startActivity(intent);
                }
            });
        }

    }

    class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            String loads = (String) path;
            Glide.with(context).load(loads).into(imageView);
        }
    }

    @Override
    public void click(String id) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", id);
        startActivity(intent);
    }

    /**
     * 显示搜索结果
     *
     * @param result
     */
    public void showSearch(List<IndexBean.DataBean.CurriculumDataBean> result) {
        list.clear();
        list.addAll(result);
        indexAdapter.notifyDataSetChanged();
    }
}
