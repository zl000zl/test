package com.example.newtext.Movie.My;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.DdAdapter;
import com.example.newtext.Movie.cinemaBean.Dd_type;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Dd_list extends BaseActivity {


    private RecyclerView DdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_list);
        setTitle("订单列表");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/ticket/order/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Dd_type> dd_type = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Dd_type>>(){
                }.getType());

                DdAdapter ddAdapter = new DdAdapter();
                DdList.setLayoutManager(new LinearLayoutManager(Dd_list.this));
                DdList.setAdapter(ddAdapter);

                ddAdapter.setDd_types(dd_type);
                ddAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        DdList = (RecyclerView) findViewById(R.id.Dd_list);
    }
}