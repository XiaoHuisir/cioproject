package com.example.myapplication.ui.acivity.exercises;

import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.ExercisesBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.ExercisesConstract;
import com.example.myapplication.presenter.exercises.ExercisesPresenter;

import butterknife.BindView;

public class ExercisesActivity extends BaseActivity implements ExercisesConstract.View {
    @BindView(R.id.txt_evaluat)
    TextView txtEvaluat;

    @Override
    protected IBasePresenter getPresenter() {
        return new ExercisesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluat;

    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        String curriulumId = getIntent().getStringExtra("evaluat_curriulum_id");
        ((ExercisesPresenter) mPresenter).getEvaluation(curriulumId);
    }

    @Override
    public void getEvaluationReturn(ExercisesBean bean) {
        if (bean.getCode() == 10000) {
            String title = bean.getData().get(0).getTitle();
            txtEvaluat.setText(title);
        }
    }


}
