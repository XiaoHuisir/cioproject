package com.example.myapplication.adaper;

import android.graphics.Color;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.ExercisesBean;

import java.util.List;

public class ExercisesAdapter extends BaseAdapter {

    private String[] answers = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};

    public ExercisesAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_item_exer;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ExercisesBean.DataBean.OptionBean bean  = (ExercisesBean.DataBean.OptionBean) mDatas.get(positon);
        TextView txt_select = (TextView) holder.getView(R.id.txt_select);
        TextView txt_answer = (TextView) holder.getView(R.id.txt_answer);
        if(positon < answers.length){
            txt_select.setText(answers[positon]);
            if(bean.select){
                txt_select.setBackgroundResource(R.drawable.exer_select);
                txt_select.setTextColor(Color.parseColor("#ffffff"));
            }else{
                txt_select.setBackgroundResource(R.drawable.exer_normal);
                txt_select.setTextColor(Color.parseColor("#000000"));
            }
        }
        txt_answer.setText(bean.getTitle());
    }
}
