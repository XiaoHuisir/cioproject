package com.example.myapplication.ui.acivity.setting;

import android.app.AlertDialog;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.app.Constant;
import com.example.myapplication.app.MyApp;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.TokenBean;
import com.example.myapplication.bean.UserInfoUpdateBean;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.interfaces.usercenter.UsercenterConstract;
import com.example.myapplication.presenter.usercenter.UpdateUserInfoPresenter;
import com.example.myapplication.ui.acivity.login.LoginActivity;
import com.example.myapplication.utils.SharedPreferencesUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements UsercenterConstract.UpdateView {

    private static final int EDIT_NAME = 100;  //修改名字
    private static final int EDIT_AVATAR = 101; //修改头像
    private static final int EDIT_ZW = 102; //职务

    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.setting_back)
    ImageView setting_back;
    @BindView(R.id.txt_zwName)
    TextView txtZwName;
    @BindView(R.id.txt_logout)
    LinearLayout txtlogout;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    String avatar;
    String nickname;
    String zw;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    String imgPath;

    @Override
    protected IBasePresenter getPresenter() {
        return new UpdateUserInfoPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        avatar = getIntent().getStringExtra("avatar");
        nickname = getIntent().getStringExtra("nickname");
        zw = getIntent().getStringExtra("zw");

        if(!TextUtils.isEmpty(avatar)){
//            Glide.with(this).load(avatar).apply(new RequestOptions().transform(new RoundedCorners(20)))
//                    .into(ivHeader);
            Glide.with(this).load(avatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivHeader);
        }
        if(!TextUtils.isEmpty(nickname)){
            txtNickname.setText(nickname);
        }
        if(!TextUtils.isEmpty(zw)){
            txtZwName.setText(zw);
//            txtZw.setText(zw);
        }
    }

    @OnClick({R.id.setting_back,R.id.txt_nickname,R.id.txt_zwName,R.id.iv_header,R.id.txt_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_nickname: //设置名称
                Intent intent = new Intent();
                intent.setClass(context, SetNameActivity.class);
                intent.putExtra("type",EDIT_NAME);
                intent.putExtra("nickname",nickname);
                intent.putExtra("zw",zw);
                startActivityForResult(intent,EDIT_NAME);
                break;
            case R.id.txt_zwName:
                Intent intentZw = new Intent();
                intentZw.setClass(context, SetNameActivity.class);
                intentZw.putExtra("type",EDIT_ZW);
                intentZw.putExtra("nickname",nickname);
                intentZw.putExtra("zw",zw);
                startActivityForResult(intentZw,EDIT_ZW);
                break;
            case R.id.iv_header:
                showPicture();
                break;
                case R.id.setting_back:
               finish();
                break;
            case R.id.txt_logout:
                SharedPreferencesUtil.deleteToken(MyApp.mApp);
                Intent intent1 = new Intent();
                intent1.setClass(context, LoginActivity.class);
//                intent1.addFlags(intent1.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent1);
                finish();
                break;

        }
    }

    /**
     * 显示图片
     */
    private void showPicture(){
        if(builder == null || dialog.isShowing()) {
            builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_selector_pop, null);
            builder.setView(view);
            dialog = builder.create();
            dialog.show();
            //显示在底部
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            WindowManager m = getWindowManager();
            Display display = m.getDefaultDisplay();
            WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
            p.width = display.getWidth();
            dialog.getWindow().setAttributes(p);

            TextView txtBig = view.findViewById(R.id.txt_showbig);
            TextView txt_photo = view.findViewById(R.id.txt_photo);
            TextView txt_camera = view.findViewById(R.id.txt_camera);
            TextView txt_cancel = view.findViewById(R.id.txt_cancel);
            txtBig.setOnClickListener(onClickListener);
            txt_photo.setOnClickListener(onClickListener);
            txt_camera.setOnClickListener(onClickListener);
            txt_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.hide();
                }
            });
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.txt_showbig:

                    break;
                case R.id.txt_photo:
                    showPhoto();
                    dialog.hide();
                    break;
                case R.id.txt_camera:
                    showCamera();
                    dialog.hide();
                    break;
            }
        }
    };

    private void showPhoto(){
        PictureSelector.create(SettingActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private void showCamera(){
        PictureSelector.create(SettingActivity.this)
                .openCamera(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .isCamera(true)
                .forResult(PictureConfig.REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == EDIT_NAME){
            String name = data.getStringExtra("value");
            if(!TextUtils.isEmpty(name)){
                txtNickname.setText(name);
            }
        }else if(requestCode == EDIT_ZW){
            String zw = data.getStringExtra("value");
            if(!TextUtils.isEmpty(zw)){
                txtZwName.setText(zw);
            }
        }else if(requestCode == EDIT_AVATAR){

        }else if (requestCode == PictureConfig.CHOOSE_REQUEST){
            //相册返回
            List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
            if(list.size() > 0){
                imgPath = list.get(0).getPath();
                getToken();
            }

        }else if(requestCode == PictureConfig.REQUEST_CAMERA){
            //相机返回
            List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
            if(list.size() > 0){
                imgPath = list.get(0).getPath();
                getToken();
            }
        }

    }

    private void getToken(){
        progressBar.setVisibility(View.VISIBLE);
        ((UpdateUserInfoPresenter)mPresenter).getToken();
    }

    //更新头像
    @Override
    public void updateUserInfoReturn(UserInfoUpdateBean result) {
        progressBar.setVisibility(View.GONE);
        if(result.getCode() == 10000){
//            Glide.with(this).load(avatar).apply(new RequestOptions().transform(new RoundedCorners(20)))
//                    .into(ivHeader);
            Glide.with(this).load(avatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivHeader);

        }
    }

    //获取token返回
    @Override
    public void getTokenReturn(TokenBean result) {
        if(result.getCode() == 10000) {
            UploadManager uploadManager = new UploadManager();
            final String name = System.currentTimeMillis() + String.valueOf(((int) Math.random() * 1000))+".jpg";
            File file = new File(imgPath);
            if(file.exists()){
                uploadManager.put(file, name, result.getData().getToken(), new UpCompletionHandler() {
                    @Override
                    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                        Log.i("Setting", s);
                        String header = Constant.ResUrl+name;
                        updateUserInfo(header);
                    }
                }, null);
            }
        }
    }

    /**
     * 更新用户信息--头像
     * @param header
     */
    private void updateUserInfo(String header){
        avatar = header;
        ((UpdateUserInfoPresenter)mPresenter).updateUserInfo(nickname,zw,header);
    }

}
