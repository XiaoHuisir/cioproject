package com.example.iwapp.adaper;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.UserCenterBean;
import com.example.iwapp.utils.DateUtil;

import java.util.List;

public class RecordAdapter extends BaseAdapter {
    public RecordItemClick  itemClick;
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
        ImageView imgicon = (ImageView) holder.getView(R.id.img_icons);
        Glide.with(mContext).load(data.getLog())
                .apply(new RequestOptions().transform(new RoundedCorners(5)))
                .into(imgicon);
        String userDate = DateUtil.getUserDate(String.valueOf(data.getLen()/60));

        long len = (long)data.getLen();
        String lens = DateUtil.formatTimeS(len);

        times.setText("观看至"+lens);
        titles.setText(data.getTitle());



        LinearLayout layout = (LinearLayout) holder.getView(R.id.record_constraint);
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

    public interface RecordItemClick {
        void click(String id);
    }

}
