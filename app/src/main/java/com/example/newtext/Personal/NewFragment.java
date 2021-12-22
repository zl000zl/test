package com.example.newtext.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.newtext.Personal.Activity.FeedbackActivity;
import com.example.newtext.Personal.Activity.PassActivity;
import com.example.newtext.Personal.Activity.Personal1Activity;
import com.example.newtext.R;
import com.example.newtext.SmartBus.Activity.TrueActivity;
import com.example.newtext.StringBean.User;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewFragment extends Fragment {

    private ImageView image1View;
    private TextView tvgetuser;
    private TextView tvPersonal;
    private TextView tvMyorder;
    private TextView tvPassword;
    private TextView tvOpinion;
    private Button btnQuit;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initinfo();
    }

    private void initinfo() {
        tvPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Personal1Activity.class));
            }
        });
        tvMyorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(new Intent(getActivity(), TrueActivity.class)));
            }
        });
        tvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PassActivity.class));
            }
        });
        tvOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
            }
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                if (Mytoken.token != null){
                    builder.addHeader("Authorization",Mytoken.token);
                }
                return chain.proceed(builder.build());
            }
        }).build();

        Request request = new Request.Builder().url(Mytoken.URl+"/prod-api/api/common/user/getInfo").get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络连接异常！！！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String string = response.body().string();
                try {
                    //创建JSONObject 就是键值对的形式  根据Key取到Value
                    JSONObject jsonObject1 = new JSONObject(string);
                    if(jsonObject1.optInt("code") == 200){
                        // 转换json字符串为JavaBean文件
                        // 第一步 获取gson对象
                        Gson gson = new Gson();
                        // 第二步
                        User user  = gson.fromJson(jsonObject1.optString("user"), User.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvgetuser.setText(user.getNickName());
                                Glide.with(image1View).load("https://c-ssl.duitang.com/uploads/blog/202107/09/20210709105552_7c4aa.thumb.1000_0.jpeg")
                                        .into(image1View);
                            }
                        });
                    }else{
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), jsonObject1.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {

        image1View = getView().findViewById(R.id.image1View);
        tvgetuser = getView().findViewById(R.id.tv_getuesr);
        tvPersonal = getView().findViewById(R.id.tv_personal);
        tvMyorder = getView().findViewById(R.id.tv_myorder);
        tvPassword = getView().findViewById(R.id.tv_password);
        tvOpinion = getView().findViewById(R.id.tv_opinion);
        btnQuit = getView().findViewById(R.id.btn_quit);



    }
}