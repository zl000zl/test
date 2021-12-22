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

public class ThreeActivity extends AppCompatActivity {

    private TextView tvTitle4;
    private ImageView ivImage4;
    private TextView tvNews4;
    private List<Press> presses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
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
                        Glide.with(ivImage4).load(Mytoken.URl +presses.get(2).getCover()).into(ivImage4);
                        tvTitle4.setText(presses.get(2).getTitle());
                        tvNews4.setText(Html.fromHtml(presses.get(2).getContent(),1));
                    }
                });
            }
        });

    }

    private void initView() {
        tvTitle4 = (TextView) findViewById(R.id.tv_title4);
        ivImage4 = (ImageView) findViewById(R.id.iv_image4);
        tvNews4 = (TextView) findViewById(R.id.tv_news4);
    }
}