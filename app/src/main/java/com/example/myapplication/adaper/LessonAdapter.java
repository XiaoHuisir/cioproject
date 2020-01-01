package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.ToadayBean;

import java.util.List;

public class LessonAdapter extends BaseAdapter {
    public LessonAdapter(List<ToadayBean.DataBean.SevenDayBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.lesson_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ImageView ivtoday = (ImageView) holder.getView(R.id.iv_lesson);
        TextView textdata = (TextView) holder.getView(R.id.text_data1);
        TextView textname = (TextView) holder.getView(R.id.text_name1);
        TextView textgs = (TextView) holder.getView(R.id.text_gs1);
        TextView text_watchtime = (TextView) holder.getView(R.id.text_watchtime1);
        ToadayBean.DataBean.SevenDayBean data = (ToadayBean.DataBean.SevenDayBean) mDatas.get(positon);
        Glide.with(mContext).load(data.getLog()).into(ivtoday);
        textdata.setText(data.getTitle());
        textname.setText(data.getTeacher());
        textgs.setText(data.getGs());
        text_watchtime.setText(String.valueOf(data.getUpdate_time()));

    }


}
