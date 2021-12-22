package com.example.newtext.ui.home.Slideshow;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
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

public class TwoActivity extends AppCompatActivity {

    private TextView tvTitle3;
    private ImageView ivImage3;
    private TextView tvNews3;
    private List<Press> presses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initData() {
        Network.doGet("/prod-api/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Press> presses = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Press>>(){
                }.getType());
                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        Glide.with(ivImage3).load(Mytoken.URl +presses.get(1).getCover()).into(ivImage3);
                        tvTitle3.setText(presses.get(1).getTitle());
                        tvNews3.setText(Html.fromHtml(presses.get(1).getContent(),1));
                    }
                });
            }
        });
    }

    private void initView() {
        tvTitle3 = (TextView) findViewById(R.id.tv_title3);
        ivImage3 = (ImageView) findViewById(R.id.iv_image3);
        tvNews3 = (TextView) findViewById(R.id.tv_news3);
    }
}