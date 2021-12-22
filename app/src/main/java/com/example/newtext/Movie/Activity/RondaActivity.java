package com.example.newtext.Movie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.HallAdapter;
import com.example.newtext.Movie.Bean.Film_xq;
import com.example.newtext.Movie.Bean.Session;
import com.example.newtext.Mytoken;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class RondaActivity extends BaseActivity {

    private TextView cinName;
    private TextView cinAddress;
    private ImageView rondaImages;
    private TextView rondaName;
    private TextView rondaFen;
    private RecyclerView cinRecycle;

    public static String cinname;
    public static String cinaddress;

    public static String date_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ronda);
        setTitle("看电影");
        initView();
        initData();
        initDots();
        initList();
    }



    private void initList() {
        Network.doGet("/prod-api/api/movie/theatre/times/list?movieId=" + Film_xingqing.filmId + "&playDate=" + date_time, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Session> sessions = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Session>>(){
                }.getType());
                HallAdapter hallAdapter = new HallAdapter();
                cinRecycle.setLayoutManager(new LinearLayoutManager(RondaActivity.this));
                cinRecycle.setAdapter(hallAdapter);

                hallAdapter.setSessions(sessions);
                hallAdapter.notifyDataSetChanged();

                hallAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        SeatActivity.date_seat = sessions.get(position).getPlayDate();
                        SeatActivity.time_seat = sessions.get(position).getStartTime()+"-"+sessions.get(position).getEndTime();
                        SeatActivity.type_seat = sessions.get(position).getPlayType();

                        SeatActivity.movie_money = sessions.get(position).getPrice();

                        Ticker_order.MovieId = sessions.get(position).getMovieId();

                        Ticker_order.hallId = sessions.get(position).getRoomId();
                        Ticker_order.cinemaId = sessions.get(position).getTheaterId();
                        Ticker_order.sessionId = sessions.get(position).getId();

                        Ticker_order.start_time = sessions.get(position).getStartTime();
                        Ticker_order.end_time = sessions.get(position).getEndTime();
                        startActivity(new Intent(RondaActivity.this,SeatActivity.class));
                    }
                });
            }
        });
    }

    private void initDots() {
        Network.doGet("/prod-api/api/movie/film/detail/" + Film_xingqing.filmId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Film_xq film_xq = new Gson().fromJson(jsonObject.optString("data"), Film_xq.class);
                Glide.with(rondaImages).load(Mytoken.URl + film_xq.getCover()).into(rondaImages);
                rondaName.setText("" + film_xq.getName());
                rondaFen.setText("评分：" + (film_xq.getScore() + film_xq.getScore()));
            }
        });
    }

    private void initData() {
        cinName.setText(cinname);
        cinAddress.setText("地址：" + cinaddress);
    }

    private void initView() {
        cinName = (TextView) findViewById(R.id.cin_name);
        cinAddress = (TextView) findViewById(R.id.cin_address);

        rondaImages = (ImageView) findViewById(R.id.ronda_images);
        rondaName = (TextView) findViewById(R.id.ronda_name);
        rondaFen = (TextView) findViewById(R.id.ronda_fen);
        cinRecycle = (RecyclerView) findViewById(R.id.cin_recycle);
    }
}