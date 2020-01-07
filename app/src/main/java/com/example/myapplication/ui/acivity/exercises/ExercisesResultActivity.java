package com.example.myapplication.ui.acivity.exercises;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adaper.EvaluatResultAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.EvaluatShowResultBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.ExercisesConstract;
import com.example.myapplication.presenter.exercises.EvaluatResultPresenter;
import com.example.myapplication.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExercisesResultActivity extends BaseActivity implements ExercisesConstract.EvaluatResultView {


    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.txt_reexercise)
    TextView txtReexercise;
    @BindView(R.id.layout_title)
    ConstraintLayout layoutTitle;
    @BindView(R.id.txt_result)
    TextView txtResult;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_score)
    TextView txtScore;
    @BindView(R.id.layout_lose)
    ConstraintLayout layoutLose;
    @BindView(R.id.txt_exercise_title)
    TextView txtExerciseTitle;
    @BindView(R.id.txt_exercise_num)
    TextView txtExerciseNum;
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
    @BindView(R.id.cardview_info)
    CardView cardviewInfo;
    @BindView(R.id.recycler_result)
    RecyclerView recyclerView;
    @BindView(R.id.txt_time)
    TextView txtTime;

    EvaluatResultAdapter evaluatResultAdapter;
    List<EvaluatShowResultBean.DataBean.EvaluatResultBean> list;
    EvaluatShowResultBean curShowResultBean;


    @Override
    protected IBasePresenter getPresenter() {
        return new EvaluatResultPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exercises_result;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        evaluatResultAdapter = new EvaluatResultAdapter(list);
        recyclerView.setAdapter(evaluatResultAdapter);
    }

    @Override
    protected void initData() {
        String evaluatId = getIntent().getStringExtra("evaluat_id");
        ((EvaluatResultPresenter) mPresenter).getEvaluatResult(evaluatId);
    }

    @Override
    public void getEvaluatResultReturn(EvaluatShowResultBean result) {
        if(result.getCode() == 10000){
            curShowResultBean = result;
            list.clear();
            list.addAll(result.getData().getEvaluat_result());
            if(result.getData().getIs_pass() == 1){
                txtResult.setText("通过");
                txtResult.setTextColor(Color.parseColor("#2196F3"));
                cardviewInfo.setVisibility(View.VISIBLE);
                layoutLose.setVisibility(View.GONE);
                txtTitle.setText(result.getData().getTitle());
                txtScore.setText(String.valueOf(result.getData().getFraction()));
                txtExerciseNum.setText(String.valueOf(result.getData().getEvaluat_result().size()));
                long date = (long)(result.getData().getAdd_time())*1000;
                String time = DateUtil.getStringByDate(date);
                txtTime.setText(time);

                int rightNum = getRightNum(result.getData().getEvaluat_result());
                int failNum = result.getData().getEvaluat_result().size()-rightNum;
                int rate = (int) ((Float.valueOf(rightNum)/Float.valueOf(result.getData().getEvaluat_result().size()))*100);
                txtExerciseNum.setText("试题数量："+result.getData().getEvaluat_result().size());
                txtRightNum.setText(String.valueOf(rightNum));
                txtErrorNum.setText(String.valueOf(failNum));
                txtRate.setText(String.valueOf(rate));

            }else{
                cardviewInfo.setVisibility(View.INVISIBLE);
                layoutLose.setVisibility(View.VISIBLE);
                txtResult.setText("未通过");
                txtResult.setTextColor(Color.parseColor("#ff0000"));
            }
            evaluatResultAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(context,result.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.img_close, R.id.txt_reexercise})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                finish();
                break;
            case R.id.txt_reexercise:
                if(curShowResultBean != null){
                    Intent intent = new Intent();
                    intent.setClass(context, ExercisesActivity.class);
                    intent.putExtra("evaluat_curriulum_id", curShowResultBean.getData().getCurriculum_id());
                    startActivity(intent);
                }
                break;
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
