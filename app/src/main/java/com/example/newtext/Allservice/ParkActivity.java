package com.example.newtext.Allservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.Allservice;
import com.example.newtext.item_Adapter.ServiceAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class ParkActivity extends AppCompatActivity {

    private RecyclerView rvPack;
    //绑定RecycleView的id

    private Button btn_inquire, btn_much;
    //绑定下面两个Button的id

    List<Allservice> allservices;

    //定义一个全局变量 停哪儿的Bean集合为方便点击更多的时候 显示更多内容
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("停车场查询");
        //屏幕上放的标题
        btn_inquire = (Button) findViewById(R.id.btn_inquire);
        btn_much = (Button) findViewById(R.id.btn_much);
        //调用控件的布局界面
        initData();  //get请求停车场查询的数据传入RecycleView
        initView();  //下面黄色按钮的点击事件
        //
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initData() {
        Network.doGet("/prod-api/api/park/lot/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                allservices /*这里就是List<Allservice>*/ = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Allservice>>() {
                }.getType());  //上面只是定义了一个全局变量
                rvPack = (RecyclerView) findViewById(R.id.rv_pack);
                rvPack.setLayoutManager(new LinearLayoutManager(ParkActivity.this));
                rvPack.setAdapter(new ServiceAdapter(ParkActivity.this, allservices.subList(0, 5)/*隐藏下面的textView*/));

            }
        });
    }

    private void initView() {
        btn_inquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParkActivity.this, PkrecordActivity.class));
                //跳转到 查看停车记录的界面
            }
        });
        btn_much.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvPack.setAdapter(new ServiceAdapter(ParkActivity.this, allservices));
                //点击查看更多的时候显示更多
            }
        });
    }
}