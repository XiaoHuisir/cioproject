package com.example.myapplication.adaper;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.EvaluatShowResultBean;
import com.example.myapplication.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by louyulin on 2020/1/12.
 */

public class ExerciseDetailAdapter extends RecyclerView.Adapter {

    List<EvaluatShowResultBean.DataBean.EvaluatResultBean> mList = new ArrayList<>();
    EvaluatShowResultBean.DataBean data;
    Context context;

    public ExerciseDetailAdapter(List<EvaluatShowResultBean.DataBean.EvaluatResultBean> mList, EvaluatShowResultBean.DataBean data, Context context) {
        this.mList = mList;
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.exercise_header, viewGroup, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_evaluat_result_item, viewGroup, false);
            return new ViewHolderContent(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolderContent) {
            if(mList.size() != 0 ){
                ((ViewHolderContent) viewHolder).setData(mList.get(i - 1),i);
            }
        } else if(viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).setData(data);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public class ViewHolderContent extends RecyclerView.ViewHolder {
        @BindView(R.id.img_status)
        ImageView imgStatus;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.recyclerView_result)
        RecyclerView recyclerViewResult;

        ViewHolderContent(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(EvaluatShowResultBean.DataBean.EvaluatResultBean bean,int positon) {


            if (bean.getSuccess() == 1) {
                imgStatus.setImageResource(R.mipmap.test_right);
            } else {
                imgStatus.setImageResource(R.mipmap.test_error);
            }

            String str = positon + ". " + bean.getTitle() + "<font color='red'>(" + bean.getFraction() + "分)</font>";
            txtTitle.setText(Html.fromHtml(str));

            //显示结果
            EvaluatResultAnswerAdapter adapter;
            if (recyclerViewResult.getTag() == null) {
                adapter = new EvaluatResultAnswerAdapter(bean.getOption());
                recyclerViewResult.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewResult.setAdapter(adapter);
                recyclerViewResult.setTag(adapter);
            } else {
                adapter = (EvaluatResultAnswerAdapter) recyclerViewResult.getTag();
                adapter.addData(bean.getOption());
                adapter.notifyDataSetChanged();
            }

        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_result)
        TextView txtResult;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_score)
        TextView txtScore;
        @BindView(R.id.layout_success)
        ConstraintLayout layoutSuccess;
        @BindView(R.id.txt_tips)
        TextView txtTips;
        @BindView(R.id.txt_exercise_title)
        TextView txtExerciseTitle;
        @BindView(R.id.txt_exercise_num)
        TextView txtExerciseNum;
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.txt_right_num)
        TextView txtRightNum;
        @BindView(R.id.layout_right)
        ConstraintLayout layoutRight;
        @BindView(R.id.txt_error_num)
        TextView txtErrorNum;
        @BindView(R.id.layout_error)
        ConstraintLayout layoutError;
        @BindView(R.id.txt_rate)
        TextView txtRate;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.layout_rate)
        ConstraintLayout layoutRate;
        @BindView(R.id.layout_info)
        ConstraintLayout layoutInfo;
        @BindView(R.id.layout_lose)
        RelativeLayout layoutLose;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(EvaluatShowResultBean.DataBean data) {

            if(data.getIs_pass() == 1){
                txtResult.setText("通过");
                txtResult.setTextColor(Color.parseColor("#2196F3"));
                layoutInfo.setVisibility(View.VISIBLE);
                layoutLose.setVisibility(View.GONE);
                txtTitle.setText(data.getTitle());
                txtScore.setText(String.valueOf(data.getFraction()));
                txtExerciseNum.setText(String.valueOf(data.getEvaluat_result().size()));
                long date = (long)(data.getAdd_time())*1000;
                String time = DateUtil.getStringByDate(date);
                txtTime.setText(time);

                int rightNum = getRightNum(data.getEvaluat_result());
                int failNum = data.getEvaluat_result().size()-rightNum;
                int rate = (int) ((Float.valueOf(rightNum)/Float.valueOf(data.getEvaluat_result().size()))*100);
                txtExerciseNum.setText("试题数量："+data.getEvaluat_result().size());
                txtRightNum.setText(String.valueOf(rightNum));
                txtErrorNum.setText(String.valueOf(failNum));
                txtRate.setText(String.valueOf(rate));
            }else{
                txtTitle.setText(data.getTitle());
                txtScore.setText(String.valueOf(data.getFraction()));
                layoutInfo.setVisibility(View.GONE);
                layoutLose.setVisibility(View.VISIBLE);
                txtResult.setText("未通过");
                txtResult.setTextColor(Color.parseColor("#ff0000"));
            }
        }

        /**
         * 正确的题目
         * @param list
         * @return
         */
        private int getRightNum(List<EvaluatShowResultBean.DataBean.EvaluatResultBean> list){
            int num = 0;
            for(EvaluatShowResultBean.DataBean.EvaluatResultBean item:list){
                if(item.getSuccess() == 1){
                    num ++;
                }
            }
            return num;
        }
    }
}
