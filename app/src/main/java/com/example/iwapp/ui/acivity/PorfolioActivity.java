package com.example.iwapp.ui.acivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BaseActivity;
import com.example.iwapp.interfaces.IBasePresenter;
import com.example.iwapp.ui.fragment.AllTypseFragment;
import com.example.iwapp.ui.fragment.LessonTypesFragment;
import com.example.iwapp.ui.fragment.TrainTypesFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class PorfolioActivity extends BaseActivity {

    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.btn_types_all)
    TextView btnAlltypes;
    @BindView(R.id.btn_types_train)
    TextView btnTrc;
    @BindView(R.id.btn_types_lesson)
    TextView btnBeyond;
    private AllTypseFragment allTypseFragment;
    private TrainTypesFragment trainTypesFragment;
    private LessonTypesFragment lessonTypesFragment;


    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_porfolio;
    }

    @Override
    protected void initView() {
        Constant.CURTYPE = Constant.STUD_TYPE_0;
        allTypseFragment = new AllTypseFragment();
        trainTypesFragment = new TrainTypesFragment();
        lessonTypesFragment = new LessonTypesFragment();
        android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.add(R.id.fragmentBin, allTypseFragment).commit();

    }


    @OnClick({R.id.image_return, R.id.btn_types_all, R.id.btn_types_train, R.id.btn_types_lesson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.btn_types_all:
                Constant.CURTYPE = Constant.STUD_TYPE_0;
                allTypes();
                break;
            case R.id.btn_types_train:
                Constant.CURTYPE = Constant.STUD_TYPE_2;
                trainTypes();
                break;
            case R.id.btn_types_lesson:
                Constant.CURTYPE = Constant.STUD_TYPE_1;
                lessonTypes();
                break;
        }
    }

    private void allTypes() {
        btnAlltypes.setBackgroundResource(R.drawable.text_bg_true);
        btnTrc.setBackgroundResource(R.drawable.text_bg_false);
        btnBeyond.setBackgroundResource(R.drawable.text_bg_false);

        allTypseFragment.onRefresh();

    }

    private void trainTypes() {
        btnAlltypes.setBackgroundResource(R.drawable.text_bg_false);
        btnTrc.setBackgroundResource(R.drawable.text_bg_true);
        btnBeyond.setBackgroundResource(R.drawable.text_bg_false);
        allTypseFragment.onRefresh();

    }

    private void lessonTypes() {
        btnAlltypes.setBackgroundResource(R.drawable.text_bg_false);
        btnTrc.setBackgroundResource(R.drawable.text_bg_false);
        btnBeyond.setBackgroundResource(R.drawable.text_bg_true);
        allTypseFragment.onRefresh();
    }

}
