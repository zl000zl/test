package com.example.newtext.Movie.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.PlAdapter;
import com.example.newtext.Movie.Bean.Film_xq;
import com.example.newtext.Movie.Bean.Pl_film;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Film_xingqing extends BaseActivity {

    private ImageView filmImage;
    private TextView filmName;
    private TextView filmTitle;
    private RatingBar filmRab;
    private TextView filmDate;
    private TextView filmThink;
    private TextView filmLike;
    private TextView filmJieshao;
    private TextView plNumber;
    private ImageView plSkip;
    private RecyclerView filmRecycle;
    private Button filmShop;

    public static int filmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_xingqing);
        setTitle("影片详情");
        initView();
        initData();
        initRecycle();
        initClick();
        initSkip();
    }

    private void initSkip() {
        filmShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Film_xingqing.this,Buy_ticket.class));
            }
        });
    }

    private void initClick() {
        plSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Film_xingqing.this, User_filmcom.class));
            }
        });
    }

    private void initRecycle() {
        Network.doGet("/prod-api/api/movie/film/comment/list?movieId=" + filmId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Pl_film> pl_films = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Pl_film>>() {
                }.getType());
                filmRecycle.setLayoutManager(new LinearLayoutManager(Film_xingqing.this));
                filmRecycle.setAdapter(new PlAdapter(Film_xingqing.this, pl_films));
                plNumber.setText("总数："+jsonObject.optString("total"));
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/film/detail/" + filmId, new OkResult() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void succes(JSONObject jsonObject) {
                Film_xq film_xq = new Gson().fromJson(jsonObject.optString("data"), Film_xq.class);
                Glide.with(filmImage).load(Mytoken.URl + film_xq.getCover()).into(filmImage);
                filmName.setText("" + film_xq.getName());
                filmTitle.setText("" + film_xq.getLanguage());
                filmRab.setRating((int) +film_xq.getScore());
                filmDate.setText("上映时间：" + film_xq.getPlayDate());
                filmThink.setText("想看：" + film_xq.getLikeNum() + "人");
                filmLike.setText("喜欢：" + film_xq.getFavoriteNum() + "人");
                filmJieshao.setText(Html.fromHtml("简介：" + film_xq.getIntroduction(), 1));

                User_filmcom.filmName = film_xq.getName();
                User_filmcom.Movie_id = film_xq.getId();
            }
        });
    }

    private void initView() {
        filmImage = (ImageView) findViewById(R.id.film_image);
        filmName = (TextView) findViewById(R.id.film_name);
        filmTitle = (TextView) findViewById(R.id.film_title);
        filmRab = (RatingBar) findViewById(R.id.film_rab);
        filmDate = (TextView) findViewById(R.id.film_date);
        filmThink = (TextView) findViewById(R.id.film_think);
        filmLike = (TextView) findViewById(R.id.film_like);
        filmJieshao = (TextView) findViewById(R.id.film_jieshao);
        filmRecycle = (RecyclerView) findViewById(R.id.film_recycle);
        filmShop = (Button) findViewById(R.id.film_shop);
        plNumber = (TextView) findViewById(R.id.pl_number);
        plSkip = (ImageView) findViewById(R.id.pl_skip);
    }
}