package com.example.myapplication.interfaces;


import com.example.myapplication.bean.CurriculumBean;
import com.example.myapplication.bean.DownFileBean;
import com.example.myapplication.bean.EvaluatShowResultBean;
import com.example.myapplication.bean.EvaluationSubmitBean;
import com.example.myapplication.bean.ExercisesBean;
import com.example.myapplication.bean.IndexBean;
import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.bean.MyfilelistBean;
import com.example.myapplication.bean.NotcieRecordBean;
import com.example.myapplication.bean.NoticeListBean;
import com.example.myapplication.bean.SearchBean;
import com.example.myapplication.bean.ToadayBean;
import com.example.myapplication.bean.TokenBean;
import com.example.myapplication.bean.TypeIndexBean;
import com.example.myapplication.bean.UnredNoticeBean;
import com.example.myapplication.bean.UserCenterBean;
import com.example.myapplication.bean.UserInfoUpdateBean;
import com.example.myapplication.bean.VerBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
    @Headers({
            "Content-type:application/json"
    })
    Flowable<EvaluationSubmitBean> submitEvaluation(@Header("x-access-token") String token, @Body RequestBody body);

    @POST("index/train/evaluation_result_show")
    @FormUrlEncoded
    Flowable<EvaluatShowResultBean> showEvaluationResult(@Header("x-access-token") String token, @Field("evaluat_id") String evaluatId);


    //个人中心
    @POST("index/train/user_center")
    Flowable<UserCenterBean> usercenter(@Header("x-access-token") String token);

    @POST("index/train/userinfo_update")
    @FormUrlEncoded
    Flowable<UserInfoUpdateBean> updateUserInfo(@Header("x-access-token") String token, @Field("nickname") String nickname,
                                                @Field("zw") String zw, @Field("avatar") String avatar);

    @POST("index/user/get_token")
    Flowable<TokenBean> getToken(@Header("x-access-token") String token);

    @POST("index/train/study_record")
    @FormUrlEncoded
    Flowable<ToadayBean> porfolio(@Header("x-access-token") String token, @Field("type") String type, @Field("page") String page);

    @POST("index/train/my_file_list")
    @FormUrlEncoded
    Flowable<MyfilelistBean> myfilelist(@Header("x-access-token") String token, @Field("page") int page);


    @POST("index/train/get_unread_notice_num")
    Flowable<UnredNoticeBean> unreadNotice(@Header("x-access-token") String token);


    @POST("index/train/notice_list")
    @FormUrlEncoded
    Flowable<NoticeListBean> noticeList(@Header("x-access-token") String token, @Field("page") String page);

    @POST("index/train/curriculum_serach")
    @FormUrlEncoded
    Flowable<SearchBean> search(@Header("x-access-token") String token, @Field("keyword") String keyword, @Field("type") String type, @Field("page") String page);


    @POST("index/train/down_file")
    @FormUrlEncoded
    Flowable<DownFileBean> downfile(@Header("x-access-token") String token, @FieldMap Map<String, String> map);

    @POST("index/train/evaluation_record")
    @FormUrlEncoded
    Flowable<NotcieRecordBean> notice_records(@Header("x-access-token") String token, @Field("page") String page);
    @POST("index/train/version_update")
    @FormUrlEncoded
    Flowable<VerBean> getVersionInfo(@Header("x-access-token") String token);

}


