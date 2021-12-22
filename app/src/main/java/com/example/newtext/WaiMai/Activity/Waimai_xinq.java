package com.example.newtext.WaiMai.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.CaipNum;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Adapter.CaiAdapter;
import com.example.newtext.WaiMai.Adapter.CailistAdapter;
import com.example.newtext.WaiMai.Adapter.PjAdapter;
import com.example.newtext.WaiMai.Waimaibean.Cailist;
import com.example.newtext.WaiMai.Waimaibean.Caitype;
import com.example.newtext.WaiMai.Waimaibean.Pingjia;
import com.example.newtext.WaiMai.Waimaibean.Shop_xing;
import com.example.newtext.Mytoken;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Waimai_xinq extends BaseActivity {

    private ImageView xingqImage;
    private TextView xingqName;
    private RatingBar xingqRab;
    private TextView xingqXiaoliang;
    private TextView xingqPrice;
    private ImageView shouchang;
    private TabLayout xingqTablayout;
    private LinearLayout xingqList;
    private RecyclerView recycle1;
    private RecyclerView recycle2;
    private LinearLayout xingqShop;
    private TextView xingqNumber;
    private TextView caiMoney;
    private Button xingqGo;
    private LinearLayout pingjia;
    private RatingBar pjRab;
    private RecyclerView pjRecycle;
    private TextView noPingjia;
    //绑定所有控件

    public static int shopId;
    //这取得是店家详情里的id

    private static List<String> tablayouts = new ArrayList<>();
    //tab栏上的文字定义集合
    private List<Caitype> caitypes;
    //所有菜品列表
    private Map<Integer, List<Cailist>> allCais = new HashMap<>();
    //这里是利于下面的菜品列表的存放在集合 方便取出  HashMap就是一个键值对
    private CailistAdapter cailistAdapter;
    //定义菜品列表的适配器 方便全局使用

    int currentIndex = 0;
    //定义变化颜色的第一个下标为0

    private int cai_number = 0;
    //定义改变下面购物车左边的数量
    private Double cai_money = 0.0;
    //定义改变下面购物车右边的钱数

    int a = 1;
    //定义打开页面时 赋给a的值

    private Set<Cailist> cailists = new HashSet<>();
    //把菜品的List转换成set(set是用于排除重复元素的) HashSet是set接口的实现 具有取存性

    private String totalStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waimai_xinq);
        setTitle("店家详细");
        initView();
        initTablayout();
        initData();
        initAdapter_list();
        initCollect();
        initClose();
    }

    private void initClose() {
        xingqGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cai_number==0){
                    //判断如果没选择菜品就会提心下面Toast
                    Toast.makeText(Waimai_xinq.this, "请你选餐！！！", Toast.LENGTH_SHORT).show();
                }else {
                    //如果选择了菜品 就执行下面的语句
                    Order_sure.number = xingqNumber.getText().toString().trim();
                    Order_sure.moneysum = totalStr;
                    Order_sure.cais = cailists;
                    //这个页面有cailists的数据  从这个页面把cailists值传到Order_sure中

                    Sure_order.acai_list = cailists;

                    Sure_order.ashop_id = shopId;
                    startActivity(new Intent(Waimai_xinq.this,Order_sure.class));
                }
            }
        });
    }

    private void initCollect() {
        shouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a == 1) {
                    //这里判断 首次进入界面时点击收藏 提示收藏成功
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("sellerId",shopId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPost("/prod-api/api/takeout/collect", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code") == 200) {
                                shouchang.setImageResource(R.drawable.startcoler);
                                Toast.makeText(Waimai_xinq.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                a = 2;
                //然后给a赋值为2
                if (a == 2){
                    Network.doGet("/prod-api/api/takeout/collect/check?sellerId=" + shopId, new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(Waimai_xinq.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initAdapter_list() {
        //初始化菜品分类
        CaiAdapter caiAdapter = new CaiAdapter();
        recycle1.setLayoutManager(new LinearLayoutManager(Waimai_xinq.this));
        recycle1.setAdapter(caiAdapter);

        //初始化菜品列表
        cailistAdapter = new CailistAdapter();
        //这里在上面已近定义
        recycle2.setLayoutManager(new LinearLayoutManager(Waimai_xinq.this));
        recycle2.setAdapter(cailistAdapter);

        cailistAdapter.setCaipNum(new CaipNum() {
            //点击菜品列表上加减来改变下面购物车的数量和钱数
            @Override
            public void add(Cailist cailist, double parseDouble, int position) {
                //这里的add就是CaipNum定义的接口
                cai_number++;
                //点击菜品的加号 数量变化 相加
                xingqNumber.setText(cai_number + "份");
                DecimalFormat df = new DecimalFormat("#####0.00");
                //DecimalFormat 是 NumberFormat 的一个具体子类，用于格式化十进制数字
                cai_money += cailist.getPrice();
                //上面定义的钱数就等于那个菜品的单价相加
                totalStr = df.format(cai_money);
                //定义一个String类型的str 就用DecimalFormat显示出来
                caiMoney.setText("￥" + totalStr);
                //xml上购物车右边的钱数发生变化

                cailists.add(cailist);



            }

            @Override
            public void remove(Cailist cailist, double parseDouble, int position) {
                cai_number--;
                xingqNumber.setText(cai_number + "份");
                DecimalFormat df = new DecimalFormat("#####0.00");
                cai_money -= cailist.getPrice();
                String str = df.format(cai_money);
                caiMoney.setText("￥" + str);
                cailists.add(cailist);
            }
        });
        //菜品分类的点击事件
        caiAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                recycle1.getChildAt(currentIndex).setBackgroundColor(Color.TRANSPARENT);
                //当左边条目点击时 设置上次点击的条目为灰色；
                view.setBackgroundColor(Color.WHITE);
                //设置当前的点击条目为白色
                currentIndex = position;
                //设置当点击条目时赋值给当前索引


                Caitype data = caitypes.get(position);
                //定义一个data为菜品的类型baen 当你点击了哪一个条目时取到菜品类型里的id值
                List<Cailist> list = allCais.get(data.getId());
                //定义一个菜品列表我list 就等于上面的Hash列表 取到菜品列表里的id  如果map集合里没有对应列表则返回null
                //（也就是当你点击左边条目时  就取出菜品类别里的id 来刷新列表里的内容）
                if (list != null) {
                    recycle2.setAdapter(new CailistAdapter(list));
                    //这一步骤就是刷新Adapter的
                } else initCaiList(data);  //如果取得值为null 就触发下面那个操作
            }
        });

        //查询店家菜品列表的请求  带所属店家详情的id值
        Network.doGet("/prod-api/api/takeout/category/list?sellerId=" + shopId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                caitypes = new Gson().fromJson(jsonObject.optString("data"), new TypeToken<List<Caitype>>() {
                }.getType());

                caiAdapter.setCaitypes(caitypes);
                //菜品类别的适配器赋值
                caiAdapter.notifyDataSetChanged();
                //是用于数据刷新用的方法
                initCaiList(caitypes.get(0));
                //这个方法里含的方法是菜品类别第一个值
            }
        });
    }

    private void initCaiList(Caitype data) {
        //菜品列表的点击事件
        Network.doGet("/prod-api/api/takeout/product/list?sellerId=" + data.getSellerId() + "&categoryId=" + data.getId(), new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Cailist> cailists = new Gson().fromJson(jsonObject.optString("data"), new TypeToken<List<Cailist>>() {
                }.getType());

                allCais.put(data.getId(), cailists);
                //存放当前的菜品列表 以hashmap的键值对的形式
                cailistAdapter.setCailists(cailists);
                //菜品类别的适配器赋值
                cailistAdapter.notifyDataSetChanged();
                //然后刷新
            }
        });
    }


    private void initData() {
        //店家详情的请求
        Network.doGet("/prod-api/api/takeout/seller/" + shopId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Shop_xing shop_xing = new Gson().fromJson(jsonObject.optString("data"), Shop_xing.class);
                Glide.with(xingqImage).load(Mytoken.URl + shop_xing.getImgUrl()).into(xingqImage);
                xingqName.setText("" + shop_xing.getName());
                xingqRab.setRating((float) +shop_xing.getScore());
                xingqXiaoliang.setText("销量：" + shop_xing.getSaleQuantity() + " 份");
                xingqPrice.setText("每人/" + shop_xing.getAvgCost() + " 元");

                pjRab.setRating((float) +shop_xing.getScore());
                //下面的评价里的商品评价的分数

                Order_sure.theme_name = shop_xing.getName();
                //这里是给activity中的公共的变量赋值为详情界面的店家名
            }
        });
    }

    private void initTablayout() {
        if (a == 1) {
            pingjia.setVisibility(View.GONE);
        }

        if (tablayouts.size() != 3) {
            tablayouts.add("点餐");
            tablayouts.add("评价");
            tablayouts.add("商家评价");
        }
        for (int i = 0; i < tablayouts.size(); i++) {
            xingqTablayout.addTab(xingqTablayout.newTab().setText(tablayouts.get(i)).setTag(i));
        }
        xingqTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getTag().toString().equals("0")) {
                    xingqList.setVisibility(View.VISIBLE);
                    xingqShop.setVisibility(View.VISIBLE);
                    pingjia.setVisibility(View.GONE);
                }
                if (tab.getTag().toString().equals("1")) {
                    initPingjia();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initPingjia() {
        xingqList.setVisibility(View.GONE);
        xingqShop.setVisibility(View.GONE);
        pingjia.setVisibility(View.VISIBLE);
        Network.doGet("/prod-api/api/takeout/comment/list?sellerId=" + shopId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Pingjia> pingjias = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Pingjia>>() {
                }.getType());
                if (pingjias.size()==0){
                    noPingjia.setVisibility(View.VISIBLE);
                }else {
                    pjRecycle.setLayoutManager(new LinearLayoutManager(Waimai_xinq.this));
                    pjRecycle.setAdapter(new PjAdapter(Waimai_xinq.this, pingjias));
                }
            }
        });
    }

    private void initView() {
        xingqImage = (ImageView) findViewById(R.id.xingq_image);
        xingqName = (TextView) findViewById(R.id.xingq_name);
        xingqRab = (RatingBar) findViewById(R.id.xingq_rab);
        xingqXiaoliang = (TextView) findViewById(R.id.xingq_xiaoliang);
        xingqPrice = (TextView) findViewById(R.id.xingq_price);
        shouchang = (ImageView) findViewById(R.id.shouchang);
        xingqTablayout = (TabLayout) findViewById(R.id.xingq_tablayout);
        xingqList = (LinearLayout) findViewById(R.id.xingq_list);
        xingqShop = (LinearLayout) findViewById(R.id.xingq_shop);
        xingqNumber = (TextView) findViewById(R.id.xingq_number);
        xingqGo = (Button) findViewById(R.id.xingq_go);
        recycle1 = (RecyclerView) findViewById(R.id.recycle1);
        recycle2 = (RecyclerView) findViewById(R.id.recycle2);
        caiMoney = (TextView) findViewById(R.id.cai_money);
        pingjia = (LinearLayout) findViewById(R.id.pingjia);
        pjRab = (RatingBar) findViewById(R.id.pj_rab);
        pjRecycle = (RecyclerView) findViewById(R.id.pj_recycle);
        noPingjia = (TextView) findViewById(R.id.no_pingjia);
    }
}