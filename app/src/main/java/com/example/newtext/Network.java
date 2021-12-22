package com.example.newtext;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Network {
    private static String token= Mytoken.token;
    private static String baseUrl = Mytoken.URl;
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    if (token != null) {
                        builder.addHeader("Authorization", token);
                    }
                    return chain.proceed(builder.build());
                }
            }).build();
    private static final MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");

    public static void doGet(String path, OkResult okResult) {
        Request request = new Request.Builder().url(baseUrl + path).get().build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }

    public static void doPost(String path, String params, OkResult okResult) {
        Request request = new Request.Builder().url(baseUrl + path).post(RequestBody.create(params, JSON)).build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }
    public static void doPut(String path, String params, OkResult okResult) {
        Request request = new Request.Builder().url(baseUrl + path).put(RequestBody.create(params, JSON)).build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }




    private static final Handler MHANDLER = new Handler(Looper.getMainLooper());

    static class OkCallback implements Callback {
        private final OkResult okResult;

        public OkCallback(OkResult okResult) {
            this.okResult = okResult;
        }

        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            e.printStackTrace();
            MHANDLER.post(new Runnable() {
                @Override
                public void run() {

                }
            });
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            String string = Objects.requireNonNull(response.body()).string();
            MHANDLER.post(() -> {
                try {
                    okResult.succes(new JSONObject(string));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static class OkData {
        private final Request request;
        private final OkResult okResult;

        public OkData(Request request, OkResult okResult) {
            this.request = request;
            this.okResult = okResult;
        }
    }

    public static void doImage(Context context ,String path, ImageView imageView) {
        Glide.with(context).load(baseUrl + path).into(imageView);
    }
    public static void doAddress(Context context, TextView textView){
        CityPickerView cityPicker = new CityPickerView();
        cityPicker.init(context);
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityPicker.setConfig(cityConfig);
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                textView.setText(province+"-"+city+"-"+district);
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }
        });
        cityPicker.showCityPicker();
    }
}


