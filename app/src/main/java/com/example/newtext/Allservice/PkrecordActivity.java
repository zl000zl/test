package com.example.newtext.Allservice;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.PackTwo;
import com.example.newtext.item_Adapter.PtAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

public class PkrecordActivity extends AppCompatActivity {
    /*dialog停车记录*/

    private TextView tvTime;
    private TextView tvDay;
    private TextView tvTime1;
    private TextView tvDay1;
    private Button btnSearch;
    private RecyclerView rvRecord;
    private Button btnMuch1;
    //绑定所有控件的id

    List<PackTwo> packTwos;
    //定义停车场的Bean为全局变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkrecord);
        setTitle("停车记录");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();  //调用控件的布局界面
        initData();
        initDots();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initDots() {
        Network.doGet("/prod-api/api/park/lot/record/list", new OkResult() {
            //get请求.查询停车记录列表
            @Override
            public void succes(JSONObject jsonObject) {
                packTwos = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<PackTwo>>() {
                }.getType());
                rvRecord = (RecyclerView) findViewById(R.id.rv_record);
                rvRecord.setLayoutManager(new LinearLayoutManager(PkrecordActivity.this));
                rvRecord.setAdapter(new PtAdapter(PkrecordActivity.this, packTwos.subList(0, 3)));//显示3个CardView
                //绑定适配器
            }
        });
        btnMuch1.setOnClickListener(new View.OnClickListener() {
            //点击更多记录
            @Override
            public void onClick(View view) {
                rvRecord.setAdapter(new PtAdapter(PkrecordActivity.this, packTwos));//全显示
            }
        });
    }
    /*-------------------------------------------------------------------------------------------------------------*/
    //这是具体日期选择适配器的事件处理
    private void initData() {
        tvDay1.setOnClickListener(new View.OnClickListener() {
            //点击入场时间的事件处理
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                //定义当前的日期
                DatePickerDialog dp = new DatePickerDialog(PkrecordActivity.this, new YouDataPickerDialog(),
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                //然后做事件处理
                dp.show();  //显示出来
            }
        });
        tvDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog dp = new DatePickerDialog(PkrecordActivity.this, new MyDataPickerDialog(),
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
        /*-------------------------------------------------------------------------------------------------------------*/

        /*时间选择适配器的事件处理*/
        tvTime.setOnClickListener(new View.OnClickListener() {
            //这是进场事件
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();//获得当前的时间
                int hour = c.get(Calendar.HOUR_OF_DAY);//定义小时
                int minute = c.get(Calendar.MINUTE);//定义分钟
                new TimePickerDialog(PkrecordActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    //新的时间段的方法
                    @Override
                    public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                        c.set(Calendar.HOUR_OF_DAY, hourofDay);
                        c.set(Calendar.MINUTE, minute);
                        tvTime.setText(hourofDay + ":" + minute);
                        //显示时间
                    }
                }, hour, minute, true).show();//一定要加上
            }
        });
        tvTime1.setOnClickListener(new View.OnClickListener() {
            //这是出场事件
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(PkrecordActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                        c.set(Calendar.HOUR_OF_DAY, hourofDay);
                        c.set(Calendar.MINUTE, minute);
                        tvTime1.setText(hourofDay + ":" + minute);
                    }
                }, hour, minute, true).show();
            }
        });
        /*-------------------------------------------------------------------------------------------------------------*/
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PkrecordActivity.this, PacktwoActivity.class));
                //点击跳转到查询停车记录
            }
        });
    }
    private void initView() {
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvDay = (TextView) findViewById(R.id.tv_day);
        tvTime1 = (TextView) findViewById(R.id.tv_time1);
        tvDay1 = (TextView) findViewById(R.id.tv_day1);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnMuch1 = (Button) findViewById(R.id.btn_much1);
        //调用控件的布局界面
    }

    /*-------------------------------------------------------------------------------------------------------------*/

    private class MyDataPickerDialog implements DatePickerDialog.OnDateSetListener {
        //上面的日期选择适配器的TextView点击赋值
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            tvDay.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    }

    private class YouDataPickerDialog implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            tvDay1.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    }
}