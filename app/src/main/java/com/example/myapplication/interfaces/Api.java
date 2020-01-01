package com.example.myapplication.interfaces;


import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.EvaluationSubmitBean;
import com.example.myapplication.bean.ExercisesBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.bean.PorfolioBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.bean.TypeIndexBean;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.bean.UserInfoUpdateBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @POST("index/user/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("mobile") String mobile, @Field("password") String password);

    @POST("index/train/index")
    @FormUrlEncoded
    Flowable<IndexBean> getIndex(@Header("x-access-token") String token, @FieldMap Map<String, String> map);

    @POST("index/train/type_index")
    @FormUrlEncoded
    Flowable<TypeIndexBean> getTypeIndex(@Header("x-access-token") String token, @Field("type") String type, @Field("page") String page);

    @POST("index/train/curriculum_show")
    @FormUrlEncoded
    Flowable<CurriculumBean> getCurriculum(@Header("x-access-token") String token, @Field("curriculum_id") String curriculum_id);

    @POST("index/train/evaluation_show")
    @FormUrlEncoded
    Flowable<ExercisesBean> getEvaluation(@Header("x-access-token") String token, @Field("curriculum_id") String curriculum_id);

    @POST("index/train/evaluation_submit")
    @FormUrlEncoded
    Flowable<EvaluationSubmitBean> submitEvaluation(@Header("x-access-token") String token, @Field("curriculum_id") String curriculum_id, @Field("answer") String answer);


    //个人中心
    @POST("index/train/user_center")
    Flowable<UserCenterBean> usercenter(@Header("x-access-token") String token);

    @POST("index/train/userinfo_update")
    @FormUrlEncoded
    Flowable<UserInfoUpdateBean> updateUserInfo(@Header("x-access-token") String token, @Field("nickname") String nickname, @Field("zw") String zw, @Field("avatar") String avatar);


    @POST("index/train/study_record")
    @FormUrlEncoded
    Flowable<ToadayBean> porfolio(@Header("x-access-token") String token, @Field("type") String type, @Field("page") String page);
}


