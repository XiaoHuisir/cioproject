package com.example.myapplication.app;

import java.io.File;

public class Constant {

    //    public static final String URl = "http://123.206.58.124:8025/index/user/login";
    public static final String URl = "http://sc.minxj.com/api/";
    public static final String BaseUrl = "http://123.206.58.124:8025/";
    public static final String IndexBaseUrl = "http://www.sosoapi.com/pass/mock/";

    //网络缓存的地址
    public static final String PATH_DATA = MyApp.mApp.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/ShiXun";

    public static String token = "";

    public static String NUM_VIEW = "";

    public static int CourseType = 2;

    public static String mobiles = "";
    public static String passwords = "";

    public static final int STATUS = 1;
    public static final int ZERO = 0;
    public static final int COURSE_TYPE_1 = 1; //课外学习
    public static final int COURSE_TYPE_2 = 2; //培训

    public static final int STUDY_TYPE_1 = 0; //智慧课堂
    public static final int STUDY_TYPE_2 = 1; //内部培训
    public static final int STUDY_TYPE_3 = 2; //其他培训


    public static String CURTYPE = "";
    public static final String STUD_TYPE_0 = "0";
    public static final String STUD_TYPE_1 = "1";
    public static final String STUD_TYPE_2 = "2";

}
