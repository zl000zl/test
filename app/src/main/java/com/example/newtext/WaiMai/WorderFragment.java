package com.example.newtext.WaiMai;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Activity.Refund;
import com.example.newtext.WaiMai.Activity.Shop_pingjia;
import com.example.newtext.WaiMai.Adapter.PaymentsAdapter;
import com.example.newtext.WaiMai.Waimaibean.Non_payment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WorderFragment extends Fragment {
    private TabLayout worderTablayout;
    private RecyclerView worderRecycle;
    public static String atype="退款";
    //首先 先给公共的变量一个初始值 这是方便根据你点击了哪个tab进行跳转的
    private static List<String> tablayout = new ArrayList<>();
    //这是给Tab里的内容个集合



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initTablayout();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/takeout/order/list?status=待支付", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {

                List<Non_payment> non_payments = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Non_payment>>() {
                }.getType());

                PaymentsAdapter paymentsAdapter = new PaymentsAdapter();
                worderRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                worderRecycle.setAdapter(paymentsAdapter);

                paymentsAdapter.setNon_payments(non_payments);
                paymentsAdapter.notifyDataSetChanged();


                paymentsAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("paymentType","电子支付").put("orderNo",non_payments.get(position).getOrderInfo().getOrderNo());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Network.doPost("/prod-api/api/takeout/pay", jsonObject.toString(), new OkResult() {
                            @Override
                            public void succes(JSONObject jsonObject) {
                                if (jsonObject.optInt("code")==200){
                                    Network.doGet("/prod-api/api/takeout/order/list?status=待支付", new OkResult() {
                                        @Override
                                        public void succes(JSONObject jsonObject) {
                                            if (jsonObject.optInt("code")==200){
                                                List<Non_payment> non_payments = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Non_payment>>() {
                                                }.getType());

                                                PaymentsAdapter paymentsAdapter = new PaymentsAdapter();
                                                worderRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                                                worderRecycle.setAdapter(paymentsAdapter);

                                                paymentsAdapter.setNon_payments(non_payments);
                                                paymentsAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                                    Toast.makeText(getActivity(), "支付成功！！！", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void initTablayout() {
        if (tablayout.size() != 4) {
            tablayout.add("待支付");
            tablayout.add("完成");
            tablayout.add("待评价");
            tablayout.add("退款");
        }
        for (int i = 0; i < tablayout.size(); i++) {
            worderTablayout.addTab(worderTablayout.newTab().setText(tablayout.get(i)).setTag(tablayout.get(i)));
        }

        worderTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //tab的点击事件
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String typename = tab.getTag().toString();
                //取到tab上的每个内容的值
                loadData(typename);
                //根据这个值来判断你点击了哪个来跳转
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadData(String typename) {
        //这里定义的switch是用来根据你点击哪个tab内容来进行跳转 是为让PaymentsAdapter里面的一个xml显示 显示不同内容
        switch (typename){
            case "待支付":
                atype="待支付";
                break;
            case "完成":
                atype="完成";
                break;
            case "待评价":
                atype="待评价";
                break;
            case "退款":
                atype="退款";
                break;
        }
        Network.doGet("/prod-api/api/takeout/order/list?status=" + typename, new OkResult() {
            //根据你点哪一个Tab栏来请求跳转到对应的效果
            @Override
            public void succes(JSONObject jsonObject) {
                List<Non_payment> non_payments = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Non_payment>>() {
                }.getType());

                PaymentsAdapter paymentsAdapter = new PaymentsAdapter();
                worderRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                worderRecycle.setAdapter(paymentsAdapter);

                paymentsAdapter.setNon_payments(non_payments);
                paymentsAdapter.notifyDataSetChanged();

                switch (typename){
                    //通过switch判断你点击了哪一个tab内容
                    case "待评价":
                        paymentsAdapter.setOnClickListener(new OnClickListener() {
                            //待评价的按钮点击事件
                            @Override
                            public void onClick(View view, int position) {
                                switch (PaymentsAdapter.click){
                                    //又在适配器里定义了一个公共的变量 来判断你点击了待评价页面的按钮
                                    case "评价":
                                        Shop_pingjia.pj_recycle=worderRecycle;
                                        Shop_pingjia.order_id = non_payments.get(position).getOrderInfo().getOrderNo();
                                        startActivity(new Intent(getActivity(), Shop_pingjia.class));
                                        break;

                                    case "退款" :
                                        //点击退款按钮的触发事件
                                        Refund.tk_recycle=worderRecycle;
                                        Refund.orderId = non_payments.get(position).getOrderInfo().getOrderNo();
                                        startActivity(new Intent(getActivity(), Refund.class));
                                        break;
                                }

                            }
                        });
                }
            }
        });

    }

    private void initView(View view) {
        worderTablayout = (TabLayout) getView().findViewById(R.id.worder_tablayout);
        worderRecycle = (RecyclerView) getView().findViewById(R.id.worder_recycle);
    }
}