package com.example.myapplication.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adaper.IndexAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.IndexPresenter;
import com.example.myapplication.ui.acivity.course.CourseActivity;
import com.example.myapplication.ui.acivity.video.VideoActivity;
import com.example.myapplication.utils.NetDownResponse;
import com.example.myapplication.utils.NetRequsetUtil;
import com.example.myapplication.widgets.DrawableCenterTextView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
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
import butterknife.OnClick;

public class CourseFragment extends BaseFragment implements IndexConstract.View, IndexAdapter.IndexItemClick {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    int curType = 0;
    @BindView(R.id.txt_type_1)
    DrawableCenterTextView txtType1;
    @BindView(R.id.txt_type_2)
    DrawableCenterTextView txtType2;
    @BindView(R.id.txt_type_3)
    DrawableCenterTextView txtType3;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.relative_ban)
    RelativeLayout relative_ban;
    @BindView(R.id.sv)
    NestedScrollView sv;

    int page = 1;
    IndexAdapter indexAdapter;
    List<IndexBean.DataBean.CurriculumDataBean> list;
    private String image;

    @Override
    protected IBasePresenter getPresenter() {
        return new IndexPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        indexAdapter = new IndexAdapter(list);
        indexAdapter.itemClick = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(indexAdapter);
        relative_ban.setVisibility(View.INVISIBLE);
        /*curType = Constant.STUDY_TYPE_1;
        resetTypeTxt();
        txtType1.setTextColor(getResources().getColor(R.color.red));*/
        getIndex();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                                    }else {
                                        Toast.makeText(CourseFragment.this.getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.txt_type_1, R.id.txt_type_2, R.id.txt_type_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_type_1:
                curType = Constant.STUDY_TYPE_1;
                resetTypeTxt();
                txtType1.setTextColor(getResources().getColor(R.color.red));
                openCourseActivity(curType, "智慧课堂");
                break;
            case R.id.txt_type_2:
                curType = Constant.STUDY_TYPE_2;
                resetTypeTxt();
                txtType2.setTextColor(getResources().getColor(R.color.red));
                openCourseActivity(curType, "内部培训");
                break;
            case R.id.txt_type_3:
                curType = Constant.STUDY_TYPE_3;
                resetTypeTxt();
                txtType3.setTextColor(getResources().getColor(R.color.red));
                openCourseActivity(curType, "其他培训");
                break;
        }

    }

    private void getIndex() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("curriculum", String.valueOf(2));
        map.put("type", String.valueOf(curType));
        map.put("page", String.valueOf(page));
        ((IndexPresenter) mPresenter).getIndex(map);
    }

    @Override
    public void getIndexReturn(IndexBean result) {
        banners(result);
        list.clear();
        list.addAll(result.getData().getCurriculum_data());
        indexAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

        if (result.getData().getLb_data() == null || result.getData().getLb_data() .size() == 0){
            relative_ban.setVisibility(View.GONE);
        }else {
            relative_ban.setVisibility(View.VISIBLE);
        }

    }

    private void openCourseActivity(int type, String title) {
        Intent intent = new Intent(context, CourseActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    private void banners(final IndexBean result) {
        List<IndexBean.DataBean.LbDataBean> lb_data = result.getData().getLb_data();
//        result.getData().getLb_data().get(0).getId()
        if (lb_data.size() > 0) {
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < lb_data.size(); i++) {
                image = lb_data.get(i).getImage();
                strings.add(image);
            }
            if (image!=null){
            relative_ban.setVisibility(View.VISIBLE);
            banner.setImages(strings)
                    .setImageLoader(new MyLoader())
                    .setDelayTime(2000)
                    .isAutoPlay(true)
                    .setIndicatorGravity(BannerConfig.CENTER)
                    .setBannerAnimation(Transformer.Accordion).start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                int curriculum_id = result.getData().getLb_data().get(position).getCurriculum_id();
                Intent intent = new Intent();
                intent.setClass(context, VideoActivity.class);
                intent.putExtra("curriulum_id", String.valueOf(curriculum_id));
                startActivity(intent);
            }
        });
            }else {
                relative_ban.setVisibility(View.INVISIBLE);
            }

        }else {
            relative_ban.setVisibility(View.INVISIBLE);
        }

    }

    class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            String loads = (String) path;
            Glide.with(context).load(loads).into(imageView);
        }
    }

    private void resetTypeTxt() {
        txtType1.setTextColor(getResources().getColor(R.color.black));
        txtType2.setTextColor(getResources().getColor(R.color.black));
        txtType3.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void click(String id) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", id);
        startActivity(intent);
    }
}
