package com.example.newtext.Job.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Job.Jobbean.Jianli;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Toudi_resume extends BaseActivity {

    public static String xinshui;
    public static int gongsiId;
    public static int zhiweiId;
    //定义公共变量从Job_xingqing页面上取到post请求所需要的值

    private TextView gangweiName;
    private TextView workJl;
    private TextView schoolJl;
    private TextView toudiTime;
    private Button btnQueding;
    private TextView daiyuMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toudi_resume);
        setTitle("投递简历");
        initView();
        initData();
        initclick();
    }

    private void initclick() {
        //post请求
        btnQueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //上面取到的值在TextView上 这是是取到TextView上的数据 装换成String
                String Gwname = gangweiName.getText().toString().trim();
                String Dymoney = daiyuMoney.getText().toString().trim();
                String dateTime = toudiTime.getText().toString().trim();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("postName", Gwname).put("money", Dymoney).put("satrTime", dateTime)
                            .put("companyId", gongsiId).put("postId", zhiweiId); //一个是公司的Id和职位的Id
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/job/deliver", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code") == 200) {
                            Toast.makeText(Toudi_resume.this, "投递成功！", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Toudi_resume.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initData() {
        //学历和工作经历的请求
        Network.doGet("/prod-api/api/job/resume/queryResumeByUserId/1", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Jianli jianlis = new Gson().fromJson(jsonObject.optString("data"), Jianli.class);
                workJl.setText("" + jianlis.getExperience());
                schoolJl.setText("" + jianlis.getMostEducation());


                daiyuMoney.setText("" + xinshui);
                gangweiName.setText("" + Job_xiangqing.postname);
                //这两个是从Job_xingqing上传递的值
            }
        });

        toudiTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dp = new DatePickerDialog(Toudi_resume.this, new JobDataPickerDialog(),
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
    }


    private void initView() {
        gangweiName = (TextView) findViewById(R.id.gangwei_name);
        workJl = (TextView) findViewById(R.id.work_jl);
        schoolJl = (TextView) findViewById(R.id.school_jl);
        toudiTime = (TextView) findViewById(R.id.toudi_time);
        btnQueding = (Button) findViewById(R.id.btn_queding);
        daiyuMoney = (TextView) findViewById(R.id.daiyu_money);
    }

    private class JobDataPickerDialog implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            toudiTime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    }
}