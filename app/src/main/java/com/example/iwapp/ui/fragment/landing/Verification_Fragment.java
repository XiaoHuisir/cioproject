package com.example.iwapp.ui.fragment.landing;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwapp.R;
import com.example.iwapp.base.BaseFragment;
import com.example.iwapp.interfaces.IBasePresenter;
import com.example.iwapp.ui.acivity.login.VerifyActivity;
import com.example.iwapp.utils.MD5Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Verification_Fragment extends BaseFragment {


    @BindView(R.id.et_phones)
    EditText mEtPhones;
    @BindView(R.id.bt_landings)
    Button mBtLandings;
    @BindView(R.id.els)
    TextView mEls;
    @BindView(R.id.weixin)
    ImageView mWeixin;
    private String android_code = "a01973b3-38a2-4260-8caa-00c4b53e6d86";
    private String url = "http://sc.minxj.com/api/smsCode";
    private String secret_key = "m$c2Bk3kb^J$7MDE";
    private String code = "132153";
    private String phone;

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verification_;
    }

    @Override
    protected void initView() {

        mBtLandings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mEtPhones.getText().toString();
                int length = phone.length();
                if (phone.equals("")) {
                    Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (length<11) {
                    Toast.makeText(getActivity(), "请输入正规的手机号码", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String, String> map = new HashMap<>();
                    map.put("phone", phone);
                    map.put("code", code);
                    map.put("mode", "2");

                    final String sign = getSign(map);

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request head = chain.request().newBuilder()
                                            .addHeader("device-id", android_code)
                                            .build();
                                    return chain.proceed(head);
                                }
                            })
                            .build();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("mode", "2")
                            .add("phone", phone)
                            .add("code", code)
                            .add("sign", sign)
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(requestBody)
                            .build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i("error:", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) {
                            String result = null;
                            if (response != null) {
                                try {
                                    result = response.body().string();
                                    Log.i("response:", result);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    Intent intent = new Intent(getActivity(), VerifyActivity.class);
                    startActivity(intent);

                }
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {


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
            Log.i("Tab",result);
            //进行MD5加密
            result = MD5Utils.MD5Encode(result+secret_key,"utf8").toUpperCase();
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}
