package com.example.iwapp.adaper;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.MyfilelistBean;
import com.example.iwapp.ui.acivity.video.VideoActivity;

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
        final MyfilelistBean.DataBean data = (MyfilelistBean.DataBean) mDatas.get(positon);

        ImageView imageMyfile = (ImageView) holder.getView(R.id.image_myfile);
        TextView txtFilename = (TextView) holder.getView(R.id.txt_file_name);
        TextView txtTitle = (TextView) holder.getView(R.id.txt_title);

        txtFilename.setText(data.getFile_name());
        txtTitle.setText(data.getTitle());


        holder.itemView.setTag(data);
        txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, VideoActivity.class);
                intent.putExtra("curriulum_id", data.getCurriculum_id() + "");
                mContext.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyfilelistBean.DataBean datas = (MyfilelistBean.DataBean) v.getTag();
                if (itemClick != null) {
                    itemClick.myfilelistClick(datas);
                }
            }
        });

    }


    public interface MyfilelistClick {
        void myfilelistClick(MyfilelistBean.DataBean datas);


    }


}
