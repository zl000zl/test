package com.example.newtext.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Network;
import com.example.newtext.NewClass.Todaynews;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.NewType;
import com.example.newtext.StringBean.Notific;
import com.example.newtext.StringBean.Press;
import com.example.newtext.item_Adapter.Notific_adapter;
import com.example.newtext.Mytoken;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationsFragment extends Fragment {
    private Banner sbanner;
    private RecyclerView rv_notific;
    private TabLayout ntablayout;

    private List<Press> mpress = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notifications, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ntablayout = view.findViewById(R.id.ntablayout);
        initOkHttp();
        initRecycleView();

        ntablayout = getView().findViewById(R.id.ntablayout);
        LinearLayout linearLayout = (LinearLayout) ntablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(30);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.rv_line2));

    }

    private void initRecycleView() {
        rv_notific = getView().findViewById(R.id.rv_notific);
        rv_notific.setLayoutManager(new LinearLayoutManager(getActivity()));
        Network.doGet("/prod-api/press/category/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<NewType> newTypes = new Gson().fromJson(jsonObject.optString("data"), new TypeToken<List<NewType>>() {
                }.getType());
                for (NewType newType : newTypes) {
                    ntablayout.addTab(ntablayout.newTab().setText(newType.getName()).setTag(newType.getId()));
                }
                loadData(newTypes.get(0).getId());

            }
        });
        ntablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String strId = tab.getTag().toString();
                loadData(strId);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void loadData(Object strId) {
        Network.doGet("/prod-api/press/press/list?type=" + strId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Press> presses = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Press>>() {
                }.getType());
                rv_notific.setAdapter(new Notific_adapter(getActivity(), presses));
            }
        });

    }

    private void initOkHttp() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Mytoken.URl + "/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络请求异常！！！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject jsonObject1 = new JSONObject(string);
                    if (jsonObject1.optInt("code") == 200) {
                        Gson gson = new Gson();
                        List<Notific> notifics = gson.fromJson(jsonObject1.optString("rows"), new TypeToken<List<Notific>>() {
                        }.getType());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sbanner = getView().findViewById(R.id.sbanner);
                                sbanner.setImages(notifics).setImageLoader(new ImageLoader() {
                                    @Override
                                    public void displayImage(Context context, Object path, ImageView imageView) {
                                        Notific notific = (Notific) path;
                                        Glide.with(imageView).load(Mytoken.URl + notific.getAdvImg()).into(imageView);
                                    }
                                });
                                sbanner_news_init();
                                sbanner.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
                                        switch (position) {
                                            case 0:
                                            case 1:
                                            case 2:
                                                Todaynews.press = mpress.get(position);
                                                getActivity().startActivity(new Intent(getActivity(), Todaynews.class));
                                                break;
                                        }
                                    }
                                });
                                sbanner.start();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sbanner_news_init() {
        Network.doGet("/prod-api/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Press> presses = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Press>>() {
                }.getType());
                for (int i = 0; i < 3; i++) {
                    mpress.add(presses.get(i));
                    //一共有三张图片 所以循环三次
                }
            }
        });
    }
}