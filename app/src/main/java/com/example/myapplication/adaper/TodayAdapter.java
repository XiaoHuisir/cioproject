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
import com.example.myapplication.bean.PorfolioBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.utils.DateUtil;

import java.util.List;

public class TodayAdapter extends BaseAdapter {
    public  TodayItemClick  itemClick;
    public TodayAdapter(List<ToadayBean.DataBean.SevenDayBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.today_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ImageView ivtoday = (ImageView) holder.getView(R.id.iv_today);
        TextView textdata = (TextView) holder.getView(R.id.text_data);
        TextView textname = (TextView) holder.getView(R.id.text_name);
        TextView textgs = (TextView) holder.getView(R.id.text_gs);
        TextView text_watchtime = (TextView) holder.getView(R.id.text_watchtime);
        ToadayBean.DataBean.SevenDayBean data = (ToadayBean.DataBean.SevenDayBean) mDatas.get(positon);
        Glide.with(mContext).load(data.getLog()).into(ivtoday);
        textdata.setText(data.getTitle());
        textname.setText(data.getTeacher());
        textgs.setText(data.getGs());
        text_watchtime.setText(String.valueOf(data.getUpdate_time()));
        long len = (long)data.getLen();
        String lens = DateUtil.formatTimeS(len);

        text_watchtime.setText("观看至"+lens);
        ConstraintLayout layout = (ConstraintLayout) holder.getView(R.id.constraint_day);
        layout.setTag(String.valueOf(data.getCurriculum_id()));
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String) v.getTag();
                if (itemClick != null) {
                    itemClick.click(id);
                }
            }
        });
    }
    public interface TodayItemClick {
        void click(String id);
    }

}
