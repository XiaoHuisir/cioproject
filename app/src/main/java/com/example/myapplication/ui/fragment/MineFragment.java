package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adaper.RecordAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.presenter.usercenter.UserCenterPresenter;
import com.example.myapplication.ui.acivity.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment implements UsercenterConstract.View {

    @BindView(R.id.iv_setting)
    ImageView ivsetting;
    @BindView(R.id.iv_word)
    TextView ivword;
    @BindView(R.id.txt_dutyname)
    TextView txtdutyname;
    @BindView(R.id.iv_buddha)
    ImageView ivBuddha;
    @BindView(R.id.txt_title_name)
    TextView txttitlename;
    @BindView(R.id.txt_referral)
    TextView txtreferral;
    @BindView(R.id.re_record)
    RecyclerView record;
    @BindView(R.id.kcpx_time)
    TextView kcpxtime;
    @BindView(R.id.kcpx_num)
    TextView kcpxnum;
    @BindView(R.id.kwxx_time)
    TextView kwxxtime;
    @BindView(R.id.kwxx_num)
    TextView kwxxnum;
    private ArrayList<UserCenterBean.DataBean.HistoryBean> historyBeans;
    private RecordAdapter recordAdapter;

    @Override
    protected IBasePresenter getPresenter() {
        return new UserCenterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @OnClick({R.id.iv_setting, R.id.iv_word})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting: //设置
                Intent intent = new Intent();
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_word:  //消息
                break;
        }
    }

    @Override
    protected void initView() {
        super.initView();
        historyBeans = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        record.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recordAdapter = new RecordAdapter(historyBeans);
        record.setAdapter(recordAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
        ((UserCenterPresenter) mPresenter).usercenter(Constant.mobiles, Constant.passwords);

    }

    @Override
    public void UserCenterReturn(UserCenterBean result) {
        if (result.getStatus() == Constant.STATUS) {
            mydata(result);
        }
    }

    private void mydata(UserCenterBean result) {
        String nickname = result.getData().getNickname();
        if (nickname != null) {
            txtdutyname.setText(nickname);
        } else {
            txtdutyname.setText("***");
        }
        String avatar = result.getData().getAvatar();
        if (avatar != null) {
            Glide.with(getActivity()).load(avatar).into(ivBuddha);
        } else {
            Toast.makeText(getActivity(), "空", Toast.LENGTH_LONG).show();
        }
        String zw = result.getData().getZw();
        if (zw != null) {
            txttitlename.setText(zw);
        } else {
            Toast.makeText(getActivity(), "空", Toast.LENGTH_LONG).show();
        }

        if (result.getData().getMechanism() != null) {
            txtreferral.setText(result.getData().getMechanism());
        }
        if (result.getData().getKcpx_time() >= 0) {
            kcpxtime.setText(result.getData().getKcpx_time());
        }
        if (result.getData().getKcpx_num() >= 0) {
            kcpxnum.setText(result.getData().getKcpx_num());
        }
        if (result.getData().getKwxx_time() >= 0) {
            kwxxtime.setText(result.getData().getKwxx_time());
        }
        if (result.getData().getKwxx_num() >= 0) {
            kwxxnum.setText(result.getData().getKwxx_num() + "门");
        }


        List<UserCenterBean.DataBean.HistoryBean> history = result.getData().getHistory();
        historyBeans.addAll(history);
        history.clear();
        recordAdapter.notifyDataSetChanged();
    }


}