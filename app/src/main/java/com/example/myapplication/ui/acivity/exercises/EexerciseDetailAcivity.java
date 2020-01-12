package com.example.myapplication.ui.acivity.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adaper.ExerciseDetailAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.EvaluatShowResultBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.ExercisesConstract;
import com.example.myapplication.presenter.exercises.EvaluatResultPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by louyulin on 2020/1/12.
 */

public class EexerciseDetailAcivity extends BaseActivity implements ExercisesConstract.EvaluatResultView {


    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.txt_reexercise)
    TextView txtReexercise;
    @BindView(R.id.layout_title)
    ConstraintLayout layoutTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private EvaluatShowResultBean.DataBean data;

    @Override
    protected IBasePresenter getPresenter() {
        return new EvaluatResultPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.exercise_detail_layout;
    }

    @Override
    protected void initData() {
        String evaluatId = getIntent().getStringExtra("evaluat_id");
        ((EvaluatResultPresenter) mPresenter).getEvaluatResult(evaluatId);
    }

    @Override
    public void getEvaluatResultReturn(EvaluatShowResultBean result) {
        List<EvaluatShowResultBean.DataBean.EvaluatResultBean> evaluat_result = result.getData().getEvaluat_result();
        data = result.getData();

        ExerciseDetailAdapter adapter = new ExerciseDetailAdapter(evaluat_result, data,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        recyclerview.setAdapter(adapter);
    }


    @OnClick({R.id.img_close, R.id.txt_reexercise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                finish();
                break;
            case R.id.txt_reexercise:
                if(data != null){
                    Intent intent = new Intent();
                    intent.setClass(context, ExercisesActivity.class);
                    intent.putExtra("evaluat_curriulum_id", data.getCurriculum_id());
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
