package com.example.newtext.Hospital.Activity;

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
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newtext.HospitalBean.Details;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;

import org.json.JSONObject;

public class XqActivity extends AppCompatActivity {
    public static int Hospital_id;

    private TextView hospTitle;
    private ImageView hospImage;
    private TextView hospContent;
    private RatingBar ratingBar;
    private Button hospCome;

    public static Details details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        setTitle("医院详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/hospital/hospital/" + Hospital_id, new OkResult() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void succes(JSONObject jsonObject) {
                Details details = new Gson().fromJson(jsonObject.optString("data"),Details.class);
                Glide.with(hospImage).load(Mytoken.URl+details.getImgUrl()).into(hospImage);
                hospTitle.setText(""+details.getHospitalName());
                hospContent.setText(Html.fromHtml(""+details.getBrief(),1));
                ratingBar.setRating(details.getLevel());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        hospTitle = (TextView) findViewById(R.id.hosp_title);
        hospImage = (ImageView) findViewById(R.id.hosp_image);
        hospContent = (TextView) findViewById(R.id.hosp_content);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        hospCome = (Button) findViewById(R.id.hosp_come);

        hospCome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(XqActivity.this,YuyueActivity.class));
            }
        });
    }
}