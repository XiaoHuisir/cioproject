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
    public MyfilelistAdapter(List<MyfilelistBean.DataBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.myfilelist_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        MyfilelistBean.DataBean data= (MyfilelistBean.DataBean) mDatas.get(positon);

        ImageView imageMyfile = (ImageView) holder.getView(R.id.image_myfile);
        TextView txtFilename = (TextView) holder.getView(R.id.txt_file_name);
        TextView txtTitle = (TextView) holder.getView(R.id.txt_title);

       txtFilename.setText(data.getFile_name());
       txtTitle.setText(data.getTitle());

    }


}
