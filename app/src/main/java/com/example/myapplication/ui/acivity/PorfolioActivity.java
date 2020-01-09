package com.example.myapplication.ui.acivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.PorfolioBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.PorfolioConstract;
import com.example.myapplication.presenter.mine.PorfolioPresenter;
import com.example.myapplication.ui.fragment.AllTypseFragment;
import com.example.myapplication.ui.fragment.LessonTypesFragment;
import com.example.myapplication.ui.fragment.TrainTypesFragment;

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
        allTypseFragment = new AllTypseFragment();
        trainTypesFragment = new TrainTypesFragment();
        lessonTypesFragment = new LessonTypesFragment();
        android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.add(R.id.fragmentBin, allTypseFragment)
                .add(R.id.fragmentBin, trainTypesFragment)
                .add(R.id.fragmentBin, lessonTypesFragment).commit();
        allTypes();
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
                Constant.CURTYPE = Constant.STUD_TYPE_1;
                trainTypes();
                break;
            case R.id.btn_types_lesson:
                Constant.CURTYPE = Constant.STUD_TYPE_2;
                lessonTypes();
                break;
        }
    }

    private void allTypes() {
        btnAlltypes.setBackgroundResource(R.drawable.text_bg_true);
        btnTrc.setBackgroundResource(R.drawable.text_bg_false);
        btnBeyond.setBackgroundResource(R.drawable.text_bg_false);

        btnAlltypes.setBackgroundResource(R.drawable.text_bg_true);
        android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.show(allTypseFragment)
                .hide(trainTypesFragment)
                .hide(lessonTypesFragment)
                .commit();
    }

    private void trainTypes() {
        btnAlltypes.setBackgroundResource(R.drawable.text_bg_false);
        btnTrc.setBackgroundResource(R.drawable.text_bg_true);
        btnBeyond.setBackgroundResource(R.drawable.text_bg_false);

        android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.show(trainTypesFragment)
                .hide(allTypseFragment)
                .hide(lessonTypesFragment)
                .commit();
    }

    private void lessonTypes() {
        btnAlltypes.setBackgroundResource(R.drawable.text_bg_false);
        btnTrc.setBackgroundResource(R.drawable.text_bg_false);
        btnBeyond.setBackgroundResource(R.drawable.text_bg_true);

        android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.show(lessonTypesFragment)
                .hide(allTypseFragment)
                .hide(trainTypesFragment)
                .commit();
    }

}
