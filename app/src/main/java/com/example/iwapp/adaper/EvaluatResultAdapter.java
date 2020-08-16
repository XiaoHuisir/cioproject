package com.example.iwapp.adaper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.EvaluatShowResultBean;

import java.util.List;

public class EvaluatResultAdapter extends BaseAdapter {

    public EvaluatResultAdapter(List mDatas){
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_evaluat_result_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        EvaluatShowResultBean.DataBean.EvaluatResultBean bean = (EvaluatShowResultBean.DataBean.EvaluatResultBean) mDatas.get(positon);

        ImageView imgStatus = (ImageView) holder.getView(R.id.img_status);
        if(bean.getSuccess() == 1){
            imgStatus.setImageResource(R.mipmap.test_right);
        }else{
            imgStatus.setImageResource(R.mipmap.test_error);
        }

        TextView txtTitle = (TextView) holder.getView(R.id.txt_title);
        String str = positon+". "+bean.getTitle()+"<font color='red'>("+bean.getFraction()+"分)</font>";
        txtTitle.setText(Html.fromHtml(str));

        //显示结果
        RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.recyclerView_result);
        EvaluatResultAnswerAdapter adapter;
        if(recyclerView.getTag() == null){
            adapter = new EvaluatResultAnswerAdapter(bean.getOption());
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);
            recyclerView.setTag(adapter);
        }else{
            adapter = (EvaluatResultAnswerAdapter) recyclerView.getTag();
            adapter.addData(bean.getOption());
            adapter.notifyDataSetChanged();
        }
    }
}
