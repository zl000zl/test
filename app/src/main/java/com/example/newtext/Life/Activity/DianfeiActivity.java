package com.example.newtext.Life.Activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.R;

public class DianfeiActivity extends BaseActivity {


    private TextView payDanwei;
    private TextView payHuhao;
    private TextView payUsername;
    private TextView payAddress;
    private TextView payHave;
    private TextView payLack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianfei);
        setTitle("电费详情");
        initView();
        initData();
    }

    private void initData() {
        Toast.makeText(this, "暂未欠费账单！！！", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        payDanwei = (TextView) findViewById(R.id.pay_danwei);
        payHuhao = (TextView) findViewById(R.id.pay_huhao);
        payUsername = (TextView) findViewById(R.id.pay_username);
        payAddress = (TextView) findViewById(R.id.pay_address);
        payHave = (TextView) findViewById(R.id.pay_have);
        payLack = (TextView) findViewById(R.id.pay_lack);
    }
}