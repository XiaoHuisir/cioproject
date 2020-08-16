package com.example.iwapp.adaper;

import android.view.View;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.NoticeListBean;
import com.example.iwapp.utils.DateUtil;

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
        TextView txtTime = (TextView) holder.getView(R.id.txt_time);
        NoticeListBean.DataBean data = (NoticeListBean.DataBean) mDatas.get(positon);
        txtmatter.setText(data.getContent());
        terrace.setText(data.getTitle());
        long  ago =(long)data.getAdd_time();
        String agotime = DateUtil.formatTime2String(ago);
        String s = DateUtil.stampToDate(String.valueOf(data.getAdd_time()));
                txtTime.setText(s);
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
