package com.example.myapplication.ui.acivity.search;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

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

    List<SearchFragment> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_return, R.id.txt_input, R.id.txt_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                break;
            case R.id.txt_input:
                break;
            case R.id.txt_search:
                break;
        }
    }
}
