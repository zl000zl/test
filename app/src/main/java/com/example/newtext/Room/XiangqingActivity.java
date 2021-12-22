package com.example.newtext.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.R;
import com.example.newtext.StringBean.Room;
import com.example.newtext.Mytoken;

public class XiangqingActivity extends BaseActivity {

    private TextView xqTitle;
    private ImageView xqImage;
    private TextView xqContext;
    private TextView xqPrice;
    private TextView xqMianji;
    private TextView xqLeixing;
    private ImageView xqImage2;
    private ImageView xqImage3;

    public static Room room;
    private TextView xqPhone;
    private TextView xqAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        setTitle("住房详情");
        initView();
        initData();
    }

    private void initData() {
        Glide.with(xqImage).load(Mytoken.URl + room.getPic()).into(xqImage);
        xqTitle.setText("" + room.getSourceName());
        xqPrice.setText("" + room.getPrice());
        xqMianji.setText("" + room.getAreaSize() + "㎡");
        xqLeixing.setText("类型：" + room.getHouseType());
        xqPhone.setText("联系方式：" + room.getTel());
        xqAdress.setText("地址："+room.getAddress());
        xqContext.setText("介绍："+room.getDescription());

    }

    private void initView() {
        xqTitle = (TextView) findViewById(R.id.xq_title);
        xqImage = (ImageView) findViewById(R.id.xq_image);
        xqContext = (TextView) findViewById(R.id.xq_content);
        xqPrice = (TextView) findViewById(R.id.xq_price);
        xqMianji = (TextView) findViewById(R.id.xq_mianji);
        xqLeixing = (TextView) findViewById(R.id.xq_leixing);
        xqImage2 = (ImageView) findViewById(R.id.xq_image2);
        xqImage3 = (ImageView) findViewById(R.id.xq_image3);
        xqPhone = (TextView) findViewById(R.id.xq_phone);
        xqAdress = (TextView) findViewById(R.id.xq_adress);

        xqImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}