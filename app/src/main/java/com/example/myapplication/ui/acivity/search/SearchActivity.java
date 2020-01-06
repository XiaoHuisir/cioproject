package com.example.myapplication.ui.acivity.search;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.FmManager;
import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.SearchBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.presenter.home.SearchPresenter;
import com.example.myapplication.utils.SystemUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements IndexConstract.SearchView {

    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.txt_input)
    EditText txtInput;
    @BindView(R.id.txt_search)
    TextView txtSearch;
    @BindView(R.id.layout_top)
    ConstraintLayout layoutTop;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.txt_clear)
    TextView txtClear;

    List<Fragment> fragments;
    String[] titles;
    FmManager fmManager;

    Map<String, List<SearchBean.DataBean.CurriculumDataBean>> map;


    @Override
    public void initView() {
        map = new HashMap<>();
        fragments = new ArrayList<>();
        titles = new String[0];
        fmManager = new FmManager(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(fmManager);
        tabLayout.setupWithViewPager(viewPager);

        //输入文本的监听
        txtInput.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() > 0){
                if(txtClear.getVisibility() == View.GONE){
                    txtClear.setVisibility(View.VISIBLE);
                }
            }else{
                txtClear.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected IBasePresenter getPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @OnClick({R.id.img_return, R.id.txt_input, R.id.txt_search,R.id.txt_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                SystemUtils.hintKeyBoard(context, txtInput);
                finish();
                break;
            case R.id.txt_input:
                break;
            case R.id.txt_clear:
                txtInput.setText("");
                break;
            case R.id.txt_search:
                String str = txtInput.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(context, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                ((SearchPresenter) mPresenter).search(str, String.valueOf(0), String.valueOf(1));
                txtInput.setFocusable(false);
                break;
        }
    }

    /**
     * 接收搜索返回并进行分类
     *
     * @param result
     */
    @Override
    public void searchResult(List<SearchBean.DataBean.CurriculumDataBean> result) {
        map.clear();
        for (SearchBean.DataBean.CurriculumDataBean item : result) {
            String key = "";
            if (item.getType() == 0) {
                key = "全部";
            } else if (item.getType() == 1) {
                key = "课外学习";
            } else if (item.getType() == 2) {
                key = "培训课程";
            }
            List<SearchBean.DataBean.CurriculumDataBean> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            list.add(item);
        }
        titles = new String[map.keySet().size()];
        int index = 0;
        for (String key : map.keySet()) {
            titles[index] = key;
            index++;
            SearchFragment fragment = SearchFragment.instance(map.get(key));
            fragments.add(fragment);
        }
        fmManager.setTitles(titles);
        fmManager.notifyDataSetChanged();
    }
}
