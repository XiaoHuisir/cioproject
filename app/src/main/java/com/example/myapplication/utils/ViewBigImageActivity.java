package com.example.myapplication.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;
import com.luck.picture.lib.photoview.PhotoViewAttacher;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import uk.co.senab.photoview.PhotoView;


/**
 * 用于查看大图
 *
 * @author jingbin
 */
public class ViewBigImageActivity extends FragmentActivity implements OnPageChangeListener, uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener {

    // 保存图片
    private TextView tv_save_big_image;
    // 接收传过来的uri地址
    List<String> imageuri;
    // 接收穿过来当前选择的图片的数量
    int code;
    // 用于判断是头像还是文章图片 1:头像 2：文章大图
    int selet;

    // 用于管理图片的滑动
    ViewPager very_image_viewpager;
    // 当前页数
    private int page;

    /**
     * 显示当前图片的页数
     */
    TextView very_image_viewpager_text;
    /**
     * 用于判断是否是加载本地图片
     */
    private boolean isLocal;

    ViewPagerAdapter adapter;

    /**
     * 本应用图片的id
     */
    private int imageId;
    /**
     * 是否是本应用中的图片
     */
    private boolean isApp;

    /**
     * @param context
     * @param selet   2,大图显示当前页数，1,头像，不显示页数
     * @param code    第几张
     * @param imgList
     */
    public static void goInto(Context context, int selet, int code, ArrayList<String> imgList) {
        Intent intent = new Intent();
        intent.setClass(context, ViewBigImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("selet", selet);//
        bundle.putInt("code", code);//
        bundle.putStringArrayList("imageuri", imgList);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_big_image);
        getView();
    }

    /**
     * 保存图片至相册
     */
    public void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "ysjk");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsoluteFile())));
    }

    /*
     * 接收控件
     */
    private void getView() {
        /************************* 接收控件 ***********************/
        very_image_viewpager_text = (TextView) findViewById(R.id.very_image_viewpager_text);
        tv_save_big_image = (TextView) findViewById(R.id.tv_save_big_image);
        very_image_viewpager = (ViewPager) findViewById(R.id.very_image_viewpager);

        tv_save_big_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxPermissions rxPermissions = new RxPermissions(ViewBigImageActivity.this);
                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                if (aBoolean) {
                                    clickSave();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
        /************************* 接收传值 ***********************/
        Bundle bundle = getIntent().getExtras();
        code = bundle != null ? bundle.getInt("code") : 0;
        selet = bundle.getInt("selet");
        isLocal = bundle.getBoolean("isLocal", false);
        imageuri = bundle.getStringArrayList("imageuri");
        /**是否是本应用中的图片*/
        isApp = bundle.getBoolean("isApp", false);
        /**本应用图片的id*/
        imageId = bundle.getInt("id", 0);

        /**
         * 给viewpager设置适配器
         */
        if (isApp) {
            MyPageAdapter myPageAdapter = new MyPageAdapter();
            very_image_viewpager.setAdapter(myPageAdapter);
            very_image_viewpager.setEnabled(false);
        } else {
            adapter = new ViewPagerAdapter();
            very_image_viewpager.setAdapter(adapter);
            very_image_viewpager.setCurrentItem(code);
            page = code;
            very_image_viewpager.setOnPageChangeListener(this);
            very_image_viewpager.setEnabled(false);
            // 设定当前的页数和总页数
            if (selet == 2) {
                very_image_viewpager_text.setText((code + 1) + " / " + imageuri.size());
            }
        }
    }

    private void clickSave() {
        Toast.makeText(this, "开始下载图片", Toast.LENGTH_SHORT).show();
        String imgUrl = imageuri.get(page);
        File file = new File(imgUrl);
        if (file.exists()) {// 本地图片
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageId);
            if (bitmap != null) {
                saveImageToGallery(ViewBigImageActivity.this, bitmap);
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        } else {// 网络图片
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 子线程获得图片路径
                    String imgUrl = imageuri.get(page);
                    Bitmap bitmap = null;
                    try {
                        FutureTarget<Bitmap> bitmapFu = Glide.with(ViewBigImageActivity.this)
                                .asBitmap()
                                .load(imgUrl)
                                .submit();
                        bitmap = bitmapFu.get();
                        if (bitmap != null) {
                            saveImageToGallery(ViewBigImageActivity.this, bitmap);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ViewBigImageActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }  catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /**
     * 本应用图片适配器
     */

    class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getLayoutInflater().inflate(R.layout.viewpager_very_image, container, false);
            PhotoView zoom_image_view = (PhotoView) view.findViewById(R.id.zoom_image_view);
            ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);
            spinner.setVisibility(View.GONE);
            if (imageId != 0) {
                zoom_image_view.setImageResource(imageId);
            }
            zoom_image_view.setOnPhotoTapListener(ViewBigImageActivity.this);
            container.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    /**
     * ViewPager的适配器
     *
     * @author guolin
     */
    class ViewPagerAdapter extends PagerAdapter {

        LayoutInflater inflater;

        ViewPagerAdapter() {
            inflater = getLayoutInflater();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.viewpager_very_image, container, false);
            final PhotoView zoom_image_view = (PhotoView) view.findViewById(R.id.zoom_image_view);
            final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);
            // 保存网络图片的路径
            String adapter_image_Entity = (String) getItem(position);
            File fileImage = new File(adapter_image_Entity);

            if (fileImage.exists()) {
                tv_save_big_image.setVisibility(View.GONE);
                Glide.with(ViewBigImageActivity.this)
                        .load(fileImage)    // 指定图片地址
                        .into(zoom_image_view);
            } else {
                Glide.with(ViewBigImageActivity.this)
                        .load(adapter_image_Entity)    // 指定图片地址
                        .into(zoom_image_view);
            }

            zoom_image_view.setOnPhotoTapListener(ViewBigImageActivity.this);
            container.addView(view, 0);
            return view;
        }

        @Override
        public int getCount() {
            if (imageuri == null || imageuri.size() == 0) {
                return 0;
            }
            return imageuri.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        Object getItem(int position) {
            return imageuri.get(position);
        }
    }

    /**
     * 下面是对Viewpager的监听
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    /**
     * 本方法主要监听viewpager滑动的时候的操作
     */
    @Override
    public void onPageSelected(int arg0) {
        // 每当页数发生改变时重新设定一遍当前的页数和总页数
        very_image_viewpager_text.setText((arg0 + 1) + " / " + imageuri.size());
        page = arg0;
    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
        finish();
    }

    @Override
    public void onOutsidePhotoTap() {
        finish();
    }

}
