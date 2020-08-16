package com.example.iwapp.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.iwapp.R;
import com.example.iwapp.ui.acivity.video.VideoActivity;

import cn.jzvd.JZVideoPlayerStandard;

public class MyJZVideoPlayerStandard extends JZVideoPlayerStandard {

    private RelativeLayout audioRl;
    private Button bt_audio;
    private TextView txt_video;
    public ImageView img_teacher;

    public MyJZVideoPlayerStandard(Context context) {
        super(context);
    }

    public MyJZVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void init(Context context) {
        super.init(context);
        batteryTimeLayout.setVisibility(GONE);
        bt_audio = (Button) findViewById(R.id.bt_audio);
        bt_audio.setOnClickListener(this);
        audioRl = findViewById(R.id.audioRl);
        txt_video = findViewById(R.id.txt_video);
        txt_video.setOnClickListener(this);
        img_teacher = findViewById(R.id.img_teacher);
        backButton.setOnClickListener(this);
            audioRl.setVisibility(GONE);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_audio:
                audioRl.setVisibility(VISIBLE);
                VideoActivity.instance.isAudio = true;
                break;
            case R.id.txt_video:
                audioRl.setVisibility(GONE);
                VideoActivity.instance.isAudio = false;
                break;
            case R.id.back:
                if (currentScreen == SCREEN_WINDOW_NORMAL) {
                    VideoActivity.instance.finish();
                }
                break;

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_standard_my;
    }

    @Override
    public void setUp(String url, int screen, Object... objects) {
        super.setUp(url, screen, objects);
        backButton.setVisibility(VISIBLE);
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        VideoActivity.instance.restartTime();
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
    }

    @Override
    public void onStateError() {
        super.onStateError();
    }
}
