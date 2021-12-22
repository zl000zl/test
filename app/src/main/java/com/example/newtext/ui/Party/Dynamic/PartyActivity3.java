package com.example.newtext.ui.Party.Dynamic;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PartyActivity3 extends AppCompatActivity {
    private Banner party_banner;
    Party3ImageLoader party3ImageLoader = new Party3ImageLoader();
    private Button button4;
    List<Integer> bannerlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party3);
        setTitle("活动报名");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        party_banner = (Banner) findViewById(R.id.party_banner);
        initView();
        initData();
    }

    private void initData() {
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PartyActivity3.this, "留言成功，请稍后查看！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        bannerlist.add(R.drawable.huodong1);
        bannerlist.add(R.drawable.huodong2);
        bannerlist.add(R.drawable.huodong3);
        bannerlist.add(R.drawable.huodong4);
        bannerlist.add(R.drawable.huodong5);
        bannerlist.add(R.drawable.huodong6);
        bannerlist.add(R.drawable.huodong7);

        party_banner.setImageLoader(party3ImageLoader);
        party_banner.setImages(bannerlist);
        party_banner.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private class Party3ImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(imageView).load(path).into(imageView);
        }
    }
}