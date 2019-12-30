package com.example.myapplication.ui.acivity.video;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

    String curriulumId;
    CurriculumBean curriculumBean;

    PdfAdapter pdfAdapter;
    List<CurriculumBean.DataBean.FileDataBean> pdfList;
    private SensorManager sensorManager;
    MediaPlayer mediaPlayer;
    private boolean inxdler = true;

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
        pdfList.clear();
        pdfList.addAll(bean.getData().getFile_data());
        pdfAdapter.notifyDataSetChanged();


    }

    @OnClick({R.id.txt_sound, R.id.txt_video, R.id.txt_detail, R.id.txt_evalua})
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
                break;
            case R.id.txt_intro:
                detail();
                break;
            case R.id.txt_evalua:
                evaluats();
                break;
        }
    }

    private void evaluats() {
        Intent intent = new Intent();
        intent.setClass(context, ExercisesActivity.class);
        intent.putExtra("evaluat_curriulum_id", curriculumBean.getData().getHave_evaluat());
        startActivity(intent);
    }

    //TODO
    private void detail() {
        txtIntro.setVisibility(View.INVISIBLE);
        txtDetail.setVisibility(View.VISIBLE);

    }

    private void selectSound() {
        videoplayer.setVisibility(View.INVISIBLE);
        layoutSound.setVisibility(View.VISIBLE);
        txtSound.setVisibility(View.GONE);
        if (videoplayer.isCurrentPlay()) {
            JZVideoPlayerStandard.goOnPlayOnPause();
        }
        playSound();
    }

    private void selectVideo() {
        videoplayer.setVisibility(View.VISIBLE);
        layoutSound.setVisibility(View.INVISIBLE);
        txtSound.setVisibility(View.VISIBLE);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
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
        }
        mediaPlayer.start();
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
