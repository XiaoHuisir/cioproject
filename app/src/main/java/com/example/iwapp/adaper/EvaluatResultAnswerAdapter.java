package com.example.iwapp.adaper;

import android.graphics.Color;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.EvaluatShowResultBean;

import java.util.List;

public class EvaluatResultAnswerAdapter extends BaseAdapter {
    private String[] answers = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};

    public EvaluatResultAnswerAdapter(List mDatas){
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_item_exer_result;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        EvaluatShowResultBean.DataBean.EvaluatResultBean.OptionBean bean = (EvaluatShowResultBean.DataBean.EvaluatResultBean.OptionBean) mDatas.get(positon);
        TextView txt_select = (TextView) holder.getView(R.id.txt_select);
        TextView txt_answer = (TextView) holder.getView(R.id.txt_answer);
        txt_select.setText(answers[positon]);
        if("0".equals(bean.getIs_select())){
            txt_select.setBackgroundResource(R.drawable.exer_normal);
            txt_select.setTextColor(Color.parseColor("#000000"));
        }else if("1".equals(bean.getIs_select())){
            txt_select.setBackgroundResource(R.drawable.exer_select);
            txt_select.setTextColor(Color.parseColor("#ffffff"));
        }
        txt_answer.setText(bean.getTitle());
    }
}
