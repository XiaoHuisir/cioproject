package com.example.myapplication.interfaces.contract;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.NoticeListBean;

import java.util.List;

public class NoticeListAdaper extends BaseAdapter {
    public NoticeListAdaper(List<NoticeListBean.DataBean> mDatas) {
        super(mDatas);
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
        NoticeListBean.DataBean data = (NoticeListBean.DataBean) mDatas.get(positon);
        txtmatter.setText(data.getContent());
        terrace.setText(data.getTitle());

    }


}
