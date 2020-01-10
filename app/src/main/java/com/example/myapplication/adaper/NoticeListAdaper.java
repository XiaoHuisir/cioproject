package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.NoticeListBean;
import com.example.myapplication.bean.TypeIndexBean;
import com.example.myapplication.utils.DateUtil;

import java.util.List;

public class NoticeListAdaper extends BaseAdapter {

    NoticeItemClick itemClick;

    public NoticeListAdaper(List<NoticeListBean.DataBean> mDatas, NoticeItemClick itemClick) {
        super(mDatas);
        this.itemClick = itemClick;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.notice_list;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        TextView terrace = (TextView) holder.getView(R.id.txt_terrace);
        TextView txtmatter = (TextView) holder.getView(R.id.txt_matter);
        TextView txtnotice = (TextView) holder.getView(R.id.txt_notice);
        TextView txt_times = (TextView) holder.getView(R.id.txt_time);
        NoticeListBean.DataBean data = (NoticeListBean.DataBean) mDatas.get(positon);
        txtmatter.setText(data.getContent());
        terrace.setText(data.getTitle());
//        formatTime2String
        long  tiem =(long)data.getAdd_time();
        String ago = DateUtil.formatTime2String(tiem);
        txt_times.setText(ago);
        holder.itemView.setTag(data);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NoticeListBean.DataBean title = (NoticeListBean.DataBean) v.getTag();
                if (itemClick != null) {
                    itemClick.noticeClick(title);
                }
            }
        });

    }

    public interface NoticeItemClick {
        void noticeClick(NoticeListBean.DataBean title);
    }

}
