package com.example.newtext.SmartBus.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.SmartBus.Adapter.BusAdapter;
import com.example.newtext.SmartBus.BusBean.Buslist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class BusActivity extends AppCompatActivity {

    private RecyclerView busRecycleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        setTitle("定制班车");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initBus();
    }

    private void initBus() {

    }

    private void initData() {
        //四条线路的列表请求
        Network.doGet("/prod-api/api/bus/line/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Buslist> buslists = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Buslist>>(){
                }.getType());
                busRecycleView.setLayoutManager(new LinearLayoutManager(BusActivity.this));
                busRecycleView.setAdapter(new BusAdapter(BusActivity.this,buslists));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        busRecycleView = (RecyclerView) findViewById(R.id.bus_recycleView);
    }
}