package com.example.newtext.Hospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Hospital.Activity.XqActivity;
import com.example.newtext.Hospital.Adapter.HomeAdapter;
import com.example.newtext.Hospital.Adapter.HotAdapter;
import com.example.newtext.Hospital.Adapter.LanmuAdapter;
import com.example.newtext.HospitalBean.Hot;
import com.example.newtext.HospitalBean.One;
import com.example.newtext.HospitalBean.Picture;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.List;

public class mHomeFragment extends Fragment {
    public static int Hospital_id;
    private Banner banner;
    private RecyclerView recycleView;
    private RecyclerView recycleView2;
    private RecyclerView homeRecycleView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_m_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initBanner();
        initRecycleView();
        initRecycleView2();
        initHome();
    }

    private void initHome() {
        Network.doGet("/prod-api/api/hospital/hospital/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<One> ones = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<One>>(){
                }.getType());
                homeRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                homeRecycleView.setAdapter(new HomeAdapter(getActivity(),ones));
            }
        });
    }
    private void initRecycleView2() {
        Network.doGet("/prod-api/api/hospital/category/list?type=1&pageNum=1&pageSize=8", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Hot> hots = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Hot>>() {
                }.getType());
                recycleView2.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                recycleView2.setAdapter(new HotAdapter(getActivity(), hots));
            }
        });
    }

    private void initRecycleView() {
        recycleView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recycleView.setAdapter(new LanmuAdapter(getActivity()));
    }

    private void initBanner() {
        Network.doGet("/prod-api/api/hospital/banner/list?hospitalId=1", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Picture> pictures = new Gson().fromJson(jsonObject.optString("data"), new TypeToken<List<Picture>>() {
                }.getType());
                banner.setImages(pictures).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Picture picture = (Picture) path;
                        Glide.with(imageView).load(Mytoken.URl + picture.getImgUrl()).into(imageView);
                    }
                });
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        switch (position) {
                            case 0:
                            case 1:
                            case 2:
                                XqActivity.Hospital_id = pictures.get(position).getHospitalId();
                                getActivity().startActivity(new Intent(getActivity(), XqActivity.class));
                                break;
                        }
                    }
                }).start();

            }
        });
    }

    private void initView(View getView) {
        banner = (Banner) getView.findViewById(R.id.banner);
        recycleView = (RecyclerView) getView.findViewById(R.id.recycleView);
        recycleView2 = (RecyclerView) getView.findViewById(R.id.recycleView2);
        homeRecycleView = (RecyclerView)getView.findViewById(R.id.home_recycleView);
    }
}