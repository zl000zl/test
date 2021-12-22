package com.example.newtext.WaiMai.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Adapter.ThemeAdapter;
import com.example.newtext.WaiMai.Waimaibean.Theme;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Good_food extends BaseActivity {

    private RecyclerView moduleRecycle;

    public static int shop_themeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_food);
        setTitle("美食店铺");
        initView();
    }

    private void initView() {
        moduleRecycle = (RecyclerView) findViewById(R.id.module_recycle);

        Network.doGet("/prod-api/api/takeout/seller/list?themeId=" + shop_themeId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Theme> themes = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Theme>>(){
                }.getType());

                ThemeAdapter themeAdapter = new ThemeAdapter();
                moduleRecycle.setLayoutManager(new LinearLayoutManager(Good_food.this));
                moduleRecycle.setAdapter(themeAdapter);

                themeAdapter.setThemes(themes);
                themeAdapter.notifyDataSetChanged();

                themeAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Waimai_xinq.shopId = themes.get(position).getId();
                        startActivity(new Intent(Good_food.this,Waimai_xinq.class));
                    }
                });
            }
        });
    }
}