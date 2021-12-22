package com.example.newtext.Movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Movie.Activity.Film_xingqing;
import com.example.newtext.Movie.Activity.Search_activity;
import com.example.newtext.Movie.Adapter.BemovieAdapter;
import com.example.newtext.Movie.Adapter.FastAdapter;
import com.example.newtext.Movie.Adapter.HotmovieAdapter;
import com.example.newtext.Movie.Adapter.RimAdapter;
import com.example.newtext.Movie.Bean.Bemovie;
import com.example.newtext.Movie.Bean.HotMovie;
import com.example.newtext.Movie.Bean.Lunbo;
import com.example.newtext.Movie.Bean.Rim_cinema;
import com.example.newtext.Movie.Bean.Weather;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.List;

public class Movie_home extends Fragment {

    private SearchView homeSearch;
    private Banner homeBanner;
    private TextView marquee;
    private RecyclerView homeRecycleView;
    private RecyclerView hotRecycle;
    private RecyclerView beRecycle;
    private RecyclerView rimRecycleView;

    public static String movie_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initweather();
        initSearch();
        initfast();
        initHot();
        initBe();
        initRim();
    }

    private void initSearch() {
        homeSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        homeSearch.setSubmitButtonEnabled(true);
        homeSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movie_name=query;
                if (Mytoken.isFastDoubleClick()){
                    return false;
                }else {
                    startActivity(new Intent(getActivity(), Search_activity.class));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String next) {
                return false;
            }
        });
    }


    private void initRim() {
        Network.doGet("/prod-api/api/movie/theatre/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Rim_cinema> rim_cinemas = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Rim_cinema>>() {
                }.getType());

                RimAdapter rimAdapter = new RimAdapter();
                rimRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                rimRecycleView.setAdapter(rimAdapter);

                rimAdapter.setRim_cinemas(rim_cinemas);
                rimAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initBe() {
        Network.doGet("/prod-api/api/movie/film/preview/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Bemovie> bemovies = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Bemovie>>() {
                }.getType());
                BemovieAdapter bemovieAdapter = new BemovieAdapter();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                beRecycle.setLayoutManager(linearLayoutManager);
                beRecycle.setAdapter(bemovieAdapter);

                bemovieAdapter.setBemovies(bemovies);
                bemovieAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initHot() {
        Network.doGet("/prod-api/api/movie/film/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<HotMovie> hotMovies = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<HotMovie>>() {
                }.getType());

                HotmovieAdapter hotmovieAdapter = new HotmovieAdapter();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                hotRecycle.setLayoutManager(linearLayoutManager);
                hotRecycle.setAdapter(hotmovieAdapter);

                hotmovieAdapter.setHotMovies(hotMovies);
                hotmovieAdapter.notifyDataSetChanged();

                hotmovieAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Film_xingqing.filmId=hotMovies.get(position).getId();
                        startActivity(new Intent(getActivity(), Film_xingqing.class));
                    }
                });
            }
        });
    }

    private void initfast() {
        homeRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        homeRecycleView.setAdapter(new FastAdapter(getActivity()));
    }

    private void initweather() {
        Network.doGet("/prod-api/api/common/weather/today", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Weather weather = new Gson().fromJson(jsonObject.optString("data"), Weather.class);

                marquee.setText("天气：" + weather.getWeather() + "，" + "当前温度：" + weather.getApparentTemperature() + "℃" + "，"
                        + "湿度：" + weather.getHumidity() + "%" + "，" + "空气质量：" + weather.getAir());
                marquee.setSelected(true);
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/rotation/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Lunbo> lunbos = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Lunbo>>() {
                }.getType());
                homeBanner.setImages(lunbos).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Lunbo lunbo = (Lunbo) path;
                        Glide.with(imageView).load(Mytoken.URl + lunbo.getAdvImg()).into(imageView);
                    }
                });
                init_Skip();
                homeBanner.start();
            }
        });
    }

    private void init_Skip() {

    }

    private void initView(View itemView) {
        homeSearch = (SearchView) getView().findViewById(R.id.home_search);
        homeBanner = (Banner) getView().findViewById(R.id.home_banner);
        marquee = (TextView) getView().findViewById(R.id.marquee);
        homeRecycleView = (RecyclerView) getView().findViewById(R.id.home_recycleView);
        hotRecycle = (RecyclerView) getView().findViewById(R.id.hot_recycle);
        beRecycle = (RecyclerView) getView().findViewById(R.id.be_recycle);
        rimRecycleView = (RecyclerView) getView().findViewById(R.id.rim_recycleView);
    }
}