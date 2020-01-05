package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.adaper.RecordAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.UnredNoticeBean;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.presenter.usercenter.UserCenterPresenter;
import com.example.myapplication.ui.acivity.PorfolioActivity;
import com.example.myapplication.ui.acivity.PracticeActivity;
import com.example.myapplication.ui.acivity.mine.MyfilelistActivity;
import com.example.myapplication.ui.acivity.mine.NoticeListAcitivity;
import com.example.myapplication.ui.acivity.setting.SettingActivity;
import com.example.myapplication.utils.NumView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static cn.jzvd.JZUtils.TAG;

public class MineFragment extends BaseFragment implements UsercenterConstract.View {

    @BindView(R.id.iv_setting)
    ImageView ivsetting;
    @BindView(R.id.iv_word)
    ImageView ivword;
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
    @BindView(R.id.txt_study_all)
    TextView txtStudyAll;
    @BindView(R.id.txt_check_all)
    TextView txtCheckAll;
    @BindView(R.id.txt_myfilelist)
    TextView txtMyfilelist;
    @BindView(R.id.num_view)
    NumView numView;
    @BindView(R.id.relative_soos)
    RelativeLayout relativesoos;
    private ArrayList<UserCenterBean.DataBean.HistoryBean> historyBeans;
    private RecordAdapter recordAdapter;

    UserCenterBean userCenterBean;

    @Override
    protected IBasePresenter getPresenter() {
        return new UserCenterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @OnClick({R.id.iv_setting, R.id.iv_word, R.id.txt_study_all, R.id.txt_check_all, R.id.txt_myfilelist})
    public void onClick(View view) {
        if (userCenterBean == null) return;
        switch (view.getId()) {
            case R.id.iv_setting: //设置
                Intent intent = new Intent();
                intent.putExtra("nickname", userCenterBean.getData().getNickname());
                intent.putExtra("avatar", userCenterBean.getData().getAvatar());
                intent.putExtra("zw", userCenterBean.getData().getZw());
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_word:  //消息
                Intent intent1 = new Intent();
                intent1.setClass(context, NoticeListAcitivity.class);
                startActivity(intent1);
                break;
            case R.id.txt_study_all:
                Intent intentall = new Intent();
                intentall.setClass(getActivity(), PorfolioActivity.class);
                startActivity(intentall);
                break;
            case R.id.txt_check_all:
                Intent intentcheckall = new Intent();
                intentcheckall.setClass(getActivity(), PracticeActivity.class);
                startActivity(intentcheckall);
                break;
            case R.id.txt_myfilelist:
                Intent intentMyfilelist = new Intent();
                intentMyfilelist.setClass(getActivity(), MyfilelistActivity.class);
                startActivity(intentMyfilelist);
                break;
        }
    }

    @Override
    protected void initView() {
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
        ((UserCenterPresenter) mPresenter).usercenter();
        ((UserCenterPresenter) mPresenter).getUnredNotice();

    }


    @Override
    public void UserCenterReturn(UserCenterBean result) {
        if (result.getStatus() == Constant.STATUS) {
            userCenterBean = result;
            mydata(result);
        }
    }


    @Override
    public void getUnredNoticeReturn(UnredNoticeBean bean) {
        if (bean.getStatus() == 1) {

            String notice_num = bean.getData().getNotice_num();
//          int noticenum = Integer.valueOf(notice_num).intValue();
            int notice_nums = Integer.parseInt(notice_num);
            Constant.NUM_VIEW = String.valueOf(notice_nums);
            numView.setNum(notice_nums);
        }

    }


    private void mydata(UserCenterBean result) {
        String nickname = result.getData().getNickname();
        if (!TextUtils.isEmpty(nickname)) {
            txtdutyname.setText(nickname);
        } else {
            txtdutyname.setText("***");
        }
        String avatar = result.getData().getAvatar();
        if (!TextUtils.isEmpty(avatar)) {
            Glide.with(getActivity()).load(avatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivBuddha);
        } else {
            Toast.makeText(getActivity(), "空", Toast.LENGTH_LONG).show();
        }
        String zw = result.getData().getZw();
        if (!TextUtils.isEmpty(zw)) {
            txttitlename.setText(zw);
        } else {
            Toast.makeText(getActivity(), "空", Toast.LENGTH_LONG).show();
        }

        if (!TextUtils.isEmpty(result.getData().getMechanism())) {
            txtreferral.setText(result.getData().getMechanism());
        }
        if (result.getData().getKcpx_time() >= Constant.ZERO) {
            kcpxtime.setText(String.valueOf(result.getData().getKcpx_time()));
        }
        if (result.getData().getKcpx_num() >= Constant.ZERO) {
            kcpxnum.setText(String.valueOf(result.getData().getKcpx_num()));
        }
        if (result.getData().getKwxx_time() >= Constant.ZERO) {
            kwxxtime.setText(String.valueOf(result.getData().getKwxx_time()) + " 分钟");
        }
        if (result.getData().getKwxx_num() >= Constant.ZERO) {
            kwxxnum.setText(result.getData().getKwxx_num() + "门");
        }


        List<UserCenterBean.DataBean.HistoryBean> history = result.getData().getHistory();
        historyBeans.addAll(history);
        history.clear();
        recordAdapter.notifyDataSetChanged();
    }


}