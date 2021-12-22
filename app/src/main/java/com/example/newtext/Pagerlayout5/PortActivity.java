package com.example.newtext.Pagerlayout5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.Mytoken;
import com.example.newtext.R;

public class PortActivity extends AppCompatActivity {

    private EditText etPort;
    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
        initData();
    }

    private void initData() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPort.getText().toString().isEmpty()){
                    Toast.makeText(PortActivity.this, "请输入IP地址！！！", Toast.LENGTH_SHORT).show();
                }else {
                    Mytoken.URl = "http://"+etPort.getText().toString().trim();
                    Toast.makeText(PortActivity.this, "保存成功！！！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initView() {
        etPort = (EditText) findViewById(R.id.et_port);
        btnEnter = (Button) findViewById(R.id.btn_enter);
    }
}