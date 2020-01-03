package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.MyfilelistBean;

import java.util.List;

public class MyfilelistAdapter extends BaseAdapter {

    MyfilelistClick itemClick;

    public MyfilelistAdapter(List<MyfilelistBean.DataBean> mDatas, MyfilelistClick itemClick) {
        super(mDatas);
        this.itemClick = itemClick;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.myfilelist_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        MyfilelistBean.DataBean data = (MyfilelistBean.DataBean) mDatas.get(positon);

        ImageView imageMyfile = (ImageView) holder.getView(R.id.image_myfile);
        TextView txtFilename = (TextView) holder.getView(R.id.txt_file_name);
        TextView txtTitle = (TextView) holder.getView(R.id.txt_title);

        txtFilename.setText(data.getFile_name());
        txtTitle.setText(data.getTitle());


        holder.itemView.setTag(String.valueOf(data.getCurriculum_id()));
        holder.itemView.setTag(data.getFile_name());
        holder.itemView.setTag(data.getFile_url());
        holder.itemView.setTag(data.getFile_size());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getCurriculum_id = (String) v.getTag();
                String filename = (String) v.getTag();
                String fileurl = (String) v.getTag();
                String filesize = (String) v.getTag();
                if (itemClick != null) {
                    itemClick.myfilelistClick(getCurriculum_id, filename, fileurl, filesize);
                }
            }
        });
    }


    public interface MyfilelistClick {
        void myfilelistClick(String getCurriculum_id, String filename, String fileurl, String filesize);


    }


}
