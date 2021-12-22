package com.example.newtext.Movie.Activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.cinemaBean.Yg_xingq;
import com.example.newtext.Mytoken;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Film_play extends BaseActivity {

    private VideoView ygVideo;
    private TextView ygJieshao;

    public static int Video_Id;

    Yg_xingq yg_xingq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_play);
        setTitle("影片播放");
        initView();
        initData();
    }
    private void initData() {
        Network.doGet("/prod-api/api/movie/film/preview/"+Video_Id , new OkResult() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void succes(JSONObject jsonObject) {
                yg_xingq= new Gson().fromJson(jsonObject.optString("data"),Yg_xingq.class);
                ygJieshao.setText(Html.fromHtml("介绍："+yg_xingq.getIntroduction(),1));
                MediaController mediaController = new MediaController(Film_play.this);
                ygVideo.setMediaController(mediaController);
                String url = Mytoken.URl+yg_xingq.getVideo();
                ygVideo.setVideoPath(url);
                ygVideo.start();
            }
        });
    }

    private void initView() {
        ygVideo = (VideoView) findViewById(R.id.yg_video);
        ygJieshao = (TextView) findViewById(R.id.yg_jieshao);
    }
}