package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adaper.CourseAdapter;
import com.example.myapplication.adaper.StudyAdapter;
import com.example.myapplication.adaper.TypeAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.TypeIndexBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.interfaces.contract.TypeIndexConstact;
import com.example.myapplication.presenter.classify.ClassifyPresenter;
import com.example.myapplication.ui.acivity.video.VideoActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class ClassifyFragment extends BaseFragment implements TypeIndexConstact.View, CourseAdapter.CourseItemClick,
        StudyAdapter.StudyItemClick, TypeAdapter.TypeItemClick {

    @BindView(R.id.txt_course_title)
    TextView txtCourseTitle;
    @BindView(R.id.tab_course)
    RecyclerView tabCourse;
    @BindView(R.id.layout_course)
    ConstraintLayout layoutCourse;
    @BindView(R.id.txt_study_title)
    TextView txtStudyTitle;
    @BindView(R.id.tab_study)
    RecyclerView tabStudy;
    @BindView(R.id.layout_study)
    ConstraintLayout layoutStudy;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.null_courses)
    RelativeLayout nullCourses;

    int type = 1;
    int page = 1;

    List<TypeIndexBean.DataBean.CurriculumPxBean> courseList;
    List<TypeIndexBean.DataBean.CurriculumKwBean> studyList;
    CourseAdapter courseAdapter;
    StudyAdapter studyAdapter;

    List<TypeIndexBean.DataBean.CurriculumDataBean> curriculumList;
    TypeAdapter typeAdapter;

    @Override
    protected IBasePresenter getPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_typeindex;
    }

    @Override
    protected void initView() {
        courseList = new ArrayList<>();
        studyList = new ArrayList<>();
        courseAdapter = new CourseAdapter(courseList, this);
        studyAdapter = new StudyAdapter(studyList, this);
        tabCourse.setLayoutManager(new LinearLayoutManager(context));
        tabCourse.setAdapter(courseAdapter);
        tabStudy.setLayoutManager(new LinearLayoutManager(context));
        tabStudy.setAdapter(studyAdapter);
        curriculumList = new ArrayList<>();
        typeAdapter = new TypeAdapter(curriculumList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(typeAdapter);

    }


    @Override
    protected void initData() {
        ((ClassifyPresenter) mPresenter).getTypeIndex(String.valueOf(type), String.valueOf(page));
    }

    @Override
    public void getTypeIndexReturn(TypeIndexBean bean) {
        if (courseList.size() == 0) {
            courseList.addAll(bean.getData().getCurriculum_px());
            if (courseList.size() > 0) {
                courseList.get(0).select = true;
                studyList.addAll(bean.getData().getCurriculum_kw());
                courseAdapter.notifyDataSetChanged();
                studyAdapter.notifyDataSetChanged();
            }

        }
        curriculumList.clear();
        curriculumList.addAll(bean.getData().getCurriculum_data());
        typeAdapter.notifyDataSetChanged();



    }

    /**
     * 点击培训课程返回
     *
     * @param id
     */
    @Override
    public void courseClick(int id) {
        type = id;
        for (TypeIndexBean.DataBean.CurriculumKwBean item : studyList) {
            item.select = false;

        }
        for (TypeIndexBean.DataBean.CurriculumPxBean item : courseList) {
            if (item.getId() == id) {
                item.select = true;

            } else {
                item.select = false;

            }
        }
        layoutStudy.setBackground(null);
        layoutCourse.setBackgroundResource(R.drawable.tab_bg);
        courseAdapter.notifyDataSetChanged();
        studyAdapter.notifyDataSetChanged();
        //刷新分类数据
        initData();
    }

    /**
     * 点击课外学习返回
     *
     * @param id
     */
    @Override
    public void studyClick(int id) {
//        type = 2;
        type = id;
        for (TypeIndexBean.DataBean.CurriculumPxBean item : courseList) {
            item.select = false;
        }
        for (TypeIndexBean.DataBean.CurriculumKwBean item : studyList) {
            if (item.getId() == id) {
                item.select = true;
            } else {
                item.select = false;
            }
        }
        layoutCourse.setBackground(null);
        layoutStudy.setBackgroundResource(R.drawable.tab_bg);
        courseAdapter.notifyDataSetChanged();
        studyAdapter.notifyDataSetChanged();
        //刷新分类数据
        initData();
    }

    @Override
    public void typeClick(int id) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("curriulum_id", String.valueOf(id));
        startActivity(intent);
    }

    public void searchResult(List<IndexBean.DataBean.CurriculumDataBean> result) {
        curriculumList.clear();

        for (IndexBean.DataBean.CurriculumDataBean item : result) {
            TypeIndexBean.DataBean.CurriculumDataBean bean = new TypeIndexBean.DataBean.CurriculumDataBean();
            bean.setId(item.getId());
            bean.setTitle(item.getTitle());
            bean.setTeacher(item.getTeacher());
            bean.setGs(item.getGs());
            bean.setLog(item.getLog());
            bean.setUp_new(item.getUp_new());
            bean.setLen(item.getLen());
            bean.setJd(item.getJd());
            curriculumList.add(bean);
        }
        typeAdapter.notifyDataSetChanged();
    }
}
