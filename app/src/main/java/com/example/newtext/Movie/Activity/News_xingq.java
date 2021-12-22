package com.example.newtext.Movie.Activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.cinemaBean.News_xq;
import com.example.newtext.Mytoken;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class News_xingq extends BaseActivity {

    private TextView newsTitle;
    private ImageView newsImage;
    private TextView newsContent;
    private TextView newsSum;
    private Button newsZan;

    News_xq news_xq;

    public static int newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_xingq);
        setTitle("新闻详情");
        initView();
        initData();
        initDots();
    }

    private void initDots() {
        newsZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();

                Network.doPut("/prod-api/api/movie/press/press/like/"+newsId,jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(News_xingq.this, "点赞成功！！！", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(News_xingq.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/press/press/" + newsId, new OkResult() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void succes(JSONObject jsonObject) {
                news_xq = new Gson().fromJson(jsonObject.optString("data"),News_xq.class);
                newsTitle.setText(""+news_xq.getTitle());
                Glide.with(newsImage).load(Mytoken.URl+news_xq.getCover()).into(newsImage);
                newsContent.setText(Html.fromHtml("内容："+news_xq.getContent(),1));
                newsSum.setText("点赞数："+news_xq.getLikeNum());
            }
        });
    }

    private void initView() {
        newsTitle = (TextView) findViewById(R.id.news_title);
        newsImage = (ImageView) findViewById(R.id.news_image);
        newsContent = (TextView) findViewById(R.id.news_content);
        newsSum = (TextView) findViewById(R.id.news_sum);
        newsZan = (Button) findViewById(R.id.news_zan);
    }
}