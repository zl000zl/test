package com.example.newtext.Traffic.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.R;

public class ParticularsActivity extends BaseActivity {

    private TextView carTime;
    private TextView carAddress1;
    private TextView carBehavior;
    private TextView carBook;
    private TextView carMarks;
    private TextView carMoney1;

    public static String date ;
    public static String address ;
    public static String xingwei ;
    public static String shuhao ;
    public static String koufen ;
    public static String jinen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars);
        setTitle("违章详情");
        initView();
        initGet();
    }

    private void initGet() {
        carTime.setText(""+date);
        carAddress1.setText(""+address);
        carBehavior.setText(""+xingwei);
        carBook.setText("" + shuhao);
        carMarks.setText(koufen+"分");
        carMoney1.setText(jinen+"元");
    }

    private void initView() {
        carTime = (TextView) findViewById(R.id.car_time);
        carAddress1 = (TextView) findViewById(R.id.car_address1);
        carBehavior = (TextView) findViewById(R.id.car_behavior);
        carBook = (TextView) findViewById(R.id.car_book);
        carMarks = (TextView) findViewById(R.id.car_marks);
        carMoney1 = (TextView) findViewById(R.id.car_money1);
    }
}