package com.example.newtext.Movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.Activity.Film_xingqing;
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

public class Movie_film extends Fragment {
    private RecyclerView ypRecycle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/film/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<HotMovie> hotMovies = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<HotMovie>>() {
                }.getType());

                YpAdapter ypAdapter = new YpAdapter();

                ypRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                ypRecycle.setAdapter(ypAdapter);

                ypAdapter.setHotMovies(hotMovies);
                ypAdapter.notifyDataSetChanged();

                ypAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Film_xingqing.filmId=hotMovies.get(position).getId();
                        startActivity(new Intent(getActivity(), Film_xingqing.class));
                    }
                });
            }
        });
    }

    private void initView(View getView) {
        ypRecycle = (RecyclerView) getView.findViewById(R.id.yp_recycle);
    }
}