package com.example.newtext.SmartBus.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.SmartBus.BusBean.Show;
import com.example.newtext.StringBean.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private TextView msgDate;
    private TextView msgTime;
    private TextView msgName;
    private TextView msgPhone;
    private TextView getCar;
    private TextView downCar;
    private Button button2;
    private TextView msgPrice;
    private TextView msgDress;
    private EditText msgOrder;
    private Spinner spinner;
    private Spinner spinner1;

    public static int money1;
    public static String path;
    //定义了公共的变量 从BusAdapter中赋值传过来

    public static List<Show> mshow;
    //定义公共的变量来求所有站点的集合

    List<String> station = new ArrayList<>();
    //定义一个集合来给下面取到所有的地点定义
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        setTitle("乘车信息");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initChick();
        initPersonal();
        initSpinner();
    }

    private void initSpinner() {
        for (int i = 0; i < mshow.size(); i++) {  //循环取出DetailsActivity里shows地点名的所有数据
            station.add(mshow.get(i).getName());
            //添加到上面定义的新的方法名字中
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_choice, station.subList(0,station.size()-1));
        //定义新的显示List列表的适配器  里面放入上下文 文本的xml 上面定义的方法集合后面的sublist是要显示哪些内容的
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner1.setAdapter(new ArrayAdapter<String>(this, R.layout.item_choice, station.subList(1,station.size())));
        //这是下车地点的定义
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //上车地点的点击事件
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                getCar.setText(station.get(position));
                //给上车地点赋上面给的值
                getCar.setVisibility(View.GONE);
                //前面的TextView设置为不显示
                spinner1.setAdapter(new ArrayAdapter<String>(MessageActivity.this, R.layout.item_choice, station.subList(position+1,station.size())));
                //上面的点击事件 下面的列表自动变化 =1操作
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //下车地点的点击事件
                downCar.setText(station.get(position));
                downCar.setVisibility(View.GONE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initPersonal() {
        //获取到这个App的姓名和手机号
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                User user = new Gson().fromJson(jsonObject.optString("user"), User.class);
                msgName.setText(user.getNickName());
                msgPhone.setText(user.getPhonenumber());
            }
        });
        msgPrice.setText(money1 + "");
        msgDress.setText(path);
        msgOrder.setText("0");
        //给文本框上赋值
    }
    /*-------------------------------------------------------------------------------------------------------------*/

    private void initChick() {
        button2.setOnClickListener(new View.OnClickListener() {
            //点击确认订单的
            @Override
            public void onClick(View view) {
                //获取到post请求所需要的的信息  这里是取值
                String getOn = getCar.getText().toString().trim();
                String getOff = downCar.getText().toString().trim();
                int Price = money1;
                String dress = path;
                String order = msgOrder.getText().toString().trim();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("start", getOn).put("end", getOff).put("price", Price)
                            .put("path", dress).put("status", order);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/bus/order", jsonObject.toString(), new OkResult() {
                    //post请求 来提交订单
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code") == 200) {
                            startActivity(new Intent(MessageActivity.this, TrueActivity.class));
                        } else {
                            Toast.makeText(MessageActivity.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initData() {
        //日期选择监听器
        msgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog dp = new DatePickerDialog(MessageActivity.this, new MsgDataPickerDialog(),
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });

        msgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(MessageActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        msgTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true).show();
                ;
            }
        });
    }

    private void initView() {
        msgDate = (TextView) findViewById(R.id.msg_date);
        msgTime = (TextView) findViewById(R.id.msg_time);
        msgName = (TextView) findViewById(R.id.msg_name);
        msgPhone = (TextView) findViewById(R.id.msg_phone);
        getCar = (TextView) findViewById(R.id.get_car);
        downCar = (TextView) findViewById(R.id.down_car);
        button2 = (Button) findViewById(R.id.button2);
        msgPrice = (TextView) findViewById(R.id.msg_price);
        msgDress = (TextView) findViewById(R.id.msg_dress);
        msgOrder = (EditText) findViewById(R.id.msg_order);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
    }

    private class MsgDataPickerDialog implements DatePickerDialog.OnDateSetListener {
        //日期选择监听器的方法
        @Override
        public void onDateSet(DatePicker datePicker, int Year, int MonthOfYear, int DayOfMonth) {
            msgDate.setText(Year + "-" + (MonthOfYear + 1) + "-" + DayOfMonth);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}