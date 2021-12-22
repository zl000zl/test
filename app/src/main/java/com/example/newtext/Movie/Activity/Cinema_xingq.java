package com.example.newtext.Movie.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.cinemaBean.Ciname_xq;
import com.example.newtext.Mytoken;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Cinema_xingq extends BaseActivity {

    private TextView yqName;
    private ImageView imageView16;
    private TextView yqCity;
    private TextView yqAddress;
    private TextView yqBrand;
    private TextView yqJuli;
    private TextView yqFen;

    public static int cinemaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_xingq);
        setTitle("影院详情");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/theatre/" + cinemaId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Ciname_xq ciname_xq = new Gson().fromJson(jsonObject.optString("data"), Ciname_xq.class);
                yqName.setText("" + ciname_xq.getName());
                Glide.with(imageView16).load(Mytoken.URl + ciname_xq.getCover()).into(imageView16);
                yqCity.setText("所在地" + ciname_xq.getProvince() + "-" + ciname_xq.getCity() + "-" + ciname_xq.getArea());
                yqAddress.setText("详细地址："+ciname_xq.getAddress());
                yqFen.setText("评分："+ciname_xq.getScore()+"分");
                yqBrand.setText("品牌："+ciname_xq.getBrand());
                yqJuli.setText("距离："+ciname_xq.getDistance());
            }
        });
    }

    private void initView() {
        yqName = (TextView) findViewById(R.id.yq_name);
        imageView16 = (ImageView) findViewById(R.id.imageView16);
        yqCity = (TextView) findViewById(R.id.yq_city);
        yqAddress = (TextView) findViewById(R.id.yq_address);
        yqBrand = (TextView) findViewById(R.id.yq_brand);
        yqJuli = (TextView) findViewById(R.id.yq_juli);
        yqFen = (TextView) findViewById(R.id.yq_fen);
    }
}