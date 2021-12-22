package com.example.newtext.Allservice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.R;

public class PacktwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packtwo);
        setTitle("查询停车记录");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}