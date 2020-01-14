package com.example.myapplication;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.adaper.RecordAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.SearchBean;
import com.example.myapplication.bean.UnredNoticeBean;
import com.example.myapplication.bean.UpDataBean;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.bean.VerBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.IndexConstract;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.presenter.home.SearchPresenter;
import com.example.myapplication.ui.acivity.mine.NoticeListAcitivity;
import com.example.myapplication.ui.acivity.search.SearchActivity;
import com.example.myapplication.ui.fragment.HomeFragment;
import com.example.myapplication.ui.fragment.MineFragment;
import com.example.myapplication.ui.fragment.ClassifyFragment;
import com.example.myapplication.ui.fragment.StudyFragment;
import com.example.myapplication.utils.DownLoadUtils;
import com.example.myapplication.utils.DownResponseInterface;
import com.example.myapplication.utils.NetDownResponse;
import com.example.myapplication.utils.NetRequsetUtil;
import com.example.myapplication.utils.NumView;
import com.example.myapplication.utils.SystemUtils;

import org.greenrobot.greendao.annotation.JoinEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements View.OnClickListener,
        IndexConstract.SearchView, RecordAdapter.RecordItemClick {


    @BindView(R.id.tl)
    TabLayout mTl;
    @BindView(R.id.fl)
    FrameLayout mFl;
    @BindView(R.id.txt_search)
    TextView txtSearch;
    @BindView(R.id.layout_search)
    RelativeLayout layoutSearch;
    @BindView(R.id.layout_msg)
    RelativeLayout layoutMsg;
    @BindView(R.id.num_wx)
    NumView numWx;
    private FragmentManager manager;
    Fragment homeFragment;
    Fragment classifyFragment;
    Fragment mineFragment;
    int curType;
    private int numNot;
    boolean indxler = false;

    private int currentBottomPosition;
    private int targetBottomPosition;
    private List<Fragment> fragmentList = new ArrayList<>();
    private PopupWindow popupWindow;

    private void initFragment() {
        manager = getSupportFragmentManager();
        mTl.addTab(mTl.newTab().setText("课程").setIcon(R.drawable.home));
        mTl.addTab(mTl.newTab().setText("分类").setIcon(R.drawable.classify));
        mTl.addTab(mTl.newTab().setText("我的").setIcon(R.drawable.mine));
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        mineFragment = new MineFragment();

        fragmentList.add(homeFragment);
        fragmentList.add(classifyFragment);
        fragmentList.add(mineFragment);


    }


    @Override
    protected IBasePresenter getPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutId() {
        manager = getSupportFragmentManager();
        return R.layout.activity_main;
    }

    public boolean isFirst = true;

    @Override
    protected void initView() {


        initFragment();
        txtSearch.setOnClickListener(this);
        layoutMsg.setOnClickListener(this);

        manager.beginTransaction().add(R.id.fl, fragmentList.get(0)).commit();

        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                curType = position;
                showFragment(position);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        NetRequsetUtil.getInstance().netRequestGet("index/train/version_update", new NetDownResponse() {
            @Override
            public void success(String str) {
                final UpDataBean upDataBean = JSON.parseObject(str, UpDataBean.class);
                int version_code =Integer.valueOf(upDataBean.getData().getVersion().getVersion_code()) ;
                if (version_code > getVersion()){
                    //安装应用
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("有新版本请更新").setNegativeButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startDownload(upDataBean.getData().getVersion().getApk_url());
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void errowithresponse(String str) {

            }

            @Override
            public void erro() {

            }

            @Override
            public void finish() {

            }
        });


    }

    @Override
    protected void initData() {
        ((SearchPresenter) mPresenter).getUnredNotice();
        ((SearchPresenter) mPresenter).getVersion();
    }


    private void showFragment(int type) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        switch (type) {
            case 0:
                targetBottomPosition = 0;
                if (currentBottomPosition == 0) {
                    return;
                }
                fragmentTransaction.hide(fragmentList.get(currentBottomPosition));
                if (!fragmentList.get(targetBottomPosition).isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragmentList.get(targetBottomPosition));
                }
                fragmentTransaction.show(fragmentList.get(targetBottomPosition)).commit();
                currentBottomPosition = 0;
                layoutSearch.setVisibility(View.VISIBLE);
                break;
            case 1:
                targetBottomPosition = 1;
                if (currentBottomPosition == 1) {
                    return;
                }
                fragmentTransaction.hide(fragmentList.get(currentBottomPosition));
                if (!fragmentList.get(targetBottomPosition).isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragmentList.get(targetBottomPosition));
                }
                fragmentTransaction.show(fragmentList.get(targetBottomPosition)).commit();
                currentBottomPosition = 1;
                layoutSearch.setVisibility(View.VISIBLE);
                break;
            case 2:
                targetBottomPosition = 2;
                if (currentBottomPosition == 2) {
                    return;
                }
                fragmentTransaction.hide(fragmentList.get(currentBottomPosition));
                if (!fragmentList.get(targetBottomPosition).isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragmentList.get(targetBottomPosition));
                }
                fragmentTransaction.show(fragmentList.get(targetBottomPosition)).commit();
                currentBottomPosition = 2;
                layoutSearch.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_search:
                Intent intent = new Intent();
                intent.setClass(context, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_msg:
//                showUpdateDialog();
                //打开消息界面
//                numWx.setNum(0);
                numWx.setNum(0);
                indxler = true;
                Intent notice = new Intent();
                notice.setClass(context, NoticeListAcitivity.class);
                startActivity(notice);

                break;
        }
    }

    @Override
    public void click(String id) {

    }


    @Override
    public void searchResult(List<SearchBean.DataBean.CurriculumDataBean> result) {

    }

    @Override
    public void getUnredNoticeReturn(UnredNoticeBean bean) {
        if (bean.getStatus() == 1) {

            String notice_num = bean.getData().getNotice_num();
            Constant.NUM_VIEW = "111";
            int noticenum = Integer.valueOf(notice_num).intValue();
//            int notice_nums = Integer.parseInt(notice_num);

            if (noticenum == 0) {
                numWx.setNum(0);
            } else {
                numWx.setNum(noticenum);
            }
        }
    }

    private VerBean verBean;

    @Override
    public void getVersionReturn(VerBean result) {
////        //TODO  更新

    }


    private int getVersion() {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    ProgressDialog progressDialog;

    private void startDownload(String down_url) {
        //进度条，在下载的时候实时更新进度，提高用户友好度
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressNumberFormat(" ");
        progressDialog.setTitle("正在下载");
        progressDialog.setMessage("请稍候...");
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        NetRequsetUtil.getInstance().upApk(down_url, "iww.apk", new DownResponseInterface() {
            @Override
            public void successFinish() {
                progressDialog.dismiss();
                //Android获取一个用于打开APK文件的intent
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(Build.VERSION.SDK_INT>=24) {
                    File file = new File(Environment.getExternalStorageDirectory(), "iww.apk");
                    Uri apkUri =
                            FileProvider.getUriForFile(MainActivity.this,
                                    "com.example.myapplication.fileprovider",
                                    file);
                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent1.setDataAndType(apkUri, "application/vnd.android.package-archive");
                }else{
                    intent1.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"iww.apk")),
                            "application/vnd.android.package-archive");
                }
                MainActivity.this.startActivity(intent1);
            }

            @Override
            public void progress(int progress) {
                Log.d("MainAcprogress", "progress:" + progress);
                progressDialog.setProgress(progress);

            }

            @Override
            public void erro() {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
