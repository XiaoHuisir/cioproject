package com.example.myapplication.ui.acivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
    Button btnAlltypes;
    @BindView(R.id.btn_types_train)
    Button btnTrc;
    @BindView(R.id.btn_types_lesson)
    Button btnBeyond;
    //    @BindView(R.id.fragmentBin)
//    FrameLayout fragmentBin;
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

    @Override
    protected void initData() {

    }


    @OnClick({R.id.image_return, R.id.btn_types_all, R.id.btn_types_train, R.id.btn_types_lesson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.btn_types_all:
                Constant.CURTYPE = Constant.STUD_TYPE_0;
                Toast.makeText(context,"1111111",Toast.LENGTH_LONG).show();
                allTypes();
                break;
            case R.id.btn_types_train:
                Constant.CURTYPE = Constant.STUD_TYPE_1;
                Toast.makeText(context,"2222",Toast.LENGTH_LONG).show();
                trainTypes();
                break;
            case R.id.btn_types_lesson:
                Constant.CURTYPE = Constant.STUD_TYPE_2;
                Toast.makeText(context,"333333",Toast.LENGTH_LONG).show();
                lessonTypes();
                break;
        }
    }

    private void allTypes() {
        android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.show(allTypseFragment)
                .hide(trainTypesFragment)
                .hide(lessonTypesFragment)
                .commit();
//        transaction1.replace(R.id.fragmentBin,allTypseFragment).commit();


    }

    private void trainTypes() {
        android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.show(trainTypesFragment)
                .hide(allTypseFragment)
                .hide(lessonTypesFragment)
                .commit();
//        transaction1.replace(R.id.fragmentBin,trainTypesFragment).commit();
    }

    private void lessonTypes() {
        android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.show(lessonTypesFragment)
                .hide(allTypseFragment)
                .hide(trainTypesFragment)
                .commit();
//        transaction1.replace(R.id.fragmentBin,lessonTypesFragment).commit();
    }

}
