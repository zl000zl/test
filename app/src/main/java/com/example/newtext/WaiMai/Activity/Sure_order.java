package com.example.newtext.WaiMai.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Cailist;
import com.example.newtext.WaiMai.Waimaibean.OrderCreate;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Sure_order extends BaseActivity {

    private TextView moneyOrder;
    private TextView moneyContent;
    private Spinner moneySpinner;


    private String[] title = {"电子支付", "微信", "支付宝"};
    private Button ljPay;
    private CardView moneyCard;


    public static String address_xiangxi;
    public static String aname;
    public static String shop_number;
    public static String aphone;
    public static int ashop_id;
    public static Set<Cailist> acai_list;
    public static String zong_money;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_order);
        setTitle("订单确定");
        initView();
        initData();
        initClick();
    }

    private void initClick() {


        ljPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderCreate orderCreate = new OrderCreate();
                orderCreate.setAddressDetail(address_xiangxi);
                orderCreate.setLabel("学校");
                orderCreate.setName(aname);
                orderCreate.setPhone(aphone);
                orderCreate.setAmount(Double.parseDouble(zong_money));
                orderCreate.setSellerId(ashop_id);
                List<OrderCreate.OrderItemListBean> itemListBeans = new ArrayList<>();
                for (Cailist cai : Order_sure.cais) {
                    itemListBeans.add(new OrderCreate.OrderItemListBean(cai.getId(), cai.getCount()));
                }
                orderCreate.setOrderItemList(itemListBeans);


                Network.doPost("/prod-api/api/takeout/order/create", new Gson().toJson(orderCreate), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code") == 200) {
                            Toast.makeText(Sure_order.this, "订单已创建！！！", Toast.LENGTH_SHORT).show();
                            moneyCard.setVisibility(View.GONE);
                            ljPay.setVisibility(View.GONE);
                            finish();
                        }else {
                            Toast.makeText(Sure_order.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initData() {
        moneyOrder.setText("" + zong_money);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_pay, title);
        moneySpinner.setAdapter(adapter);
        moneySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                moneyContent.setText("" + title[i]);
                moneyContent.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView() {
        moneyOrder = (TextView) findViewById(R.id.money_order);
        moneyContent = (TextView) findViewById(R.id.money_content);
        moneySpinner = (Spinner) findViewById(R.id.money_spinner);
        ljPay = (Button) findViewById(R.id.lj_pay);
        moneyCard = (CardView) findViewById(R.id.money_card);
    }
}