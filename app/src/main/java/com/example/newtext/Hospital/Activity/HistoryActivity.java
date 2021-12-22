package com.example.newtext.Hospital.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Hospital.Adapter.HistoryAdapter;
import com.example.newtext.HospitalBean.History;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView lsRecycleView;
    private Button liBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("预约历史");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initChick();
    }

    private void initChick() {
        liBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/hospital/reservation/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<History> histories = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<History>>(){
                }.getType());
                lsRecycleView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                lsRecycleView.setAdapter(new HistoryAdapter(HistoryActivity.this,histories));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        lsRecycleView = (RecyclerView) findViewById(R.id.ls_recycleView);
        liBack = (Button) findViewById(R.id.li_back);
    }
}