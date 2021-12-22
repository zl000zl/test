package com.example.newtext.Movie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.WnewAdapter;
import com.example.newtext.Movie.cinemaBean.Wnews;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Movie_news extends BaseActivity {

    private RecyclerView MnewsRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_news);
        setTitle("娱乐新闻");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Wnews> wnews = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Wnews>>(){
                }.getType());

                WnewAdapter wnewAdapter = new WnewAdapter();
                MnewsRecycle.setLayoutManager(new LinearLayoutManager(Movie_news.this));
                MnewsRecycle.setAdapter(wnewAdapter);

                wnewAdapter.setWnews(wnews);
                wnewAdapter.notifyDataSetChanged();

                wnewAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        News_xingq.newsId = wnews.get(position).getId();
                        startActivity(new Intent(Movie_news.this,News_xingq.class));
                    }
                });
            }
        });
    }

    private void initView() {
        MnewsRecycle = (RecyclerView) findViewById(R.id.Mnews_recycle);
    }
}