package com.example.newtext.Traffic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.R;

public class TrafficActivity extends BaseActivity {

    private TextView carName;
    private Spinner spinner;
    private EditText carCarId;
    private EditText carNumber;
    private Button carChaxun;
    private TextView carUser;
    private Spinner carSpinner;

    private String[] carshow = {"京", "沪", "津", "渝", "鲁", "冀", "晋", "蒙", "辽", "吉", "黑", "苏", "浙", "皖", "闽", "赣", "豫", "湘", "鄂", "粤", "桂", "琼", "川", "贵", "云", "藏", "陕", "甘", "青", "宁", "新", "港", "澳", "台"};
    private String[] carType = {"小型车辆", "大型车辆"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        setTitle("违章查询");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initSpinner1();
        initSpinner();
        initChick();
    }

    private void initChick() {
        carChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = carCarId.getText().toString().trim();
                if (ID.isEmpty()) {
                    Toast.makeText(TrafficActivity.this, "请输入正确的车牌号！", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(TrafficActivity.this, ChaxunActivity.class));
                }
            }
        });
    }

    private void initSpinner1() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.car_type, carType);
        carSpinner.setAdapter(adapter);
        carSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                carName.setText("" + carType[i]);
                carName.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initSpinner() {
        //车牌号地区选择的事件处理
        for (int i = 0; i < carshow.length; i++) {
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.car_choice, carshow);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    carUser.setText("" + carshow[i]);
                    carUser.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void initView() {
        carName = (TextView) findViewById(R.id.car_name);
        spinner = (Spinner) findViewById(R.id.spinner);
        carCarId = (EditText) findViewById(R.id.car_carId);
        carNumber = (EditText) findViewById(R.id.car_number);
        carChaxun = (Button) findViewById(R.id.car_chaxun);
        carUser = (TextView) findViewById(R.id.car_user);
        carSpinner = (Spinner) findViewById(R.id.car_spinner);
    }
}