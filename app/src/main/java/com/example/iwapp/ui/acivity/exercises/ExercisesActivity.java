package com.example.iwapp.ui.acivity.exercises;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwapp.R;
import com.example.iwapp.adaper.ExercisesAdapter;
import com.example.iwapp.base.BaseActivity;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.EvaluationSubmitBean;
import com.example.iwapp.bean.ExercisesBean;
import com.example.iwapp.interfaces.IBasePresenter;
import com.example.iwapp.interfaces.contract.ExercisesConstract;
import com.example.iwapp.presenter.exercises.ExercisesPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ExercisesActivity extends BaseActivity implements ExercisesConstract.View, BaseAdapter.OnItemClickListener {
    @BindView(R.id.txt_evaluat)
    TextView txtEvaluat;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.layout_title)
    ConstraintLayout layoutTitle;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_score)
    TextView txtScore;
    @BindView(R.id.txt_answer_type)
    TextView txtAnswerType;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.layout_answers)
    ConstraintLayout layoutAnswers;
    @BindView(R.id.txt_rate)
    TextView txtRate;
    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.layout_bottom)
    ConstraintLayout layoutBottom;
    @BindView(R.id.layout_next)
    ConstraintLayout layoutNext;
    @BindView(R.id.txt_prev)
    TextView txtPrev;
    @BindView(R.id.txt_next)
    TextView txtNext;

    ExercisesBean currentExercises;
    ExercisesAdapter exercisesAdapter;
    List<ExercisesBean.DataBean.OptionBean> answerList;
    int currentPos; //当前答题
    String curriculumId;

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
        answerList = new ArrayList<>();
        exercisesAdapter = new ExercisesAdapter(answerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(exercisesAdapter);
        exercisesAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {
        curriculumId = String.valueOf(getIntent().getIntExtra("evaluat_curriulum_id", 0));
        ((ExercisesPresenter) mPresenter).getEvaluation(curriculumId);
    }

    @OnClick({R.id.layout_next, R.id.txt_prev,R.id.img_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_next:
                if (currentExercises != null) {
                    //检查是否选择答案
                    boolean bool = checkAnswer();
                    if (!bool) {
                        showDialog("请选择习题答案",0);
                        return;
                    }

                    currentPos++;
                    //如果是最后一条就交卷
                    if (currentPos > currentExercises.getData().size() && txtNext.getText().equals("交卷")) {
                        currentPos = currentExercises.getData().size();
                        try {
                            JSONArray answer = getAnswers();
                            JSONObject data = new JSONObject();
                            data.put("curriculum_id",curriculumId);
                            data.put("answer",answer);
                            ((ExercisesPresenter) mPresenter).submitEvaluation(data);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    updateExercises(currentPos);
                    updateCurrentNum(currentPos);
                }

                break;
            case R.id.txt_prev:
                currentPos--;
                if (currentPos < 1) {
                    currentPos = 1;
                    return;
                }
                updateExercises(currentPos);
                updateCurrentNum(currentPos);
                break;
            case R.id.img_close:
                closeActivity();
                break;
        }
    }

    @Override
    public void getEvaluationReturn(ExercisesBean bean) {
        if (bean.getCode() == 10000) {
            currentExercises = bean;
            if (currentExercises.getData().size() > 0) {
                currentPos = 1;
                updateExercises(currentPos);
                updateCurrentNum(currentPos);
            } else {
                Toast.makeText(this, "没有答题数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateExercises(int pos) {
        ExercisesBean.DataBean dataBean = currentExercises.getData().get(pos - 1);
        txtTitle.setText(dataBean.getTitle());
        txtScore.setText(String.valueOf(dataBean.getFraction()) + "分");
        if (dataBean.getType() == 2) {
            txtAnswerType.setText("多选题");
            txtAnswerType.setBackgroundResource(R.drawable.txt_roundborder_org);
        } else {
            txtAnswerType.setText("单选题");
            txtAnswerType.setBackgroundResource(R.drawable.txt_roundborder_blue);
        }
        answerList.clear();
        answerList.addAll(dataBean.getOption());
        exercisesAdapter.notifyDataSetChanged();
        if (pos > 1 && txtPrev.getVisibility() == View.GONE) {
            txtPrev.setVisibility(View.VISIBLE);
            txtNext.setText("下一题");
        } else if (pos == 1) {
            txtPrev.setVisibility(View.GONE);
            txtNext.setText("下一题");
        } else if (pos == currentExercises.getData().size() ) {
            txtNext.setText("交卷");
        } else if(pos == currentExercises.getData().size() - 1) {
            txtPrev.setVisibility(View.VISIBLE);
            txtNext.setText("下一题");
        }
    }

    private void updateCurrentNum(int pos) {
        txtRate.setText(String.valueOf(pos));
        txtTotal.setText("/" + currentExercises.getData().size());
    }

    @Override
    public void onItemClick(View v, int position) {
        ExercisesBean.DataBean dataBean = currentExercises.getData().get(currentPos - 1);
        if (position < dataBean.getOption().size()) {
            //先判断当前选项是否选中,如果当前选中，直接取消
            if (dataBean.getOption().get(position).select) {
                dataBean.getOption().get(position).select = false;
                exercisesAdapter.notifyDataSetChanged();
            } else {
                //如果当前没有选中，需要判断是单选还是多选
                if (dataBean.getType() == 2) {
                    dataBean.getOption().get(position).select = true;
                    exercisesAdapter.notifyDataSetChanged();
                } else {
                    for (ExercisesBean.DataBean.OptionBean item : dataBean.getOption()) {
                        item.select = false;
                    }
                    dataBean.getOption().get(position).select = true;
                    exercisesAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * 提交答案返回
     *
     * @param result
     */
    @Override
    public void submitEvaluationReturn(EvaluationSubmitBean result) {
        if (result.getCode() == 10000) {
            Toast.makeText(this, "提交试卷成功", Toast.LENGTH_SHORT).show();
            //获取考试结果
            Intent intent = new Intent(this, EexerciseDetailAcivity.class);
            intent.putExtra("evaluat_id",result.getData().getEvaluat_id());
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 封装提交答案的数据对象
     *
     * @return
     * @throws JSONException
     */
    private JSONArray getAnswers() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (ExercisesBean.DataBean dataBean : currentExercises.getData()) {
            JSONObject item = new JSONObject();
            item.put("id", String.valueOf(dataBean.getId()));
            JSONArray answers = new JSONArray();
            for (ExercisesBean.DataBean.OptionBean optionBean : dataBean.getOption()) {
                if (optionBean.select) {
                    answers.put(optionBean.getId());
                }
            }
            item.put("select", answers);
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * 提示显示习题答案
     * action 1只显示确定按
     */
    private void showDialog(String string,int action) {
        AlertDialog alertDialog;
        if(action == 1){
            alertDialog = new AlertDialog.Builder(this)
                    .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消",null)
                    .setMessage(string)
                    .create();
        }else{
            alertDialog = new AlertDialog.Builder(this)
                    .setMessage(string)
                    .create();
        }
        alertDialog.show();
    }


    /**
     * 检查是否选择答案
     *
     * @return
     */
    private boolean checkAnswer() {
        boolean bool = false;
        ExercisesBean.DataBean dataBean = currentExercises.getData().get(currentPos - 1);
        for (ExercisesBean.DataBean.OptionBean item : dataBean.getOption()) {
            if (item.select) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    /**
     * 退出考试
     */
    private void closeActivity(){
        showDialog("您还没有完成作答，确认退出习题测试吗？",1);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            closeActivity();
            return false;
        }
        return false;
    }
}
