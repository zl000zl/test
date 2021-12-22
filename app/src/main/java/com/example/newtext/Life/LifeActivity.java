package com.example.newtext.Life;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Life.Activity.UserActivity;
import com.example.newtext.Life.Adapter.LifeAdapter;
import com.example.newtext.Life.Adapter.ZhixunAdapter;
import com.example.newtext.Life.LifeBean.Jiaofei;
import com.example.newtext.Life.LifeBean.LifeShow;
import com.example.newtext.Life.LifeBean.Zhixun;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.List;

public class LifeActivity extends BaseActivity {

    private Banner banner;
    private RecyclerView lifeRecycle;
    private RecyclerView zhixunRecycle;
    private Button btnAuto;
    private Button btnGuanli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        setTitle("生活缴费");
        initView();
        initData();
        initRecycleView();
        initzhuxun();
        initClick();
    }

    private void initClick() {
        btnGuanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LifeActivity.this, UserActivity.class));
            }
        });
    }

    private void initzhuxun() {
        Network.doGet("/prod-api/api/living/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Zhixun> zhixuns = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Zhixun>>() {
                }.getType());
                zhixunRecycle.setLayoutManager(new GridLayoutManager(LifeActivity.this, 2));
                zhixunRecycle.setAdapter(new ZhixunAdapter(LifeActivity.this, zhixuns));
            }
        });
    }

    private void initRecycleView() {
        Network.doGet("/prod-api/api/living/category/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Jiaofei> jiaofeis = new Gson().fromJson(jsonObject.optString("data"), new TypeToken<List<Jiaofei>>() {
                }.getType());
                lifeRecycle.setLayoutManager(new GridLayoutManager(LifeActivity.this, 4));
                lifeRecycle.setAdapter(new LifeAdapter(LifeActivity.this, jiaofeis));
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/living/rotation/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<LifeShow> lifeShows = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<LifeShow>>() {
                }.getType());
                banner.setImages(lifeShows).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        LifeShow lifeShow = (LifeShow) path;
                        Glide.with(imageView).load(Mytoken.URl + lifeShow.getAdvImg()).into(imageView);
                    }
                }).start();
            }
        });
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        lifeRecycle = (RecyclerView) findViewById(R.id.life_recycle);
        zhixunRecycle = (RecyclerView) findViewById(R.id.zhixun_recycle);
        btnAuto = (Button) findViewById(R.id.btn_auto);
        btnGuanli = (Button) findViewById(R.id.btn_guanli);
    }
}