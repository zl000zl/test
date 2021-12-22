package com.example.newtext.Job.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Job.Jobbean.Xingqing;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Job_xiangqing extends BaseActivity {

    public static int jobId;
    private TextView companyIntro;
    private TextView postName;
    private TextView postDuty;
    private TextView companyAddress1;
    private TextView postMoney;
    private TextView postPersonal;
    private TextView postContent;
    private TextView postYear;
    private Button btnResume;

    Xingqing xingqings;

    public static String postname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_xiangqing);
        setTitle("职位详情");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/job/post/" + jobId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    xingqings = new Gson().fromJson(jsonObject.optString("data"), Xingqing.class);
                    companyIntro.setText("" + xingqings.getObligation());
                    postName.setText("职位名称：" +postname);
                    postDuty.setText("岗位职责：" + xingqings.getObligation());
                    companyAddress1.setText("公司地址：" + xingqings.getAddress());
                    postMoney.setText("资薪待遇：" + xingqings.getSalary());
                    postPersonal.setText("联系人：" + xingqings.getContacts());
                    postYear.setText("工作年限：" + xingqings.getNeed());
                } else {
                    Toast.makeText(Job_xiangqing.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
                btnResume.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toudi_resume.gongsiId = xingqings.getCompanyId();
                        Toudi_resume.zhiweiId = xingqings.getProfessionId();
                        Toudi_resume.xinshui = xingqings.getSalary();

                        startActivity(new Intent(Job_xiangqing.this,Toudi_resume.class));
                    }
                });
            }
        });
    }

    private void initView() {
        companyIntro = (TextView) findViewById(R.id.company_intro);
        postName = (TextView) findViewById(R.id.post_name);
        postDuty = (TextView) findViewById(R.id.post_duty);
        companyAddress1 = (TextView) findViewById(R.id.company_address1);
        postMoney = (TextView) findViewById(R.id.post_money);
        postPersonal = (TextView) findViewById(R.id.post_personal);
        postYear = (TextView) findViewById(R.id.post_year);
        btnResume = (Button) findViewById(R.id.btn_resume);
    }
}