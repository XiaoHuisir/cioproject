package com.example.myapplication.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.SearchBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.txt_course_1)
    TextView txt_course_1;
    @BindView(R.id.txt_course_2)
    TextView txt_course_2;

    int curCourse = 2;
    int page = 1;

    private int currentBottomPosition;
    private int targetBottomPosition;

    private CourseFragment courseFragment;
    private StudyFragment studyFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;

    }

    @Override
    protected void initView() {
        targetBottomPosition = 0;
        currentBottomPosition = 0;
        courseFragment = new CourseFragment();
        studyFragment = new StudyFragment();

        fragmentList.add(courseFragment);
        fragmentList.add(studyFragment);



        fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentBox, fragmentList.get(0)).commit();


    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.txt_course_1, R.id.txt_course_2})
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.txt_course_1:
                Constant.CourseType = Constant.COURSE_TYPE_2;
                resetCourseTxt();
                targetBottomPosition = 0;
                if (currentBottomPosition == 0) {
                    return;
                }
                txt_course_1.setTextColor(getResources().getColor(R.color.red));
                fragmentTransaction.hide(fragmentList.get(currentBottomPosition));
                if (!fragmentList.get(targetBottomPosition).isAdded()) {
                    fragmentTransaction.add(R.id.fragmentBox, fragmentList.get(targetBottomPosition));
                }
                fragmentTransaction.show(fragmentList.get(targetBottomPosition)).commit();
                currentBottomPosition = 0;

                break;
            case R.id.txt_course_2:
                Constant.CourseType = Constant.COURSE_TYPE_1;
                targetBottomPosition = 1;
                if (currentBottomPosition == 1) {
                    return;
                }
                fragmentTransaction.hide(fragmentList.get(currentBottomPosition));
                Log.d("HomeFragment", "!fragmentList.get(targetBottomPosition).isAdded():" + !fragmentList.get(targetBottomPosition).isAdded());
                if (!fragmentList.get(targetBottomPosition).isAdded()) {
                    fragmentTransaction.add(R.id.fragmentBox, fragmentList.get(targetBottomPosition));
                    fragmentTransaction.show(fragmentList.get(targetBottomPosition)).commit();
                }else {
                    fragmentTransaction.show(fragmentList.get(targetBottomPosition)).commit();
                }
                currentBottomPosition = 1;
                txt_course_1.setTextColor(getResources().getColor(R.color.black));
                txt_course_2.setTextColor(getResources().getColor(R.color.red));
                break;

        }

    }

    private void showCourse() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentBox, courseFragment).commit();
    }

    private void showStudy() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentBox, studyFragment).commit();
    }


    private void resetCourseTxt() {
        txt_course_1.setTextColor(getResources().getColor(R.color.black));
        txt_course_2.setTextColor(getResources().getColor(R.color.black));
    }

}
