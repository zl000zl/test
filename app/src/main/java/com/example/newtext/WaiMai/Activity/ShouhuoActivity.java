package com.example.newtext.WaiMai.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ShouhuoActivity extends BaseActivity {

    private LinearLayout shouhuoAddress;
    private TextView threeDong;
    private EditText etAddress;
    private EditText etName;
    private EditText etTitle;
    private EditText etIphone;
    private Button btnSave;
    private LinearLayout letGo;

    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouhuo);
        setTitle("收货地址");
        initView();
        initData();
        initSave();
    }

    private void initSave() {
        threeDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Network.doAddress(ShouhuoActivity.this, threeDong);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sh_address = etAddress.getText().toString().trim();
                String sh_title = etTitle.getText().toString().trim();
                String sh_name = etName.getText().toString().trim();
                String sh_number = etIphone.getText().toString().trim();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("addressDetail", sh_address).put("label",sh_title)
                            .put("name",sh_name).put("phone",sh_number);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/takeout/address", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(ShouhuoActivity.this, "添加地址成功！！！", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(ShouhuoActivity.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initData() {
        shouhuoAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letGo.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView() {
        shouhuoAddress = (LinearLayout) findViewById(R.id.shouhuo_address);
        threeDong = (TextView) findViewById(R.id.three_dong);
        etAddress = (EditText) findViewById(R.id.et_address);
        etName = (EditText) findViewById(R.id.et_name);
        etIphone = (EditText) findViewById(R.id.et_iphone);
        btnSave = (Button) findViewById(R.id.btn_save);
        letGo = (LinearLayout) findViewById(R.id.let_go);
        etTitle = (EditText) findViewById(R.id.et_title);
    }
}