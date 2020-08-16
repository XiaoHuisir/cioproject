package com.example.iwapp.adaper;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.TypeIndexBean;

import java.util.List;

public class    StudyAdapter extends BaseAdapter {

    StudyItemClick itemClick;

    public StudyAdapter(List<TypeIndexBean.DataBean.CurriculumKwBean> list,StudyItemClick itemClick){
        super(list);
        this.itemClick = itemClick;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_type_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        TypeIndexBean.DataBean.CurriculumKwBean bean = (TypeIndexBean.DataBean.CurriculumKwBean) mDatas.get(positon);
        View view = holder.getView(R.id.line);
        TextView txt_type = (TextView) holder.getView(R.id.txt_type);
        if(bean.select){
            view.setVisibility(View.VISIBLE);
            txt_type.setTextColor(Color.parseColor("#ff0000"));
        }else{
            view.setVisibility(View.GONE);
            txt_type.setTextColor(Color.parseColor("#FF333333"));
        }

        txt_type.setText(bean.getTitle());
        holder.itemView.setTag(positon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                if(itemClick != null){
                    int id = ((TypeIndexBean.DataBean.CurriculumKwBean)mDatas.get(pos)).getId();
                    itemClick.studyClick(id);
                }
            }
        });

    }

    public interface StudyItemClick{
        void studyClick(int id);
    }

}
