package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.NoticeListBean;
import com.example.myapplication.bean.TypeIndexBean;

import java.util.List;

public class NoticeListAdaper extends BaseAdapter {

    NoticeItemClick itemClick;
    public NoticeListAdaper(List<NoticeListBean.DataBean> mDatas,NoticeItemClick itemClick) {
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
        NoticeListBean.DataBean data = (NoticeListBean.DataBean) mDatas.get(positon);
        txtmatter.setText(data.getContent());
        terrace.setText(data.getTitle());

        holder.itemView.setTag(data.getContent());
        holder.itemView.setTag(data.getAdd_time());
        holder.itemView.setTag(data.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = (String) v.getTag();
                String addtime = (String) v.getTag();
                String title = (String) v.getTag();
                if(itemClick != null){
                    itemClick.noticeClick(content,addtime,title);
                }
            }
        });


    }
    public interface NoticeItemClick{
        void noticeClick(String content,String  addtime,String title);
    }

}
