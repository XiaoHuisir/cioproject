package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.CurriculumBean;

import java.util.List;

public class PdfAdapter extends BaseAdapter {

    public PdfAdapter(List<CurriculumBean.DataBean.FileDataBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_pdf_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CurriculumBean.DataBean.FileDataBean bean = (CurriculumBean.DataBean.FileDataBean) mDatas.get(positon);
        ImageView imgIcon = (ImageView) holder.getView(R.id.img_icon);
        TextView txtName = (TextView) holder.getView(R.id.txt_name);
        txtName.setText(bean.getName());
    }
}
