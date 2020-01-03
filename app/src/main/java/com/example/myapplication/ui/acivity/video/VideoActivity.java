package com.example.myapplication.ui.acivity.video;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.adaper.PdfAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.contract.CurriculumConstract;
import com.example.myapplication.presenter.curriculum.CurriculumPresenter;
import com.example.myapplication.ui.acivity.exercises.ExercisesActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoActivity extends BaseActivity implements CurriculumConstract.View {
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
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
    @BindView(R.id.txt_sound)
    TextView txtSound;
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
    ImageView imgMaterial;
    @BindView(R.id.linear_details)
    LinearLayout lineardetails;
    @BindView(R.id.image_log)
    ImageView imageLog;
    @BindView(R.id.txt_teacher)
    TextView txTeacher;
    @BindView(R.id.txt_gs)
    TextView txtGs;
    @BindView(R.id.text_title)
    TextView texTitle;
    @BindView(R.id.txt_content)
    TextView txtContent;

    private static final int CODE_EXERCISES = 100;

    String curriulumId;
    CurriculumBean curriculumBean;

    PdfAdapter pdfAdapter;
    List<CurriculumBean.DataBean.FileDataBean> pdfList;
    private SensorManager sensorManager;
    MediaPlayer mediaPlayer;
    private boolean inxdler = true;
    long curPos;

    @Override
    protected IBasePresenter getPresenter() {
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
        layoutSound.setVisibility(View.GONE);
        //TODO
        txtDetail.setVisibility(View.VISIBLE);
        txtIntro.setVisibility(View.INVISIBLE);
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
        if (curriculumBean.getData().getRecord_data().getIs_pass() == 1) {
            str = fraction + "分 已通过";
            txtScore.setTextColor(Color.parseColor("#C7C7C7"));
        } else {
            str = "未通过";
            txtScore.setTextColor(Color.parseColor("#FF0000"));
        }
        txtScore.setText(str);
    }

    @OnClick({R.id.txt_sound, R.id.txt_video, R.id.txt_detail, R.id.txt_evalua, R.id.layout_exercises, R.id.txt_intro})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_sound:
                selectSound();
                break;
            case R.id.txt_video:
                selectVideo();
                break;
            //TODO
            case R.id.txt_detail:
                txtIntro.setVisibility(View.VISIBLE);
                txtDetail.setVisibility(View.INVISIBLE);
                pdfRecyclerview.setVisibility(View.INVISIBLE);
                lineardetails.setVisibility(View.VISIBLE);
                detail();
                break;
            case R.id.txt_intro:
                txtIntro.setVisibility(View.INVISIBLE);
                txtDetail.setVisibility(View.VISIBLE);
                pdfRecyclerview.setVisibility(View.VISIBLE);
                lineardetails.setVisibility(View.INVISIBLE);
                detail();
                break;
            case R.id.txt_evalua:
                evaluats();
                break;
            case R.id.layout_exercises:
                if (curriculumBean.getData().getRecord_data().getIs_pass() == 0) {
                    openExercises();
                } else {
                    Toast.makeText(context, "已通过测试", Toast.LENGTH_SHORT).show();
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
        if (details.getContent()!=null){
            txtContent.setText(details.getContent());
        }else {
            txtContent.setText("暂无内容！！！-");
        }
        txtGs.setText(details.getGs());
        Glide.with(context).load(details.getLog()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageLog);
        txTeacher.setText(details.getTeacher());
        texTitle.setText(details.getTitle());

    }

    private void selectSound() {
        videoplayer.setVisibility(View.INVISIBLE);
        layoutSound.setVisibility(View.VISIBLE);
        txtSound.setVisibility(View.GONE);
        if (videoplayer.isCurrentPlay()) {
            JZVideoPlayerStandard.goOnPlayOnPause();
            curPos = JZMediaManager.getCurrentPosition();
        }
        playSound();
    }

    private void selectVideo() {
        videoplayer.setVisibility(View.VISIBLE);
        layoutSound.setVisibility(View.INVISIBLE);
        txtSound.setVisibility(View.VISIBLE);
        if (mediaPlayer != null) {
            if(mediaPlayer.isPlaying()){
                curPos = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
            }
        }
        if(curPos > 0){
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
        if(curPos > 0){
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
        if (videoplayer != null && videoplayer.isCurrentPlay()) {
            JZVideoPlayerStandard.goOnPlayOnPause();
        }

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoplayer != null && videoplayer.isCurrentPlay()) {
            JZVideoPlayerStandard.goOnPlayOnPause();
        }

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
    }
}
