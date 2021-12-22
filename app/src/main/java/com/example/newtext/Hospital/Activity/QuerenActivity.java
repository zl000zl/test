package com.example.newtext.Hospital.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class QuerenActivity extends AppCompatActivity {

    private TextView qrName;
    private TextView qrRoom;
    private TextView qrClass;
    private TextView qrMoney;
    private EditText qrDate;
    private EditText qrTime;
    private Button qrNo;
    private Button qrYes;

    public static String name;
    //定义了一个公共的变量 从JiuzhengAdapter里传入姓名的值 带过来

    public static String keshi;
    public static String leixing;
    //定义公共的变量 从XiugaiActivity中传入科室类型的值
    public static int qian;
    //定义公共变量 从XiugaiActivity中传入金额 注意数字是int类型


    public static int ksId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queren);
        setTitle("预约确认单");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initDots();
        initChick();
    }

    private void initChick() {
        qrNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuerenActivity.this,HistoryActivity.class));
            }
        });
        qrYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Class = qrClass.getText().toString().trim();
                int money = qian;
                String date = qrDate.getText().toString().trim();
                String time = qrTime.getText().toString().trim();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("categoryId",ksId).put("type",Class).put("money",money)
                           .put("patientName",name).put("reserveTime",date+" "+time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/hospital", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(QuerenActivity.this, "恭喜您，预约成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(QuerenActivity.this,HistoryActivity.class));
                        }else {
                            Toast.makeText(QuerenActivity.this,jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initDots() {
        qrDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog dp = new DatePickerDialog(QuerenActivity.this,new MyDatePickerDialog(),
                        c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
        qrTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(QuerenActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        qrTime.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,true).show();
            }
        });
    }

    /*这里是给TextView赋值的  数据是从JiuzhengActivity/Classify中传来的*/
    private void initData() {
        qrName.setText(""+name);
        if (leixing.equals("1")){ //判断如果科室类型是1的情况下
            qrClass.setText("专家号");
        }else {  //如果科室类型是2的情况下
            qrClass.setText("普通号");
        }
        qrRoom.setText(keshi);
        qrMoney.setText((qian+"元"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        qrName = (TextView) findViewById(R.id.qr_name);
        qrRoom = (TextView) findViewById(R.id.qr_room);
        qrClass = (TextView) findViewById(R.id.qr_class);
        qrMoney = (TextView) findViewById(R.id.qr_money);
        qrDate = (EditText) findViewById(R.id.qr_date);
        qrTime = (EditText) findViewById(R.id.qr_time);
        qrNo = (Button) findViewById(R.id.qr_no);
        qrYes = (Button) findViewById(R.id.qr_yes);
    }

    private class MyDatePickerDialog implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            qrDate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
        }
    }
}