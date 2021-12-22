package com.example.newtext.Movie.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Bean.Film_xq;
import com.example.newtext.Movie.Bean.Order_post;
import com.example.newtext.Mytoken;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Ticker_order extends BaseActivity {

    private ImageView tickerImage;
    private TextView tickerName;
    private TextView tickerTitle;
    private RatingBar tickerRab;
    private TextView tickerDate;
    private TextView tickerThink;
    private TextView tickerLike;
    private TextView tickerStart;
    private TextView tickerEnd;
    private TextView tickerSum;
    private Button tickerEnter;

    public static int MovieId;

    public static int paiId;
    public static int zuoId;
    public static int hallId;
    public static int cinemaId;
    public static int sessionId;

    public static String start_time;
    public static String end_time;
    public static String moneySum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker_order);
        initView();
        initData();
        initpost();
    }

    private void initpost() {
        tickerStart.setText("开始："+start_time);
        tickerEnd.setText("结束："+end_time);
        tickerSum.setText(""+moneySum);


        Order_post orderPost = new Order_post();
        orderPost.setMovieId(MovieId);
        orderPost.setRoomId(hallId);
        orderPost.setTheaterId(cinemaId);
        orderPost.setTimesId(sessionId);
        orderPost.setPrice(Double.parseDouble(moneySum));
        orderPost.setSeatId(7070);

        tickerEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Network.doPost("/prod-api/api/movie/ticket", new Gson().toJson(orderPost), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(Ticker_order.this, "购票成功！！！", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(Ticker_order.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/film/detail/" + MovieId, new OkResult() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void succes(JSONObject jsonObject) {
                Film_xq film_xq = new Gson().fromJson(jsonObject.optString("data"), Film_xq.class);
                Glide.with(tickerImage).load(Mytoken.URl + film_xq.getCover()).into(tickerImage);
                tickerName.setText("" + film_xq.getName());
                tickerTitle.setText("" + film_xq.getLanguage());
                tickerRab.setRating((int) +film_xq.getScore());
                tickerDate.setText("上映时间：" + film_xq.getPlayDate());
                tickerThink.setText("想看：" + film_xq.getLikeNum() + "人");
                tickerLike.setText("喜欢：" + film_xq.getFavoriteNum() + "人");
            }
        });
    }

    private void initView() {
        tickerImage = (ImageView) findViewById(R.id.ticker_image);
        tickerName = (TextView) findViewById(R.id.ticker_name);
        tickerTitle = (TextView) findViewById(R.id.ticker_title);
        tickerRab = (RatingBar) findViewById(R.id.ticker_rab);
        tickerDate = (TextView) findViewById(R.id.ticker_date);
        tickerThink = (TextView) findViewById(R.id.ticker_think);
        tickerLike = (TextView) findViewById(R.id.ticker_like);
        tickerStart = (TextView) findViewById(R.id.ticker_start);
        tickerEnd = (TextView) findViewById(R.id.ticker_end);
        tickerSum = (TextView) findViewById(R.id.ticker_sum);
        tickerEnter = (Button) findViewById(R.id.ticker_enter);
    }
}