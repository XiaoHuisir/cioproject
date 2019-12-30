package com.example.myapplication.ui.fragment.landing;

import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.interfaces.IBasePresenter;
import com.example.myapplication.utils.MD5Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Landing_Fragment extends BaseFragment{


    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_passage)
    EditText mEtPassage;
    @BindView(R.id.forget)
    TextView mForget;
    @BindView(R.id.bt_landing)
    Button mBtLanding;
    @BindView(R.id.methods)
    TextView mMethods;
    private View view;
    private Unbinder unbinder;
    private String android_code = "a01973b3-38a2-4260-8caa-00c4b53e6d86";
    private String url = "http://sc.minxj.com/api/login";
    private String secret_key = "m$c2Bk3kb^J$7MDE";
    private String phone;
    private String passage;

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_landing_;
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        mEtPassage.setTransformationMethod(PasswordTransformationMethod.getInstance());

        mBtLanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mEtPhone.getText().toString();
                passage = mEtPassage.getText().toString();
                Map<String,String> map = new HashMap<>();
                map.put("phone",phone);
                map.put("password",passage);
                map.put("mode","1");

                final String sign = getSign(map);

                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request head = chain.request().newBuilder()
                                        .addHeader("device-id",android_code)
                                        .build();
                                return chain.proceed(head);
                            }
                        })
                        .build();
                RequestBody requestBody = new FormBody.Builder()
                        .add("mode","1")
                        .add("phone",phone)
                        .add("password",passage)
                        .add("sign",sign)
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("error:",e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response){
                        String result = null;
                        try {
                            result = response.body().string();
                            Log.i("response:",result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public String getSign(Map<String,String> map){
        String result = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null)) {
                        sb.append(key + "=" + val + "&");
                    }
                }
            }
            sb = sb.deleteCharAt(sb.length()-1);
            result = sb.toString();

            //进行MD5加密
            result = MD5Utils.MD5Encode(result+secret_key,"utf8").toUpperCase();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

}
