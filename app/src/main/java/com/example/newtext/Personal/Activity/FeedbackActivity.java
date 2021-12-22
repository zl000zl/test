package com.example.newtext.Personal.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackActivity extends AppCompatActivity {

    private EditText etMsg;
    private TextView tvMsg;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        setTitle("意见反馈");
        initView();
        initSubmit();

        initData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initSubmit() {
        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text =etMsg.getText().toString().trim();
                tvMsg.setText(text.length()+"/150");
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void initData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etMsg.getText().toString().trim();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("content",text);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/common/feedback", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(FeedbackActivity.this, "谢谢你的宝贵意见！！！", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(FeedbackActivity.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void initView() {
        etMsg = (EditText) findViewById(R.id.et_msg);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }
}