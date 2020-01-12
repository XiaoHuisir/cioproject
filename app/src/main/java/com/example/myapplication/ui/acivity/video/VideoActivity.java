package com.example.myapplication.ui.acivity.video;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.adaper.PdfAdapter;
import com.example.myapplication.app.Constant;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.CurriculumConstract;
import com.example.myapplication.presenter.curriculum.CurriculumPresenter;
import com.example.myapplication.ui.acivity.exercises.ExercisesActivity;
import com.example.myapplication.ui.acivity.exercises.ExercisesResultActivity;
import com.example.myapplication.ui.acivity.pdf.PdfActivity;
import com.example.myapplication.utils.DownLoadUtils;
import com.example.myapplication.utils.FileUtils;
import com.example.myapplication.utils.MyJZVideoPlayerStandard;
import com.example.myapplication.utils.NetDownResponse;
import com.example.myapplication.utils.NetRequsetUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoActivity extends BaseActivity implements CurriculumConstract.View, BaseAdapter.OnItemClickListener {

    @BindView(R.id.layout_bg)
    ConstraintLayout layoutBg;
    @BindView(R.id.videoplayer)
    MyJZVideoPlayerStandard videoplayer;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_work)
    TextView txtWork;
    @BindView(R.id.txt_detail)
    TextView txtDetail;
    @BindView(R.id.layout_info)
    ConstraintLayout layoutInfo;
    @BindView(R.id.txt_study_title)
    TextView txtStudyTitle;
    @BindView(R.id.pdf_recyclerview)
    RecyclerView pdfRecyclerview;

    @BindView(R.id.txt_intro)
    TextView txtIntro;
    @BindView(R.id.txt_video)
    TextView txtVideo;
    @BindView(R.id.layout_sound)
    ConstraintLayout layoutSound;
    @BindView(R.id.img_teacher)
    ImageView imgTeacher;
    @BindView(R.id.txt_evalua)
    TextView txtEvalau;
    @BindView(R.id.txt_score)
    TextView txtScore;
    @BindView(R.id.img_material)
    RelativeLayout imgMaterial;
    @BindView(R.id.linear_details)
    ScrollView lineardetails;
    @BindView(R.id.image_log)
    ImageView imageLog;
    @BindView(R.id.layout_bottoms)
    RelativeLayout layoutbottoms;
    @BindView(R.id.txt_teacher)
    TextView txTeacher;
    @BindView(R.id.txt_gs)
    TextView txtGs;
    @BindView(R.id.txt_content)
    TextView txtContent;
    @BindView(R.id.relative_xq)
    RelativeLayout relative_xq;

    private static final int CODE_EXERCISES = 100;

    static String  curriulumId;
    CurriculumBean curriculumBean;

    PdfAdapter pdfAdapter;
    List<CurriculumBean.DataBean.FileDataBean> pdfList;
    private SensorManager sensorManager;
    MediaPlayer mediaPlayer;
    private boolean inxdler = true;
    long curPos;

    //下载
    PopupWindow popupWindow;
    ProgressBar progressBar;

    int fileSize;
    String fileName;
    String filePath;
    public static VideoActivity instance;

    public boolean isAudio;

    @Override
    protected IBasePresenter getPresenter() {
        instance = this;
        return new CurriculumPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_info;
    }

    @Override
    protected void initView() {
        pdfList = new ArrayList<>();
        pdfAdapter = new PdfAdapter(pdfList);
        pdfRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        pdfRecyclerview.setAdapter(pdfAdapter);
        pdfAdapter.setOnItemClickListener(this);
        layoutSound.setVisibility(View.GONE);
        //TODO
        txtDetail.setVisibility(View.VISIBLE);
        txtIntro.setVisibility(View.INVISIBLE);
        relative_xq.setVisibility(View.VISIBLE);
        layoutbottoms.setVisibility(View.VISIBLE);
//        txtStudyTitle.setVisibility(View.VISIBLE);


        if (commonTimer != null) {
            commonTimer.cancel();
            commontask.cancel();
        }



        commontask = new TimerTask() {
            @Override
            public void run() {
                    Message message = new Message();
                    message.what = SEND_ADD_TIME;
                    addTieHandler.sendMessage(message);
            }
        };
        commonTimer = new Timer();
        commonTimer.schedule(commontask, 0, 60 * 1000 * 5);




    }

    @Override
    protected void initData() {
        curriulumId = getIntent().getStringExtra("curriulum_id");
        ((CurriculumPresenter) mPresenter).getCurriculum(curriulumId);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void getCurriculumReturn(CurriculumBean bean) {
        curriculumBean = bean;
        String video_url = curriculumBean.getData().getCurriculum_data().getVideo_url_code();
        if (!TextUtils.isEmpty(video_url)) {

            videoplayer.setUp(video_url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
            Glide.with(VideoActivity.this).load(bean.getData().getCurriculum_data().getLog())
            .into(videoplayer.img_teacher);
            //用于实现重力感应下切换横竖屏
            sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
            JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
            videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            JZVideoPlayer.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
            videoplayer.TOOL_BAR_EXIST = true;
            //cover为在线图片地址  （视频未播放时的展示图片）
            Glide.with(context).load(video_url).into(videoplayer.thumbImageView);
        }
        txtTitle.setText(curriculumBean.getData().getCurriculum_data().getTitle());
        txtName.setText(curriculumBean.getData().getCurriculum_data().getTeacher());
        txtWork.setText(curriculumBean.getData().getCurriculum_data().getGs());

        if (curriculumBean.getData().getFile_data().size() == 0) {
            imgMaterial.setVisibility(View.VISIBLE);
        } else {
            imgMaterial.setVisibility(View.GONE);
            pdfList.clear();
            pdfList.addAll(bean.getData().getFile_data());
            pdfAdapter.notifyDataSetChanged();
        }

        int fraction = curriculumBean.getData().getRecord_data().getFraction();
        String str = "";

        if (curriculumBean.getData().getHave_evaluat().equals("0")){
            layoutbottoms.setVisibility(View.GONE);
        }else {
            if (curriculumBean.getData().getRecord_data().getId() == 0){
                layoutbottoms.setVisibility(View.VISIBLE);
                str = "未测试";
                txtScore.setTextColor(Color.parseColor("#FF0000"));
            }else {
                layoutbottoms.setVisibility(View.VISIBLE);
                if (curriculumBean.getData().getRecord_data().getIs_pass() == 1) {
                    str = fraction + "分 已通过";
                    txtScore.setTextColor(Color.parseColor("#C7C7C7"));
                } else {
                    str = fraction + "分 未通过";
                    txtScore.setTextColor(Color.parseColor("#FF0000"));
                }
            }

            txtScore.setText(str);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        ((CurriculumPresenter) mPresenter).getCurriculum(curriulumId);
    }

    /**
     * 调用下载文件返回
     * @param result
     */
    @Override
    public void downFileReturn(DownFileBean result) {
        //打开显示页面
        openPdfRead(fileName,filePath);
    }



    @OnClick({R.id.txt_video, R.id.txt_detail, R.id.txt_evalua, R.id.layout_exercises, R.id.txt_intro})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.txt_video:
                selectVideo();
                break;
            case R.id.txt_detail:
                relative_xq.setVisibility(View.INVISIBLE);
                layoutbottoms.setVisibility(View.INVISIBLE);
                txtIntro.setVisibility(View.VISIBLE);
                txtDetail.setVisibility(View.INVISIBLE);
//                txtStudyTitle.setVisibility(View.INVISIBLE);
//                pdfRecyclerview.setVisibility(View.INVISIBLE);
                lineardetails.setVisibility(View.VISIBLE);
                imgMaterial.setVisibility(View.INVISIBLE);
                detail();
                break;
            case R.id.txt_intro:
                relative_xq.setVisibility(View.VISIBLE);

                if (curriculumBean.getData().getHave_evaluat().equals("0")){
                    layoutbottoms.setVisibility(View.GONE);
                }else {
                    if (curriculumBean.getData().getRecord_data().getId() == 0) {
                        layoutbottoms.setVisibility(View.VISIBLE);
                    } else {
                        layoutbottoms.setVisibility(View.VISIBLE);
                    }
                }
                txtIntro.setVisibility(View.INVISIBLE);
//                txtStudyTitle.setVisibility(View.VISIBLE);
                txtDetail.setVisibility(View.VISIBLE);
//                pdfRecyclerview.setVisibility(View.VISIBLE);
                lineardetails.setVisibility(View.INVISIBLE);
                if (curriculumBean.getData().getFile_data().size() == 0) {
                imgMaterial.setVisibility(View.VISIBLE);
            }
                detail();
                break;
            case R.id.txt_evalua:
                //evaluats();
                break;
            case R.id.layout_exercises:
                Intent intent = new Intent();

                if (curriculumBean.getData().getRecord_data().getId() == 0 ) {
                    intent.putExtra("evaluat_curriulum_id" , Integer.valueOf(curriculumBean.getData().getCurriculum_data().getId()) );
                    intent.setClass(VideoActivity.this,ExercisesActivity.class);
                    startActivity(intent);
                } else {
                    intent.putExtra("evaluat_id" , curriculumBean.getData().getRecord_data().getId());
                    intent.setClass(VideoActivity.this,ExercisesResultActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private void evaluats() {
        Intent intent = new Intent();
        intent.setClass(context, ExercisesActivity.class);
        intent.putExtra("evaluat_curriulum_id", curriculumBean.getData().getHave_evaluat());
        startActivity(intent);
    }

    private void detail() {
        CurriculumBean.DataBean.CurriculumDataBean details = curriculumBean.getData().getCurriculum_data();
        if (details.getContent() != null) {
            txtContent.setText(details.getContent());
        } else {
            txtContent.setText("暂无内容！！！-");
        }
        txtGs.setText(details.getGs());
        Glide.with(context).load(details.getLog()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageLog);
        txTeacher.setText(details.getTeacher());

    }

    private void selectSound() {
        videoplayer.setVisibility(View.INVISIBLE);
        layoutSound.setVisibility(View.VISIBLE);

        if (videoplayer.isCurrentPlay()) {
            JZVideoPlayerStandard.goOnPlayOnPause();
            curPos = JZMediaManager.getCurrentPosition();
        }
        playSound();
    }

    private void selectVideo() {
        videoplayer.setVisibility(View.VISIBLE);
        layoutSound.setVisibility(View.INVISIBLE);
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                curPos = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
            }
        }
        if (curPos > 0) {
            JZMediaManager.seekTo(curPos);
            curPos = 0;
        }
    }

    private void playSound() {
        String soundUrl = curriculumBean.getData().getCurriculum_data().getAudio_url_code();
        if (TextUtils.isEmpty(soundUrl)) {
            Toast.makeText(this, "无音频地址", Toast.LENGTH_SHORT).show();
            return;
        }
        String teacherHead = curriculumBean.getData().getCurriculum_data().getLog();
        Glide.with(context).load(teacherHead).into(imgTeacher);
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(soundUrl);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    curPos = mp.getCurrentPosition();
                }
            });
        }
        mediaPlayer.start();
        if (curPos > 0) {
            mediaPlayer.seekTo((int) curPos);
            curPos = 0;
        }
    }

    /**
     * 跳转答题页
     */
    private void openExercises() {
        Intent intent = new Intent();
        intent.setClass(this, ExercisesActivity.class);
        intent.putExtra("evaluat_curriulum_id", curriculumBean.getData().getCurriculum_data().getId());
        startActivityForResult(intent, CODE_EXERCISES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_EXERCISES) {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyJZVideoPlayerStandard.goOnPlayOnPause();
    }

    public void restartTime(){
        addTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = ADD_TIME;
                addTieHandler.sendMessage(message);
            }
        };
        addTimeEr.schedule(addTimerTask, 100, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (addTimeEr != null && addTimerTask != null) {
            addTimeEr.cancel();
            addTimerTask.cancel();
        }
        if (commonTimer != null) {
            commonTimer.cancel();
            commontask.cancel();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("curriculum_id" ,curriulumId );
        jsonObject.put("len" , courseAddTime);

        NetRequsetUtil.getInstance().netRequestPostJson("index/train/curriculum_tj", jsonObject.toString(),
                new NetDownResponse() {
                    @Override
                    public void success(String str) {
                        courseAddTime = 0;
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

        courseAddTime = 0;

        long savedProgress = 0;
        try {
             savedProgress = JZMediaManager.getCurrentPosition();
        }catch (Exception e){

        }


        JZVideoPlayerStandard.releaseAllVideos();

        JSONObject jsonObjec1t = new JSONObject();
        jsonObjec1t.put("curriculum_id" , curriulumId);
        jsonObjec1t.put("len" , savedProgress/1000);

        NetRequsetUtil.getInstance().netRequestPostJson("index/train/record_curriculum_history", jsonObjec1t.toString(),
                new NetDownResponse() {
                    @Override
                    public void success(String str) {
                        Log.d("VideoActivity", "上传成功");
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

        instance = null;
    }

    /**
     * pdf条目点击
     * @param v
     * @param position
     */
    @Override
    public void onItemClick(View v, int position) {
        String pdfname = curriculumBean.getData().getFile_data().get(position).getName();
        String url = curriculumBean.getData().getFile_data().get(position).getUrl();
        String path = Constant.PATH_PDF+pdfname;
        int size = FileUtils.checkFile(Constant.PATH_PDF+pdfname);
        boolean isDir = FileUtils.checkDir(Constant.PATH_PDF);
        //如果存在直接打开
        if(size > 0){
            fileName = pdfname;
            fileSize = size;
            filePath = path;
            sendDownFile(fileName,url,size);
        }else{
            showDown(url,pdfname,path);
        }
        //showDown(url,pdfname,path);
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

    private void sendDownFile(String fileName,String fileUrl,int size){
        Map<String,String> map = new HashMap<>();
        map.put("curriculum_id", String.valueOf(curriculumBean.getData().getCurriculum_data().getId()));
        map.put("file_name",fileName);
        map.put("file_url",fileUrl);
        map.put("file_size", String.valueOf(size));
        ((CurriculumPresenter)mPresenter).downFile(map);
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


    private Timer commonTimer;
    private TimerTask commontask;
    private static final int ADD_TIME = 0;
    private static final int SEND_ADD_TIME = 1;
    private Handler addTieHandler = new AddTimeHandler();
    private static double courseAddTime = 0;
    private Timer addTimeEr = new Timer();
    private TimerTask addTimerTask;



    //计时handler回调
    static class AddTimeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ADD_TIME:
                    if (JZVideoPlayerManager.getSecondFloor() != null) {
                        int currentState = JZVideoPlayerManager.getSecondFloor().currentState;
                        boolean isPaused = currentState == JZVideoPlayer.CURRENT_STATE_PAUSE;
                        if (!isPaused) {
                            courseAddTime++;
                        }
                    } else {
                        courseAddTime++;
                    }
                    break;
                case SEND_ADD_TIME:
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("curriculum_id" ,curriulumId );
                    jsonObject.put("len" , courseAddTime);

                    NetRequsetUtil.getInstance().netRequestPostJson("index/train/curriculum_tj", jsonObject.toString(),
                            new NetDownResponse() {
                                @Override
                                public void success(String str) {
                                    courseAddTime = 0;
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
                    break;
            }
            super.handleMessage(msg);
        }
    }











}
