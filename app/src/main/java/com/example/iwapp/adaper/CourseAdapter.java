package com.example.iwapp.adaper;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.TypeIndexBean;

import java.util.List;

public class CourseAdapter extends BaseAdapter {

    CourseItemClick itemClick;
    public CourseAdapter(List<TypeIndexBean.DataBean.CurriculumPxBean> mData,CourseItemClick itemClick){
        super(mData);
        this.itemClick = itemClick;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_type_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        TypeIndexBean.DataBean.CurriculumPxBean bean = (TypeIndexBean.DataBean.CurriculumPxBean) mDatas.get(positon);
        View view = holder.getView(R.id.line);
        TextView txt_type = (TextView) holder.getView(R.id.txt_type);
        txt_type.setText(bean.getTitle());
        if(bean.select){
            view.setVisibility(View.VISIBLE);
            txt_type.setTextColor(Color.parseColor("#ff0000"));
        }else{
            view.setVisibility(View.GONE);
            txt_type.setTextColor(Color.parseColor("#FF333333"));
        }
        holder.itemView.setTag(positon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                if(itemClick != null){
                    int id = ((TypeIndexBean.DataBean.CurriculumPxBean)mDatas.get(pos)).getId();
                    itemClick.courseClick(id);
                }
            }
        });
    }

    public interface CourseItemClick{
        void courseClick(int id);
    }
}
