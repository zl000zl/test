package com.example.newtext.WaiMai.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Adapter.ClickAdapter;
import com.example.newtext.WaiMai.Adapter.SureAdapter;
import com.example.newtext.WaiMai.Waimaibean.Address;
import com.example.newtext.WaiMai.Waimaibean.Cailist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;
import java.util.Set;

public class Order_sure extends BaseActivity {

    public static Set<Cailist> cais;
    //这里定义了 公共的set变量 来从Waimai_xingq赋值  并排除重复的值

    List<Address> addresses;
    //地址信息的全局变量

    private LinearLayout takeAddress;
    private ImageView surePosition;
    private RecyclerView sureRecycle;
    private TextView dianjiaAddress;
    private TextView dianName;
    private TextView dianjiaPhone;
    private TextView dianjiaName;
    private ImageView dianjiaImage;
    private TextView dainjiaTitle;
    private TextView dianjiaNumber;
    private TextView dianjiaPrice;
    private Button tijiao;
    private LinearLayout visition;
    private RecyclerView click;


    int a = 1;
    //定义刚进去界面给a的值为1

    public static String number;
    public static String moneysum ;
    //定义公共变量从详情界面点击结算的份数 和钱数

    public static String theme_name;
    //定义公共变量你点击哪个店家的名字

    public static String shuoliang;
    //这是CailistAdapter 里赋值的   显示  你选中的各个菜品的数量




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sure);
        setTitle("收货地址");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initDots();
    }

    private void initDots() {


        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address_z = dianjiaAddress.getText().toString().trim();
                String name_z = dianName.getText().toString().trim();
                String phone_z = dianjiaPhone.getText().toString().trim();
                String shop_number = dianjiaNumber.getText().toString().trim();

                Sure_order.address_xiangxi=address_z;
                Sure_order.aname=name_z;
                Sure_order.aphone=phone_z;
                Sure_order.shop_number=shop_number;
                Sure_order.zong_money = moneysum;

                startActivity(new Intent(Order_sure.this,Sure_order.class));
            }
        });
    }

    private void initData() {
        surePosition.setOnClickListener(new View.OnClickListener() {
            //收货地址小按钮的点击事件
            @Override
            public void onClick(View view) {
                if (a == 1) {
                    //如果a=1就为第一个进来
                    sureRecycle.setVisibility(View.VISIBLE);
                    //收货地址展现出来
                    Network.doGet("/prod-api/api/takeout/address/list", new OkResult() {
                        //收货地址的请求
                        @Override
                        public void succes(JSONObject jsonObject) {
                            addresses = new Gson().fromJson(jsonObject.optString("data"), new TypeToken<List<Address>>() {
                            }.getType());

                            SureAdapter sureAdapter = new SureAdapter();
                            sureRecycle.setLayoutManager(new LinearLayoutManager(Order_sure.this));
                            sureRecycle.setAdapter(sureAdapter);
                            //初始化适配器
                            sureAdapter.setAddresses(addresses);
                            sureAdapter.notifyDataSetChanged();
                            //绑定数据

                            sureAdapter.setOnClickListener(new OnClickListener() {
                                //适配器界面的点击事件
                                @Override
                                public void onClick(View view, int position) {
                                    takeAddress.setVisibility(View.GONE);
                                    //收货地址为不显示
                                    sureRecycle.setVisibility(View.GONE);
                                    //收货地址的具体信息也不显示
                                    visition.setVisibility(View.VISIBLE);
                                    //下面的菜品信息显示   在xml界面为一开始不显示

                                    dianjiaAddress.setText("收货地址：" + addresses.get(position).getAddressDetail());
                                    dianName.setText("联系人：" + addresses.get(position).getName());
                                    dianjiaPhone.setText("手机：" + addresses.get(position).getPhone());
                                    //确认订单页面的 收货地址

                                    dianjiaNumber.setText("总数："+number);
                                    dianjiaPrice.setText("总金额："+moneysum);
                                    //点击详情页面前选中的每一个菜品的 分量和钱数
                                    initClick();
                                    //RecycleView 你选中的每一个数量的菜品 展现的数量集合
                                }
                            });

                        }
                    });
                    a = 2;
                    //然后赋值a=2
                } else {
                    sureRecycle.setVisibility(View.GONE);
                    //再点击一次收货地址的小按钮 收货地址的详细信息为不显示
                    a = 1;
                    //然后赋值a=1 重复点击生效
                }
            }
        });
    }

    private void initClick() {
        //点击的菜品信息的显示
        click.setLayoutManager(new LinearLayoutManager(Order_sure.this));
        click.setAdapter(new ClickAdapter(Order_sure.this,cais));
    }

    private void initView() {
        takeAddress = (LinearLayout) findViewById(R.id.take_address);
        surePosition = (ImageView) findViewById(R.id.sure_position);
        sureRecycle = (RecyclerView) findViewById(R.id.sure_recycle);
        dianjiaAddress = (TextView) findViewById(R.id.dianjia_address);
        dianName = (TextView) findViewById(R.id.dian_name);
        dianjiaPhone = (TextView) findViewById(R.id.dianjia_phone);
        dianjiaName = (TextView) findViewById(R.id.dianjia_name);
        dianjiaImage = (ImageView) findViewById(R.id.dianjia_image);
        dainjiaTitle = (TextView) findViewById(R.id.dainjia_title);
        dianjiaNumber = (TextView) findViewById(R.id.dianjia_number);
        dianjiaPrice = (TextView) findViewById(R.id.dianjia_price);
        tijiao = (Button) findViewById(R.id.tijiao);
        visition = (LinearLayout) findViewById(R.id.visition);
        click = (RecyclerView) findViewById(R.id.click);
    }
}