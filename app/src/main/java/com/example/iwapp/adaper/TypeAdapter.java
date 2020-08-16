package com.example.iwapp.adaper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.TypeIndexBean;

import java.util.List;

public class TypeAdapter extends BaseAdapter {

    TypeItemClick itemClick;

    public TypeAdapter(List<TypeIndexBean.DataBean.CurriculumDataBean> list, TypeItemClick itemClick) {
        super(list);
        this.itemClick = itemClick;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_index_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        TypeIndexBean.DataBean.CurriculumDataBean bean = (TypeIndexBean.DataBean.CurriculumDataBean) mDatas.get(positon);
        ImageView img_head = (ImageView) holder.getView(R.id.img_head);
        TextView txt_title = (TextView) holder.getView(R.id.txt_title);
        TextView txt_name = (TextView) holder.getView(R.id.txt_name);
        TextView txt_work = (TextView) holder.getView(R.id.txt_work);
        TextView txt_study = (TextView) holder.getView(R.id.txt_study);
        Glide.with(mContext).load(bean.getLog()).apply(new RequestOptions().transform(new RoundedCorners(5)))
                .into(img_head);
        txt_title.setText(bean.getTitle());
        txt_name.setText(bean.getTeacher());
        txt_work.setText(bean.getGs());
        float num = Float.parseFloat(bean.getJd());
        String str = "";
        if (num > 0) {
            str = "已学习：" + (int) (num * 100) + "%  >";
        } else {
            str = "未学习  >";
        }
        txt_study.setText(str);

        holder.itemView.setTag(bean.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();
                if (itemClick != null) {
                    itemClick.typeClick(id);
                }
            }
        });
    }

    public interface TypeItemClick {
        void typeClick(int id);
    }

}
