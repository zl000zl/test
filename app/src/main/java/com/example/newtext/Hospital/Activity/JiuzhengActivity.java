package com.example.newtext.Hospital.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Hospital.Adapter.JiuzhengAdapter;
import com.example.newtext.HospitalBean.Chaxun;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class JiuzhengActivity extends AppCompatActivity {

    private RecyclerView doctorRecycleView;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiuzhneg);
        setTitle("就诊人卡片");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/hospital/patient/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
              List<Chaxun> chaxuns = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Chaxun>>(){}.getType());
                doctorRecycleView.setLayoutManager(new LinearLayoutManager(JiuzhengActivity.this));
                doctorRecycleView.setAdapter(new JiuzhengAdapter(JiuzhengActivity.this,chaxuns));

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        doctorRecycleView = (RecyclerView) findViewById(R.id.doctor_recycleView);
        cardView = (CardView) findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiuzhengActivity.this,AddActivity.class));
            }
        });
    }
}