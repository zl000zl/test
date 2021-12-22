package com.example.newtext.WaiMai.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

public class Shop_pingjia extends BaseActivity {

    private Button butPingjia;
    private RatingBar pjFenshu;
    private EditText pjNeirong;

    public static String order_id;

    public static RecyclerView pj_recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_pingjia);
        setTitle("顾客评价");
        initView();
        initRat();
        initData();
    }

    private void initRat() {
        pjFenshu.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            //改变点亮的星星个数触发的事件：
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                pjFenshu.getRating();
                // ratingbar 评分修改的RatingBar
                // rating 当前评分分数。取值范围为0到星型的数量。
                // fromUser 如果评分改变是由用户触摸手势或方向键轨迹球移动触发的，则返回true
            }
        });
    }

    private void initData() {
        butPingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pjNeirong.getText().toString().trim().length() == 0) {
                    Toast.makeText(Shop_pingjia.this, "请你评论！！！", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    String pj_content = pjNeirong.getText().toString().trim();
                    int pj_fen = (int) pjFenshu.getRating();

                    try {
                        jsonObject.put("content",pj_content).put("orderNo",order_id)
                                .put("score",pj_fen);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPost("/prod-api/api/takeout/comment", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(Shop_pingjia.this, "评论成功！！！", Toast.LENGTH_SHORT).show();
                                finish();
                                Network.doGet("/prod-api/api/takeout/order/list?status=待评价", new OkResult() {
                                    //这一步是刷新用的
                                    @Override
                                    public void succes(JSONObject jsonObject) {
                                        if (jsonObject.optInt("code")==200){
                                            List<Non_payment> non_payments = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Non_payment>>() {
                                            }.getType());

                                            PaymentsAdapter paymentsAdapter = new PaymentsAdapter();
                                            pj_recycle.setLayoutManager(new LinearLayoutManager(Shop_pingjia.this));
                                            pj_recycle.setAdapter(paymentsAdapter);

                                            paymentsAdapter.setNon_payments(non_payments);
                                            paymentsAdapter.notifyDataSetChanged();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(Shop_pingjia.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        butPingjia = (Button) findViewById(R.id.but_pingjia);
        pjFenshu = (RatingBar) findViewById(R.id.pj_fenshu);
        pjNeirong = (EditText) findViewById(R.id.pj_neirong);
    }
}