package com.example.newtext.Allservice.Subway.Subway_Path;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

public class Map_station extends AppCompatActivity {

    private ImageView iv_mimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_station);
        setTitle("线路总览图");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
    }

    private void initView() {
        iv_mimage = (ImageView)findViewById(R.id.iv_mimage);
        Glide.with(iv_mimage).load(Mytoken.URl+"/prod-api/profile/upload/image/2021/05/08/554f2392-1e1c-4449-b95c-327a5f7ec91d.jpeg")
                .into(iv_mimage);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}