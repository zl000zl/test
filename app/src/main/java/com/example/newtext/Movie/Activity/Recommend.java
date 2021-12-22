package com.example.newtext.Movie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.YpAdapter;
import com.example.newtext.Movie.Bean.HotMovie;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Recommend extends BaseActivity {

    private RecyclerView yqRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        setTitle("推荐影片");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/film/list?recommend=Y", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<HotMovie> hotMovies = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<HotMovie>>() {
                }.getType());

                YpAdapter ypAdapter = new YpAdapter();

                yqRecycle.setLayoutManager(new LinearLayoutManager(Recommend.this));
                yqRecycle.setAdapter(ypAdapter);

                ypAdapter.setHotMovies(hotMovies);
                ypAdapter.notifyDataSetChanged();

                ypAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Film_xingqing.filmId=hotMovies.get(position).getId();
                        startActivity(new Intent(Recommend.this, Film_xingqing.class));
                    }
                });
            }
        });
    }

    private void initView() {
        yqRecycle = (RecyclerView) findViewById(R.id.yq_recycle);
    }
}