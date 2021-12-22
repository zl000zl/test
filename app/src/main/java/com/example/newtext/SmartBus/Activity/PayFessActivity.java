package com.example.newtext.SmartBus.Activity;

import android.os.Bundle;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.R;

public class PayFessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_fess);
        setTitle("未交费详情");
    }
}