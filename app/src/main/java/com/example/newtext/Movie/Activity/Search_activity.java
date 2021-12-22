package com.example.newtext.Movie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.Search_fime;
import com.example.newtext.Movie.Bean.HotMovie;
import com.example.newtext.Movie.Movie_home;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Search_activity extends BaseActivity {

    private RecyclerView searchRecy;
    private TextView seVisibility;

    public static int se_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);
        setTitle("搜索详情");
        initView();
        initSearch_content();
    }

    private void initSearch_content() {
        Network.doGet("/prod-api/api/movie/film/list?name=" + Movie_home.movie_name, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<HotMovie> hotMovies = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<HotMovie>>() {
                }.getType());
                if (hotMovies.size() != 0) {
                    Search_fime search_fime = new Search_fime();
                    searchRecy.setLayoutManager(new LinearLayoutManager(Search_activity.this));
                    searchRecy.setAdapter(search_fime);

                    search_fime.setHotMovies(hotMovies);
                    search_fime.notifyDataSetChanged();
                    search_fime.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Film_xingqing.filmId=hotMovies.get(position).getId();
                            startActivity(new Intent(Search_activity.this,Film_xingqing.class));
                        }
                    });
                } else {
                    seVisibility.setVisibility(View.VISIBLE);
                    searchRecy.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initView() {
        searchRecy = (RecyclerView) findViewById(R.id.search_recy);
        seVisibility = (TextView) findViewById(R.id.se_visibility);
    }
}