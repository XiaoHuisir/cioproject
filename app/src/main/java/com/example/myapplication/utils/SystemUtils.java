package com.example.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.myapplication.app.MyApp;


public class SystemUtils {

    /**
     * 检查是否有网络
     *
     * @return
     */
    public static boolean checkNetWork() {
            ConnectivityManager manager = (ConnectivityManager) MyApp.mApp.getSystemService(Context.CONNECTIVITY_SERVICE);
            return manager.getActiveNetworkInfo() != null;
    }


    /**
     * 当前是否是wifi链接
     * @return
     */
    public static boolean isWifiConnected(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.mApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null;
    }

    /**
     * 检查手机（4,3,2）G是否链接
     */
    public static boolean isMobileNetworkConnected(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.mApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null;
    }

    /**
     * 隐藏键盘
     * @param context
     * @param editText
     */
    public static void hintKeyBoard(Context context, EditText editText) {
        //拿到InputMethodManager
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }
}
