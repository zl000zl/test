package com.example.newtext.Movie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.SeatAdapter;
import com.example.newtext.R;

import java.util.List;

public class SeatActivity extends BaseActivity {

    private RecyclerView seatRecycle;
    private TextView seatDate;
    private TextView seatTime;
    private TextView seatType;

    private Button button3;

    public static String date_seat;
    public static String time_seat;
    public static String type_seat;
    public static RecyclerView listRecycle;

    public static int[] b = new int[80];
    public static int[] c = new int[80];
    //定义选座时点击的位置为公共的变量  一共有80个座位 方便后面得到排数 与座位数；

    public static List<Integer> pai;
    public static List<Integer> zuo;
    //定义两个int集合来储存你选择座位的位置和数量；(SeatAdapter)
    //在XzAdapter中应用这个公共的变量

    public static TextView seatNumber;
    //电影票的数量
    public static TextView seatMoney;
    //电影票的总价钱
    public static int count = 0;
    //定义的公共变量点击座位 变化的数量

    public static double movie_money;
    //从RondaActivity中传入的电影的单价
    public static double price = 0.0;
    //定义公共的变量方便适配器中引用 价格保留两位数
    public static String moviestr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        setTitle("选座");
        initView();
        initData();
        initVisi();
        initClick();
    }

    private void initClick() {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ticker_order.moneySum = moviestr;
                startActivity(new Intent(SeatActivity.this, Ticker_order.class));
            }
        });
    }

    private void initVisi() {
        seatDate.setText("日期：" + date_seat);
        seatTime.setText("" + time_seat);
        switch (type_seat) {
            case "1":
                seatType.setText("国语2D");
                break;
            case "2":
                seatType.setText("国语3D");
                break;
            case "3":
                seatType.setText("国语IMAX");
                break;
            case "4":
                seatType.setText("国语4DX");
                break;
        }
    }

    private void initData() {
        seatRecycle.setLayoutManager(new GridLayoutManager(SeatActivity.this, 16));
        seatRecycle.setAdapter(new SeatAdapter(SeatActivity.this));
    }

    private void initView() {
        seatRecycle = (RecyclerView) findViewById(R.id.seat_recycle);
        seatDate = (TextView) findViewById(R.id.seat_date);
        seatTime = (TextView) findViewById(R.id.seat_time);
        seatType = (TextView) findViewById(R.id.seat_type);
        button3 = (Button) findViewById(R.id.button3);
        listRecycle = (RecyclerView) findViewById(R.id.list_recycle);
        seatNumber = (TextView) findViewById(R.id.seat_number);
        seatMoney = (TextView) findViewById(R.id.seat_money);
    }
}