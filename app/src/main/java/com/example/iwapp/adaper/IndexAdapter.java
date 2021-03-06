package com.example.iwapp.adaper;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.IndexBean;
import com.example.iwapp.utils.CircleBarView;

import java.util.List;

public class IndexAdapter extends BaseAdapter {

    public IndexItemClick itemClick;

    public IndexAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_item_index;
    }

    @Override
    protected void bindData(BaseAdapter.BaseViewHolder holder, int positon, Object o) {
        IndexBean.DataBean.CurriculumDataBean bean = (IndexBean.DataBean.CurriculumDataBean) mDatas.get(positon);
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
        String baifen = ((int) (Float.valueOf("1") * 100)) + "%";
        String zero = ((int) (Float.valueOf("0") * 100)) + "%";
        if (!progress.equals(baifen) && !progress.equals(zero)) {
            txt_study_state.setText(progress);
        }


        if (progress.equals(baifen)) {
            txt_study_state.setText("完成学时");

            txt_study_state.setTextColor(Color.parseColor("#FFD7AB70"));

        }
        if (progress.equals(zero)) {
            txt_study_state.setText("未学习");
            txt_study_state.setTextColor(Color.parseColor("#FFE1E3ED"));

        }


        CircleBarView circleImageView = (CircleBarView) holder.getView(R.id.img_persent);

        circleImageView.setMaxNum(100f);
        circleImageView.setProgressNum(Float.valueOf(bean.getJd()) * 100, 0);


        ConstraintLayout layout = (ConstraintLayout) holder.getView(R.id.layout_item);
        layout.setTag(String.valueOf(bean.getId()));
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


    public interface IndexItemClick {
        void click(String id);
    }

}
