package com.example.iwapp.ui.acivity.mine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.example.iwapp.R;
import com.example.iwapp.adaper.MyfilelistAdapter;
import com.example.iwapp.app.Constant;
import com.example.iwapp.base.BaseActivity;
import com.example.iwapp.bean.DownFileBean;
import com.example.iwapp.bean.MyfilelistBean;
import com.example.iwapp.interfaces.IBasePresenter;
import com.example.iwapp.interfaces.contract.MyfilelistConstract;
import com.example.iwapp.presenter.mine.MyfilelistPresenter;
import com.example.iwapp.ui.acivity.pdf.PdfActivity;
import com.example.iwapp.utils.DownLoadUtils;
import com.example.iwapp.utils.FileUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MyfilelistActivity extends BaseActivity implements MyfilelistConstract.View, MyfilelistAdapter.MyfilelistClick, OnRefreshLoadMoreListener {

    @BindView(R.id.layout_bg)
    ConstraintLayout layoutBg;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;

    @BindView(R.id.iv_myreturn)
    ImageView ivMyreturn;
    @BindView(R.id.recycler_my)
    RecyclerView recyclerMy;
    private MyfilelistAdapter myfilelistAdapter;
    private ArrayList<MyfilelistBean.DataBean> dataBeanslist;
    private int curriculum_id;
    private String file_name;
    private String file_size;
    private String file_url;
    int fileSize;
    String fileName;
    String filePath;
    //下载
    PopupWindow popupWindow;
    ProgressBar progressBar;
    private MyfilelistBean.DataBean data;
    int mCurrentPage = 1;

    @Override
    protected IBasePresenter getPresenter() {
        return new MyfilelistPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myfilelist;
    }

    @Override
    protected void initView() {
        dataBeanslist = new ArrayList<>();
        recyclerMy.setLayoutManager(new LinearLayoutManager(context));
        myfilelistAdapter = new MyfilelistAdapter(dataBeanslist, this);
        recyclerMy.setAdapter(myfilelistAdapter);
        smartRefresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        ((MyfilelistPresenter) mPresenter).getMyfilelist(mCurrentPage++);
    }

    @Override
    public void getMyfilelistReturn(MyfilelistBean result) {

        if (result.getStatus() == 1) {
            List<MyfilelistBean.DataBean> data = result.getData();
            if(mCurrentPage == 2){
                smartRefresh.finishRefresh();
                dataBeanslist.clear();
                dataBeanslist.addAll(data);
            } else {
                smartRefresh.finishLoadMore();
                dataBeanslist.addAll(data);
            }

            myfilelistAdapter.notifyDataSetChanged();
        }

    }

    //下载文件 TODO
    @Override
    public void getdownfileReturn(DownFileBean result) {
//        String insert_id = result.getData().getInsert_id();
//        Toast.makeText(context, "" + result.getStatus(), Toast.LENGTH_LONG).show();
        //打开显示页面
        openPdfRead(fileName,filePath);
    }

    /**
     * 打开pdf的详情页
     * @param path
     */
    private void openPdfRead(String name,String path){
        Intent intent = new Intent();
        intent.setClass(this, PdfActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("pdf_path",path);
        startActivity(intent);
    }


    @OnClick({R.id.iv_myreturn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_myreturn:
                finish();
                break;
        }
    }

    /**
     * pdf条目点击
     * @param datas
     */
    @Override
    public void myfilelistClick(MyfilelistBean.DataBean datas) {
        data = datas;

       String pdfname = datas.getFile_name();
      String  url = datas.getFile_url();
//        String pdfname = curriculumBean.getData().getFile_data().get(position).getName();
//        String url = curriculumBean.getData().getFile_data().get(position).getUrl();
//
        String path = Constant.PATH_PDF+pdfname;
        int size = FileUtils.checkFile(Constant.PATH_PDF+pdfname);
        //如果存在直接打开
        if(size > 0){
            fileName = pdfname;
            fileSize = size;
            filePath = path;
            sendDownFile(fileName,url,size);
        }else{
            showDown(url,pdfname,path);
        }

//        if (datas != null) {
//            curriculum_id = datas.getCurriculum_id();
//            file_name = datas.getFile_name();
//            file_size = datas.getFile_size();
//            file_url = datas.getFile_url();
//            HashMap<String, String> map = new HashMap<>();
//            map.put("curriculum_id", String.valueOf(curriculum_id));
//            map.put("file_name", file_name);
//            map.put("file_size", file_size);
//            map.put("file_url", file_url);
//            ((MyfilelistPresenter) mPresenter).getdownfile(map);
//        }
    }
    private void sendDownFile(String fileName,String fileUrl,int size){
        Map<String,String> map = new HashMap<>();
        map.put("curriculum_id", String.valueOf(data.getCurriculum_id()));
        map.put("file_name",fileName);
        map.put("file_url",fileUrl);
        map.put("file_size", String.valueOf(size));
        ((MyfilelistPresenter) mPresenter).getdownfile(map);
    }
    private void showDown(final String url, final String name, final String path){
        if(popupWindow == null){
            View view = LayoutInflater.from(this).inflate(R.layout.layout_down_loading,null);
            popupWindow = new PopupWindow();
            popupWindow.setContentView(view);
            popupWindow.setWidth(500);
            popupWindow.setHeight(180);
            progressBar = view.findViewById(R.id.loadingBar);
            progressBar.setProgress(0);
            popupWindow.setFocusable(true);
            layoutBg.setVisibility(View.VISIBLE);
            popupWindow.showAtLocation(layoutBg, Gravity.CENTER,0,0);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DownLoadUtils downLoadUtils = new DownLoadUtils();
                    downLoadUtils.downFile(url, path, new DownLoadUtils.DownLoadListener() {
                        @Override
                        public void loading(final int loaded, final int total) {
                            final int pre = (int) (((float)loaded)/((float)total)*100);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(pre);
                                    //下载完成
                                    if(loaded == total){
                                        fileSize = total;
                                        fileName = name;
                                        filePath = path;
                                        popupWindow.dismiss();
                                        popupWindow = null;
                                        sendDownFile(fileName,url,fileSize);
                                        layoutBg.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        initData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPage = 1;
        initData();
    }
}

