package com.example.myapplication;


import android.Manifest;
import android.app.Activity;
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
import android.view.LayoutInflater;
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
import com.example.myapplication.utils.SharedPreferencesUtil;
import com.example.myapplication.utils.SystemUtils;

import org.greenrobot.greendao.annotation.JoinEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


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
    private VerBean.DataBean.VersionBean version;
    private String apk_url;
    private int status;
    private UpDataBean upDataBean;
    private int version_code;
    private  boolean booleandelet=false;

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
        checkPermission();
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
                upDataBean = JSON.parseObject(str, UpDataBean.class);
                if (upDataBean != null) {
                    if (upDataBean.getData().getVersion().getStatus() == 0) {
//                        Toast.makeText(context, "以是最新版本", Toast.LENGTH_LONG).show();
                    } else {
                        final String version_code1 = upDataBean.getData().getVersion().getVersion_code();
                        version_code = Integer.valueOf(version_code1);

                            if (version_code > getVersion()) {

                                LayoutInflater inflater = getLayoutInflater();
                                //引入自定义好的对话框.xml布局
                                View layout = inflater.inflate(R.layout.alertdialog_layout, null);
                                //实列提示对话框对象，并将加载的试图对象设置给对话框对象
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle(" ").setView(layout).show();
                                final RelativeLayout update = layout.findViewById(R.id.relative_update);
                                TextView datas = layout.findViewById(R.id.txt_data);
                                String upgrade_content = upDataBean.getData().getVersion().getUpgrade_content();
                                if (upgrade_content != null) datas.setText(upgrade_content); //内容
                                update.setOnClickListener(new View.OnClickListener() {  //更新
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.dismiss();
//
                                    checkIsAndroidO();
//                                        startDownload(upDataBean.getData().getVersion().getApk_url());

                                    }
                                });
//                                RelativeLayout onhint = layout.findViewById(R.id.relative_onhint);
//
//                                onhint.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        booleandelet = true;
////                                        SharedPreferencesUtil.addUpdate(context,Integer.valueOf(upDataBean.getData().getVersion().getVersion_code()));
//                                        alertDialog.dismiss();
//
//                                    }
//                                });
                                RelativeLayout cancel = layout.findViewById(R.id.relative_cancel);
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.dismiss();
                                    }
                                });
                            }
                    }
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
//        ((SearchPresenter) mPresenter).getVersion();
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
            case R.id.layout_msg:       //打开消息界面

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
    @Override
    public void getVersionReturn(VerBean result) {
        ////        //TODO  更新
//        if (result.getData() != null) {
//            version = result.getData().getVersion();
//            if (version != null) {
//                status = version.getStatus();
//                apk_url = version.getApk_url();
//                if (status == 0) {
//                    Toast.makeText(context, "已是最新版本", Toast.LENGTH_LONG).show();
//                } else {
//                    int versions = Integer.valueOf(version.getVersion_code());
//                    if (versions > getVersion()) {
//                        //安装应用
//                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                        builder.setTitle(version.getUpgrade_content());
//                        builder.setMessage("有新版本请更新").setPositiveButton("更新", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                startDownload(apk_url);
//                                Toast.makeText(context, "" + version.getUpgrade_content(), Toast.LENGTH_LONG).show();
//                            }
//                        });
////                        builder.setMessage().setPositiveButton()
//                        builder.show();
//                    }
//                }
//            }
//        }


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
        downurlapk(down_url);
        progressDialog.setMax(100);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


    }

    private void downurlapk(final String url) {
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Call call = client.newCall(request);
                Response response = null;

                    response = call.execute();

                //获取下载的内容输入流
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();
                final long lengh = body.contentLength();
//                System.out.println("文件大小" + lengh);
                // 文件保存到本地
                File file1 = Environment.getExternalStorageDirectory();
                File file = new File(file1, "iww.apk");
                FileOutputStream outputStream = new FileOutputStream(file);
                int lien = 0;
                int losing = 0;
                byte[] bytes = new byte[2048];
                while ((lien = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, lien);

                    losing += lien;
                    final float i = losing * 1.0f / lengh;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            int ii = (int) (i * 100);
//                            progressDialog.setProgress(ii);
//                            System.out.println("下载进度" + ii);
//                            if (ii==100){
//
//                            }
//                        }
//                    });
//                    使用rxjava切换线程
                    UpdateAppearanceProgress(i,url);
                }

                outputStream.flush();
                inputStream.close();
                inputStream.notify();
                inputStream.notifyAll();
                outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void UpdateAppearanceProgress(final float i, final String urls) {
        Observable.just(i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Float>() {
                    @Override
                    public void accept(Float aFloat) throws Exception {
                        int ii = (int) (aFloat * 100);
                        progressDialog.setProgress(ii);
                        if (ii==50){
                            progressDialog.setMessage("正在努力加载...");
                        }
                        if (ii==80){
                            progressDialog.setMessage("即将完成...");
                        }
                        if (ii==99){
                            progressDialog.setMessage("下载完成准备安装...");
                        }
                        if (ii==100){
                                    progressDialog.dismiss();

                            //Android获取一个用于打开APK文件的intent
                            Intent intent1 = new Intent(Intent.ACTION_VIEW);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 24) {
                                File file = new File(Environment.getExternalStorageDirectory(), "iww.apk");
                                Uri apkUri =
                                        FileProvider.getUriForFile(MainActivity.this,
                                                "com.example.myapplication.FileProvider",
                                                file);
                                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                                intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent1.setDataAndType(apkUri, "application/vnd.android.package-archive");
                            } else {
                                intent1.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "iww.apk")),
                                        "application/vnd.android.package-archive");
                            }
                            MainActivity.this.startActivity(intent1);
//                            NetRequsetUtil.getInstance().upApk(urls, "iww.apk", new DownResponseInterface() {
//                                @Override
//                                public void successFinish() {
//                                    progressDialog.dismiss();
//                                    //Android获取一个用于打开APK文件的intent
//                                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
//                                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    if (Build.VERSION.SDK_INT >= 24) {
//                                        File file = new File(Environment.getExternalStorageDirectory(), "iww.apk");
//                                        Uri apkUri =
//                                                FileProvider.getUriForFile(MainActivity.this,
//                                                        "com.example.myapplication.FileProvider",
//                                                        file);
//                                        //添加这一句表示对目标应用临时授权该Uri所代表的文件
//                                        intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                                        intent1.setDataAndType(apkUri, "application/vnd.android.package-archive");
//                                    } else {
//                                        intent1.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "iww.apk")),
//                                                "application/vnd.android.package-archive");
//                                    }
//                                    MainActivity.this.startActivity(intent1);
//                                }
//
//                                @Override
//                                public void progress(int progress) {
//                                    Log.d("MainAcprogress", "progress:" + progress);
//
//                                    progressDialog.setProgress(progress);
//
//                                }
//
//                                @Override
//                                public void erro() {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
//                                }
//                            });
                                progressDialog.notifyAll();
                        }
                    }
                });

    }

    //动态权限
    public void checkPermission() {
        boolean isGranted = true;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //如果没有写sd卡权限
                isGranted = false;
            }
            if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            Log.i("cbs", "isGranted == " + isGranted);
            if (!isGranted) {
                ((Activity) context).requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            }
        }
    }

    /**
     * 判断是否是8.0系统,是的话需要获取此权限，判断开没开，没开的话处理未知应用来源权限问题,否则直接安装
     */
    private void checkIsAndroidO() {
        checkPermission();
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            boolean b = this.getPackageManager().canRequestPackageInstalls();
            if (b) {
                //publicApk();//安装应用的逻辑(写自己的就可以)
                startDownload(upDataBean.getData().getVersion().getApk_url());
            } else {
                //请求安装未知应用来源的权限
                startDownload(upDataBean.getData().getVersion().getApk_url());
                this.requestPermissions(new String[]{
                        Manifest.permission.REQUEST_INSTALL_PACKAGES}, 102);
            }
        } else {
            //publicApk();
            startDownload(upDataBean.getData().getVersion().getApk_url());
        }
    }

}
