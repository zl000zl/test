package com.example.newtext.Allservice;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.R;
import com.example.newtext.StringBean.Allservice;

public class PackThree extends AppCompatActivity {

    private TextView tvAddress;
    private TextView tvAddress2;
    private TextView tvKm;
    private TextView tvPirce;
    private TextView tvPack1;
    //绑定私有控件的id

    public static Allservice allservice;

    //定义 停哪儿Bean为公共类的方法  方便下面跳转的时候把值传到另一个Activity中
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_three);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("停车场详情");
        initView();
        //调用所有控件的id
        initData();
        //请求到的数据
    }

    private void initData() {
        /*请求到的数据给上面的TextView赋值显示出来*/
        tvAddress.setText(allservice.getParkName());
        tvKm.setText(allservice.getDistance() + "公里");
        tvAddress2.setText(allservice.getAddress());
        tvPirce.setText(allservice.getRates() + "￥/小时");
        tvPack1.setText(allservice.getVacancy() + "/" + allservice.getAllPark());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvAddress2 = (TextView) findViewById(R.id.tv_address2);
        tvKm = (TextView) findViewById(R.id.tv_km);
        tvPirce = (TextView) findViewById(R.id.tv_pirce);
        tvPack1 = (TextView) findViewById(R.id.tv_pack1);
        //调用控件的假面布局
    }
}