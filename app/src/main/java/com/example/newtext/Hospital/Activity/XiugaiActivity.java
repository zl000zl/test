package com.example.newtext.Hospital.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Hospital.Adapter.Classify;
import com.example.newtext.HospitalBean.Classi;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class XiugaiActivity extends AppCompatActivity {

    private Button xgOne;
    private Button xgTwo;
    private RecyclerView xiuRecycleView;

    public static List<Classi> xg_room;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai);
        initView();
        setTitle("预约挂号");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initData();
        One();
        initChick();
    }

    private void initChick() {
        xgTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Network.doGet("/prod-api/api/hospital/category/list?type=2", new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        List<Classi> classis = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Classi>>(){
                        }.getType());
                        xiuRecycleView.setLayoutManager(new LinearLayoutManager(XiugaiActivity.this));
                        xiuRecycleView.setAdapter(new Classify(XiugaiActivity.this,classis));
                        QuerenActivity.leixing = "2";
                        //赋值给QuerenActivity里的普通类型的值
                    }
                });
            }
        });
    }

    private void initData() {
        xgOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                One();
            }
        });


    }

    private void One() {
        Network.doGet("/prod-api/api/hospital/category/list?type=1", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Classi> classis = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Classi>>(){
                }.getType());
                xiuRecycleView.setLayoutManager(new LinearLayoutManager(XiugaiActivity.this));
                xiuRecycleView.setAdapter(new Classify(XiugaiActivity.this,classis));
                QuerenActivity.leixing = "1";
                //赋值给QuerenActivity里的专家类型的值
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        xgOne = (Button) findViewById(R.id.xg_one);
        xgTwo = (Button) findViewById(R.id.xg_two);
        xiuRecycleView = (RecyclerView) findViewById(R.id.xiu_recycleView);
    }
}