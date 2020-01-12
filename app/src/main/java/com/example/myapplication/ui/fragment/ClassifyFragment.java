package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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

    @BindView(R.id.txt_title)
    TextView txtTitle;
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

    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;

    @BindView(R.id.line1_normal)
    View line1_normal;
    @BindView(R.id.line1_select)
    View line1_select;
    @BindView(R.id.line2_normal)
    View line2_normal;
    @BindView(R.id.line2_select)
    View line2_select;


    int type = 1;
    int page = 1;

    List<TypeIndexBean.DataBean.CurriculumPxBean> courseList;
    List<TypeIndexBean.DataBean.CurriculumKwBean> studyList;
    CourseAdapter courseAdapter;
    StudyAdapter studyAdapter;

    List<TypeIndexBean.DataBean.CurriculumDataBean> curriculumList;
    TypeAdapter typeAdapter;
    boolean isRefresh;

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

        smartRefresh.setRefreshHeader(new ClassicsHeader(this.getActivity()));


        smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                page++;
                ((ClassifyPresenter) mPresenter).getTypeIndex(String.valueOf(type), String.valueOf(page));
//                nullCourses.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = false;
                page = 1;
                ((ClassifyPresenter) mPresenter).getTypeIndex(String.valueOf(type), String.valueOf(page));
            }
        });

    }


    @Override
    protected void initData() {
        isRefresh = false;
        ((ClassifyPresenter) mPresenter).getTypeIndex(String.valueOf(type), String.valueOf(page));
    }

    @Override
    public void getTypeIndexReturn(TypeIndexBean bean) {
        smartRefresh.finishLoadMore(300);
        smartRefresh.finishRefresh(300);
        if (courseList.size() == 0) {
            courseList.addAll(bean.getData().getCurriculum_px());
            if (courseList.size() > 0) {
                courseList.get(0).select = true;
                studyList.addAll(bean.getData().getCurriculum_kw());
                courseAdapter.notifyDataSetChanged();
                studyAdapter.notifyDataSetChanged();
            }
            txtCourseTitle.setText("培训课程");
            txtStudyTitle.setText("课外学习");

            selectCourse();
        }
        if(!isRefresh && page == 1){
            curriculumList.clear();
        }
        if (bean.getData().getCurriculum_data().size() > 0) {
            curriculumList.addAll(bean.getData().getCurriculum_data());

            nullCourses.setVisibility(View.INVISIBLE);
        } else {
            if (isRefresh) {
                page--;
                nullCourses.setVisibility(View.INVISIBLE);
            } else {
                nullCourses.setVisibility(View.VISIBLE);
            }

            Toast.makeText(context, "无课程数据", Toast.LENGTH_SHORT).show();
        }
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
                txtTitle.setText(item.getTitle());
            } else {
                item.select = false;
            }
        }
        selectCourse();

        curriculumList.clear();
        typeAdapter.notifyDataSetChanged();

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
                txtTitle.setText(item.getTitle());
            } else {
                item.select = false;
            }
        }
        selectStudy();

        curriculumList.clear();
        typeAdapter.notifyDataSetChanged();

        courseAdapter.notifyDataSetChanged();
        studyAdapter.notifyDataSetChanged();
        //刷新分类数据
        initData();
    }

    //选择培训课程的分类
    private void selectCourse() {
        line1_normal.setVisibility(View.GONE);
        line1_select.setVisibility(View.VISIBLE);
        layoutCourse.setBackgroundResource(R.drawable.tab_bg_select);
        line2_normal.setVisibility(View.VISIBLE);
        line2_select.setVisibility(View.GONE);
        layoutStudy.setBackgroundResource(0);
    }

    //选择课外学习的分类
    private void selectStudy() {
        line1_normal.setVisibility(View.VISIBLE);
        line1_select.setVisibility(View.GONE);
        layoutCourse.setBackgroundResource(0);
        line2_normal.setVisibility(View.GONE);
        line2_select.setVisibility(View.VISIBLE);
        layoutStudy.setBackgroundResource(R.drawable.tab_bg_select);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        page = 1;
        type = 1;
    }
}
