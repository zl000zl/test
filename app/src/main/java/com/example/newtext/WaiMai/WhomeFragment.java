package com.example.newtext.WaiMai;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Activity.Good_food;
import com.example.newtext.WaiMai.Activity.Waimai_xinq;
import com.example.newtext.WaiMai.Adapter.ModuleAdapter;
import com.example.newtext.WaiMai.Adapter.NearbyAdapter;
import com.example.newtext.WaiMai.Adapter.ShopAdapter;
import com.example.newtext.WaiMai.Waimaibean.Goodshop;
import com.example.newtext.WaiMai.Waimaibean.Module;
import com.example.newtext.WaiMai.Waimaibean.Nearby;
import com.example.newtext.WaiMai.Waimaibean.Slideshow;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.List;

public class WhomeFragment extends Fragment {

    private Banner banner;
    private RecyclerView takeRecycle;
    private RecyclerView good_shop;
    private RecyclerView takeStore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_whome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initBanner();
        initModule();
        initGoodshop();
        initNearby();
    }

    private void initNearby() {
        Network.doGet("/prod-api/api/takeout/seller/near", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Nearby> nearbies = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Nearby>>(){
                }.getType());
                NearbyAdapter nearbyAdapter = new NearbyAdapter();
                takeStore.setLayoutManager(new LinearLayoutManager(getActivity()));
                takeStore.setAdapter(nearbyAdapter);

                nearbyAdapter.setNearbies(nearbies);
                nearbyAdapter.notifyDataSetChanged();

                nearbyAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Waimai_xinq.shopId=nearbies.get(position).getId();
                        getActivity().startActivity(new Intent(getActivity(),Waimai_xinq.class));
                    }
                });
            }
        });
    }

    private void initModule() {
        Network.doGet("/prod-api/api/takeout/theme/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Module> modules = new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<Module>>(){
                }.getType());
                ModuleAdapter moduleAdapter = new ModuleAdapter();
                takeRecycle.setLayoutManager(new GridLayoutManager(getActivity(),5));
                takeRecycle.setAdapter(moduleAdapter);
                moduleAdapter.setModules(modules);
                moduleAdapter.notifyDataSetChanged();

                moduleAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        switch (position){
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                Good_food.shop_themeId = modules.get(position).getId();
                                getActivity().startActivity(new Intent(getActivity(), Good_food.class));
                                break;
                        }
                    }
                });
            }
        });
    }

    private void initGoodshop() {
        Network.doGet("/prod-api/api/takeout/seller/list?recommend=Y", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Goodshop> goodshops = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Goodshop>>(){
                }.getType());
                ShopAdapter shopAdapter = new ShopAdapter();
                good_shop.setLayoutManager(new GridLayoutManager(getActivity(),2));
                good_shop.setAdapter(shopAdapter);

                shopAdapter.setGoodshops(goodshops);
                shopAdapter.notifyDataSetChanged();

                shopAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Waimai_xinq.shopId=goodshops.get(position).getId();
                        getActivity().startActivity(new Intent(getActivity(),Waimai_xinq.class));
                    }
                });
            }
        });
    }

    private void initBanner() {
        Network.doGet("/prod-api/api/takeout/rotation/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<Slideshow> slideshows = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Slideshow>>(){
                    }.getType());
                    banner.setImages(slideshows).setImageLoader(new ImageLoader(){

                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Slideshow slideshow = (Slideshow) path;
                            Glide.with(imageView).load(Mytoken.URl+slideshow.getAdvImg()).into(imageView);
                        }
                    }).start();
                }
            }
        });
    }


    private void initView(View itemView) {
        banner = (Banner) getView().findViewById(R.id.banner);
        takeRecycle = (RecyclerView) getView().findViewById(R.id.take_recycle);
        good_shop = (RecyclerView) getView().findViewById(R.id.good_shop);
        takeStore = (RecyclerView) getView().findViewById(R.id.take_store);
    }
}