package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

public class SharedPreferencesUtil {

    private static SharedPreferences.Editor editor;

    //添加ui模式
    public static void addModeUI(Context context,boolean bool){
        editor = context.getSharedPreferences("ModeUI",Context.MODE_PRIVATE).edit();
        editor.putBoolean("mode_ui",bool);
        editor.commit();
    }

    /**
     * 是否是夜间模式
     * @param context
     * @return
     */
    public static int getNight(Context context){
        boolean bool = context.getSharedPreferences("ModeUI",Context.MODE_PRIVATE).getBoolean("mode_ui",false);
        return bool ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
    }


    /**
     * 添加用户token
     * @param context
     * @param token
     */
    public static void addUserToken(Context context,String token){
        editor = context.getSharedPreferences("user",Context.MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.commit();
    }

    /**
     * 获取用户token
     * @param context
     * @return
     */
    public static String getToken(Context context){
        return context.getSharedPreferences("user",Context.MODE_PRIVATE).getString("token","");
    }

}
