package com.example.newtext.Movie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.YgAdapter;
import com.example.newtext.Movie.Bean.Bemovie;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Trailers extends BaseActivity {

    private RecyclerView ygRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers);
        setTitle("影片预告");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/film/preview/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Bemovie> bemovies = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Bemovie>>() {
                }.getType());
                YgAdapter ygAdapter = new YgAdapter();
                ygRecycle.setLayoutManager(new LinearLayoutManager(Trailers.this));
                ygRecycle.setAdapter(ygAdapter);

                ygAdapter.setBemovies(bemovies);
                ygAdapter.notifyDataSetChanged();

                ygAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Film_play.Video_Id = bemovies.get(position).getId();
                        startActivity(new Intent(Trailers.this,Film_play.class));
                    }
                });
            }
        });
    }

    private void initView() {
        ygRecycle = (RecyclerView) findViewById(R.id.yg_recycle);
    }
}