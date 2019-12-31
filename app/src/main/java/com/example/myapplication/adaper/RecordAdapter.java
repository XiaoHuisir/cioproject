package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.UserCenterBean;

import java.util.List;

public class RecordAdapter extends BaseAdapter {
    public RecordAdapter(List <UserCenterBean.DataBean.HistoryBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.record_list;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        UserCenterBean.DataBean.HistoryBean data = (UserCenterBean.DataBean.HistoryBean) mDatas.get(positon);
        TextView titles = (TextView) holder.getView(R.id.txt_titles);
        TextView times = (TextView) holder.getView(R.id.txt_times);
        ImageView imgicon = (ImageView) holder.getView(R.id.img_icon);
        Glide.with(mContext).load(data.getLog())
                .transform(new RoundedCorners(5))
                .into(imgicon);
        times.setText(data.getLen());
        titles.setText(data.getTitle());
    }


}
