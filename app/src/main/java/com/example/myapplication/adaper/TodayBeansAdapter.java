package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.utils.DateUtil;

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
        ToadayBean.DataBean.TodayBean data = (ToadayBean.DataBean.TodayBean) mDatas.get(positon);
        ImageView ivtoday = (ImageView) holder.getView(R.id.iv_todaybean);
        TextView textdata = (TextView) holder.getView(R.id.text_databeans);
        TextView textname = (TextView) holder.getView(R.id.text_namebeans);
        TextView textgs = (TextView) holder.getView(R.id.text_gsbeans);
        TextView text_watchtime = (TextView) holder.getView(R.id.text_watchtimebeans);
//        String userDate = DateUtil.getUserDate(String.valueOf(data.getLen()/60));

        long len = (long)data.getLen();
//        String lens = DateUtil.formatTimeS(len);
//        formatTimeS
        String lens = DateUtil.stampToDate(String.valueOf(data.getLen()));
//        String s1 = DateUtil.stampToDate(String.valueOf(data.getLen()));
        String s = DateUtil.formatTimeS(len);
        text_watchtime.setText("观看至"+s);
        TextView text_timebeans = (TextView) holder.getView(R.id.text_timebeans);
        int type = data.getType();
        switch (type) {
            case 1:
                text_timebeans.setText("课外学习");
                break;
            case 2:
                text_timebeans.setText("培训课程");
                break;
            case 3:
                break;
        }
        Glide.with(mContext).load(data.getLog()).into(ivtoday);
        textdata.setText(data.getTitle());
        textname.setText(data.getTeacher());
        textgs.setText(data.getGs());


//        text_watchtime.setText(String.valueOf(data.getUpdate_time()));
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
