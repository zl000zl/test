package com.example.newtext.WaiMai.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Adapter.PaymentsAdapter;
import com.example.newtext.WaiMai.Waimaibean.Non_payment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Refund extends BaseActivity {

    private EditText edTuikuan;
    private Button btnTuikuan;

    public static String orderId;
    public static RecyclerView tk_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        setTitle("退款");
        initView();
        initData();
    }

    private void initData() {
        btnTuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edTuikuan.getText().toString().trim().length() == 0) {
                    Toast.makeText(Refund.this, "请输入理由！！！", Toast.LENGTH_SHORT).show();
                }else {
                    String tk_content = edTuikuan.getText().toString().trim();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("reason",tk_content).put("orderNo",orderId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPost("/prod-api/api/takeout/order/refound", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Network.doGet("/prod-api/api/takeout/order/list?status=待评价", new OkResult() {
                                    //这一步是刷新用的
                                    @Override
                                    public void succes(JSONObject jsonObject) {
                                        if (jsonObject.optInt("code")==200){
                                            List<Non_payment> non_payments = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Non_payment>>() {
                                            }.getType());

                                            PaymentsAdapter paymentsAdapter = new PaymentsAdapter();
                                            tk_recycle.setLayoutManager(new LinearLayoutManager(Refund.this));
                                            tk_recycle.setAdapter(paymentsAdapter);

                                            paymentsAdapter.setNon_payments(non_payments);
                                            paymentsAdapter.notifyDataSetChanged();
                                        }
                                    }
                                });
                                Toast.makeText(Refund.this, "退款成功！！！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        edTuikuan = (EditText) findViewById(R.id.ed_tuikuan);
        btnTuikuan = (Button) findViewById(R.id.btn_tuikuan);
    }
}