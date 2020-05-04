package com.example.iwapp.ui.acivity.search;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseActivity;
import com.example.iwapp.base.FmManager;
import com.example.iwapp.interfaces.IBasePresenter;
import com.example.iwapp.utils.SystemUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchFragment.TabLayoutFun {

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
    int[] types = new int[]{0,2, 1};
    FmManager fmManager;
    int curPageNo=0;

    @Override
    public void initView() {
        fragments = new ArrayList<>();
        titles = new String[]{"全部课程","培训课程", "课外学习"};
        for (int i = 0; i < titles.length; i++) {
            SearchFragment fragment = SearchFragment.instance(types[i],this);
            fragments.add(fragment);
        }

        fmManager = new FmManager(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(fmManager);
        tabLayout.setupWithViewPager(viewPager);

        //输入文本的监听
        txtInput.addTextChangedListener(textWatcher);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                curPageNo = i;
                //切换分类以后在这里搜索数据 不知道需求是否如此
                String str = txtInput.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(context, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                ((SearchFragment) fragments.get(viewPager.getCurrentItem())).doSearch(str);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                if (txtClear.getVisibility() == View.GONE) {
                    txtClear.setVisibility(View.VISIBLE);
                }
            } else {
                txtClear.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @OnClick({R.id.img_return, R.id.txt_input, R.id.txt_search, R.id.txt_clear})
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
                setTabLayout(View.GONE);
                ((SearchFragment) fragments.get(viewPager.getCurrentItem())).clearSearch();
                tabLayout.getTabAt(0).select();
                break;
            case R.id.txt_search:
                String str = txtInput.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(context, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                ((SearchFragment) fragments.get(viewPager.getCurrentItem())).doSearch(str);
                SystemUtils.hintKeyBoard(context, txtInput);
                break;
        }
    }

    @Override
    public void setTabLayout(int visible) {
        tabLayout.setVisibility(visible);
    }
}
