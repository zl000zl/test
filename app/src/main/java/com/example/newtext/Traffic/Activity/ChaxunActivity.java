package com.example.newtext.Traffic.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.Traffic.Adapter.RulesAdapter;
import com.example.newtext.Traffic.Jiaotongbean.Rules;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class ChaxunActivity extends BaseActivity {

    private RecyclerView carRecycle;

    List<Rules> rules;
    private Button carMuch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaxun);
        setTitle("违章信息列表");
        initView();
        initMuch();
    }

    private void initMuch() {
        carMuch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carRecycle.setAdapter(new RulesAdapter(ChaxunActivity.this, rules));
            }
        });
    }

    private void initView() {
        carRecycle = (RecyclerView) findViewById(R.id.car_recycle);
        Network.doGet("/prod-api/api/traffic/illegal/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                rules = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Rules>>() {
                }.getType());
                carRecycle.setLayoutManager(new LinearLayoutManager(ChaxunActivity.this));
                carRecycle.setAdapter(new RulesAdapter(ChaxunActivity.this, rules.subList(0, 6)));
            }
        });
        carMuch = (Button) findViewById(R.id.car_much);
    }
}