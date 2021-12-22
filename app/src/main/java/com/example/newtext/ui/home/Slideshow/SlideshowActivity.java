package com.example.newtext.ui.home.Slideshow;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.Press;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class SlideshowActivity extends AppCompatActivity {

    private TextView tvTitle2;
    private ImageView ivImage2;
    private TextView tvNews2;
    private List<Press> presses;
    private RelativeLayout rl_rlt;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        Network.doGet("/prod-api/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Press> presses = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Press>>(){
                }.getType());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(ivImage2).load(Mytoken.URl +presses.get(0).getCover()).into(ivImage2);
                        tvTitle2.setText(presses.get(0).getTitle());
                        tvNews2.setText(Html.fromHtml(presses.get(0).getContent(),1));
                    }
                });
            }
        });
    }

    private void initView() {
        tvTitle2 = (TextView) findViewById(R.id.tv_title2);
        ivImage2 = (ImageView) findViewById(R.id.iv_image2);
        tvNews2 = (TextView) findViewById(R.id.tv_news2);
        rl_rlt = (RelativeLayout)findViewById(R.id.rl_rlt);
    }
}