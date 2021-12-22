package com.example.newtext.Hospital.Activity;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Hospital.Adapter.YuyueAdapter;
import com.example.newtext.HospitalBean.One;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class YuyueActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView ghRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guah);
        setTitle("门诊预约");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/hospital/hospital/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<One> ones = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<One>>(){
                }.getType());
                ghRecycleView.setLayoutManager(new LinearLayoutManager(YuyueActivity.this));
                ghRecycleView.setAdapter(new YuyueAdapter(YuyueActivity.this,ones));
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        searchView = (SearchView) findViewById(R.id.searchView);
        ghRecycleView = (RecyclerView) findViewById(R.id.gh_recycleView);
    }
}