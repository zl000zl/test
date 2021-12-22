package com.example.newtext.SmartBus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.SmartBus.Adapter.BusAdapter;
import com.example.newtext.SmartBus.Adapter.DetailsAdapter;
import com.example.newtext.SmartBus.BusBean.Line;
import com.example.newtext.SmartBus.BusBean.Show;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private TextView detailsFirst;
    private TextView detailsEnd;
    private TextView detailsMoney;
    private TextView detailsKm;
    private RecyclerView detailsRecycle;
    private Button detailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("巴士详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initRecycleView();
        initChick();
    }

    private void initChick() {
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DetailsActivity.this,MessageActivity.class));
            }
        });
    }

    private void initRecycleView() {
        Network.doGet("/prod-api/api/bus/stop/list?linesId=" +BusAdapter.line1, new OkResult() {
            //根据线路的id 请求到每一条线路的站点信息
            @Override
            public void succes(JSONObject jsonObject) {
                List<Show> shows = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Show>>() {
                }.getType());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailsActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                detailsRecycle.setLayoutManager(linearLayoutManager);
                detailsRecycle.setAdapter(new DetailsAdapter(DetailsActivity.this,shows));

                MessageActivity.mshow = shows;  //这里定义的是MessageActivity中 公共的求所有站点的集合数
            }
        });

    }

    private void initData() {
        Network.doGet("/prod-api/api/bus/line/" + BusAdapter.line1, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Line line = new Gson().fromJson(jsonObject.optString("data"),Line.class);
                detailsFirst.setText(""+line.getFirst());
                detailsEnd.setText(""+line.getEnd());
                detailsMoney.setText("￥"+line.getPrice()+"元");
                detailsKm.setText("全程"+line.getMileage()+"KM");


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        detailsFirst = (TextView) findViewById(R.id.details_first);
        detailsEnd = (TextView) findViewById(R.id.details_end);
        detailsMoney = (TextView) findViewById(R.id.details_money);
        detailsKm = (TextView) findViewById(R.id.details_km);
        detailsRecycle = (RecyclerView) findViewById(R.id.details_recycle);
        detailsButton = (Button) findViewById(R.id.details_button);
    }
}