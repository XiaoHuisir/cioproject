package com.example.iwapp.adaper;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.ToadayBean;
import com.example.iwapp.utils.DateUtil;

import java.util.List;

public class DaAgoAdapter extends BaseAdapter {
    public  TrainItemClick  itemClick;
    public DaAgoAdapter( List<ToadayBean.DataBean.DayAgoBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dago_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ImageView ivtoday = (ImageView) holder.getView(R.id.iv_todaytrain);
        TextView textdata = (TextView) holder.getView(R.id.text_datatrain);
        TextView textname = (TextView) holder.getView(R.id.text_nametrain);
        TextView textgs = (TextView) holder.getView(R.id.text_gstrain);
        TextView text_watchtime = (TextView) holder.getView(R.id.text_watchtimetrain);
        TextView text_timetrain = (TextView) holder.getView(R.id.text_timetrain);
        ToadayBean.DataBean.DayAgoBean data = (ToadayBean.DataBean.DayAgoBean) mDatas.get(positon);
        Glide.with(mContext).load(data.getLog()).into(ivtoday);
        textdata.setText(data.getTitle());
        textname.setText(data.getTeacher());
        textgs.setText(data.getGs());
        int type = data.getType();
        switch (type) {
            case 1:
                text_timetrain.setText("课外学习");
                break;
            case 2:
                text_timetrain.setText("培训课程");
                break;
            case 3:
                break;
        }
        long len = (long)data.getLen();
        String lens = DateUtil.formatTimeS(len);
        text_watchtime.setText("观看至"+lens);


        ConstraintLayout layout = (ConstraintLayout) holder.getView(R.id.constraint_daytrain);
        layout.setTag(String.valueOf(data.getCurriculum_id()));
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String) v.getTag();
                if (itemClick != null) {
                    itemClick.trainclick(id);
                }
            }
        });
    }
    public interface TrainItemClick {
        void trainclick(String id);
    }

}
