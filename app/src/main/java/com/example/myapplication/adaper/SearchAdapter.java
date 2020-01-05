package com.example.myapplication.adaper;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.SearchBean;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    public SearchAdapter(List mDatas){
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_item_index;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        SearchBean.DataBean.CurriculumDataBean bean = (SearchBean.DataBean.CurriculumDataBean) mDatas.get(positon);
        TextView txt_title = (TextView) holder.getView(R.id.txt_title);
        txt_title.setText(bean.getTitle());
        ImageView img_icon = (ImageView) holder.getView(R.id.img_icon);
        Glide.with(mContext).load(bean.getLog())
                .apply(new RequestOptions().transform(new RoundedCorners(5)))
                .into(img_icon);

        TextView txt_name = (TextView) holder.getView(R.id.txt_name);
        txt_name.setText(bean.getTeacher());
        TextView txt_work = (TextView) holder.getView(R.id.txt_work);
        txt_work.setText(bean.getGs());

        TextView txt_study_state = (TextView) holder.getView(R.id.txt_study_state);
        String progress = ((int) (Float.valueOf(bean.getJd()) * 100)) + "%";
        txt_study_state.setText(progress);

        ConstraintLayout layout = (ConstraintLayout) holder.getView(R.id.layout_item);
        layout.setTag(String.valueOf(bean.getId()));
    }
}
