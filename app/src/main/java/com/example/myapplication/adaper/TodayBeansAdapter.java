package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.ToadayBean;

import java.util.List;

public class TodayBeansAdapter extends BaseAdapter {
    public TodayBeansItemClick itemClick;
    public TodayBeansAdapter(List<ToadayBean.DataBean.TodayBean>  mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.todaybeans_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ImageView ivtoday = (ImageView) holder.getView(R.id.iv_todaybean);
        TextView textdata = (TextView) holder.getView(R.id.text_databeans);
        TextView textname = (TextView) holder.getView(R.id.text_namebeans);
        TextView textgs = (TextView) holder.getView(R.id.text_gsbeans);
        TextView text_watchtime = (TextView) holder.getView(R.id.text_watchtimebeans);
        ToadayBean.DataBean.TodayBean data = (ToadayBean.DataBean.TodayBean) mDatas.get(positon);


        Glide.with(mContext).load(data.getLog()).into(ivtoday);
        textdata.setText(data.getTitle());
        textname.setText(data.getTeacher());
        textgs.setText(data.getGs());
        text_watchtime.setText(String.valueOf(data.getUpdate_time()));
        ConstraintLayout layout = (ConstraintLayout) holder.getView(R.id.constraint_todaybeans);
        layout.setTag(String.valueOf(data.getCurriculum_id()));
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String) v.getTag();
                if (itemClick != null) {
                    itemClick.todaybeansclick(id);
                }
            }
        });
    }
    public interface TodayBeansItemClick {
        void todaybeansclick(String id);
    }



}
