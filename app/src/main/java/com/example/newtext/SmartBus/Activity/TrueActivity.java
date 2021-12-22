package com.example.newtext.SmartBus.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.SmartBus.Adapter.OrderAdapter;
import com.example.newtext.SmartBus.BusBean.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class TrueActivity extends AppCompatActivity {

    private RecyclerView orderRecycle;
    private TextView trueView1;
    private TextView trueView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true);
        setTitle("我的订单");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        NotPaid();
    }

    private void initData() {
        trueView2.setOnClickListener(new View.OnClickListener() {
            //点击已支付时的背景色和字体颜色的改变情况
            @Override
            public void onClick(View view) {
                trueView1.setBackgroundColor(Color.rgb(255,255,255));
                trueView1.setTextColor(Color.rgb(30,150,243));
                //未支付背景变为透明 字体变为蓝色

                trueView2.setBackgroundColor(Color.rgb(30,150,243));
                trueView2.setTextColor(Color.rgb(255,255,255));
                //已支付背景变为蓝色 字体变为透明
                Have_paid();
            }
        });
        trueView1.setOnClickListener(new View.OnClickListener() {
            //点击未支付时的背景色和字体颜色的改变情况
            @Override
            public void onClick(View view) {
                trueView2.setBackgroundColor(Color.rgb(255,255,255));
                trueView2.setTextColor(Color.rgb(30,150,243));
                //已支付背景变为透明 字体变为蓝色

                trueView1.setBackgroundColor(Color.rgb(30,150,243));
                trueView1.setTextColor(Color.rgb(255,255,255));
                //未支付背景变为蓝色 字体变为透明
                NotPaid();
            }
        });
    }

    private void Have_paid() {
        Network.doGet("/prod-api/api/bus/order/list?status=1", new OkResult() {
            //get请求到订单已支付列表
            @Override
            public void succes(JSONObject jsonObject) {
                List<Order> orders = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Order>>(){
                }.getType());
                orderRecycle.setLayoutManager(new LinearLayoutManager(TrueActivity.this));
                orderRecycle.setAdapter(new OrderAdapter(TrueActivity.this,orders,orderRecycle));

            }
        });
    }

    private void NotPaid() {
        Network.doGet("/prod-api/api/bus/order/list?status=0", new OkResult() {
            //get请求到订单未支付列表
            @Override
            public void succes(JSONObject jsonObject) {
                List<Order> orders = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Order>>(){
                }.getType());
                orderRecycle.setLayoutManager(new LinearLayoutManager(TrueActivity.this));
                orderRecycle.setAdapter(new OrderAdapter(TrueActivity.this,orders,orderRecycle));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {

        orderRecycle = (RecyclerView) findViewById(R.id.order_recycle);
        trueView1 = (TextView) findViewById(R.id.true_View1);
        trueView2 = (TextView) findViewById(R.id.true_view2);
    }
}