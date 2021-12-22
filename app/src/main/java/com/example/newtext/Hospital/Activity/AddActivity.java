package com.example.newtext.Hospital.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private EditText addName;
    private RadioButton addBoy;
    private RadioButton addGirl;
    private EditText addData;
    private EditText addIdCard;
    private EditText addPhone;
    private EditText addAdress;
    private Button addFalse;
    private Button addTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("新增就诊人");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    private void initData() {
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog dp = new DatePickerDialog(AddActivity.this,new MyDatePickerDialog(),
                        c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
        addTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addName.getText().toString().trim();
                String sex;
                if (addBoy.isChecked()) {
                    sex = "0";
                } else {
                    sex = "1";
                }
                String date = addData.getText().toString().trim();
                String idCard = addIdCard.getText().toString().trim();
                String phonenumber = addPhone.getText().toString().trim();
                String address = addAdress.getText().toString().trim();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", name).put("sex", sex).put("birthday", date)
                            .put("cardId", idCard).put("tel", phonenumber).put("address", address);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/hospital/patient", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(AddActivity.this, "添加成功，请退出查看！", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(AddActivity.this, "添加失败,确认信息是否有误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        addFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        addName = (EditText) findViewById(R.id.add_name);
        addBoy = (RadioButton) findViewById(R.id.add_boy);
        addGirl = (RadioButton) findViewById(R.id.add_girl);
        addData = (EditText) findViewById(R.id.add_data);
        addIdCard = (EditText) findViewById(R.id.add_idCard);
        addPhone = (EditText) findViewById(R.id.add_phone);
        addAdress = (EditText) findViewById(R.id.add_adress);
        addFalse = (Button) findViewById(R.id.add_false);
        addTrue = (Button) findViewById(R.id.add_true);
    }

    private class MyDatePickerDialog implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            addData.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
        }
    }
}