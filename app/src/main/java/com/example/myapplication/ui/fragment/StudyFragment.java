package com.example.myapplication.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adaper.IndexAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.IndexPresenter;
import com.example.myapplication.ui.acivity.video.VideoActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class StudyFragment extends BaseFragment implements IndexConstract.View ,IndexAdapter.IndexItemClick{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.banner_learn)
    Banner banners;

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
        indexAdapter.itemClick = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(indexAdapter);

        getIndex();
    }

    private void getIndex() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("curriculum", String.valueOf(1));
        map.put("type", "");
        map.put("page", "1");
        ((IndexPresenter) mPresenter).getIndex(map);





    }




    @Override
    public void getIndexReturn(IndexBean result) {
        list.clear();
        list.addAll(result.getData().getCurriculum_data());
        setBanner(result);
        indexAdapter.notifyDataSetChanged();
    }

    private void setBanner(IndexBean result) {
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
     * @param result
     */
    public void showSearch(List<IndexBean.DataBean.CurriculumDataBean> result){
        list.clear();
        list.addAll(result);
        indexAdapter.notifyDataSetChanged();
    }
}
