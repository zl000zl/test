package com.example.newtext.Allservice.Subway.Subway_Path;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.Station;
import com.google.gson.Gson;

import org.json.JSONObject;

public class BjStation extends AppCompatActivity {

    private TextView stationFirst;
    private TextView stationEnd;
    private TextView stationTime;
    private TextView stationKm;
    private ImageView ivSubway;
    private RecyclerView stationList;

    public static int id;
    //公共的方法  求哪个站点的id 方便前面的SubwayAdapter适配器里带入站点的id来求每个站点的路线名


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bj_station);
        setTitle("线路详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }



    private void initData() {
        stationList = (RecyclerView) findViewById(R.id.station_list);

        Network.doGet("/prod-api/api/metro/line/"+id, new OkResult() {
            //请求时带上线路的lineId可以完成4条点击事件的不同路线结果
            @Override
            public void succes(JSONObject jsonObject) {
                Station station = new Gson().fromJson(jsonObject.optString("data"),Station.class);
                stationFirst.setText(""+station.getFirst());
                stationEnd.setText(""+station.getEnd());
                stationTime.setText(""+station.getRemainingTime()+"分钟");
                stationKm.setText(""+station.getStationsNumber()+"站"+station.getKm()+"KM");


                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BjStation.this);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                //水平滑动
                stationList.setLayoutManager(linearLayoutManager);
                stationList.setAdapter(new StationAdapter(BjStation.this,station));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        stationFirst = (TextView) findViewById(R.id.station_first);
        stationEnd = (TextView) findViewById(R.id.station_end);
        stationTime = (TextView) findViewById(R.id.station_time);
        stationKm = (TextView) findViewById(R.id.station_km);
        ivSubway = (ImageView) findViewById(R.id.iv_subway);

    }
}