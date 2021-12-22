package com.example.newtext.Personal.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PassActivity extends AppCompatActivity {

    private EditText etOldpass;
    private EditText etNewpass;
    private Button btnEnterpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        setTitle("修改密码");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initData() {
        btnEnterpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpassword = etOldpass.getText().toString().trim();
                String newpassword = etNewpass.getText().toString().trim();
                //获取账号密码控件上的信息
                if (newpassword.length() >= 6) {//开始请求
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("newPassword", newpassword).put("oldPassword", oldpassword);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPut("/prod-api/api/common/user/resetPwd", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code") == 200) {
                                Toast.makeText(PassActivity.this, "修改密码成功！！！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                } else {
                    Toast.makeText(PassActivity.this, "至少输入6位密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        etOldpass = (EditText) findViewById(R.id.et_oldpass);
        etNewpass = (EditText) findViewById(R.id.et_newpass);
        btnEnterpass = (Button) findViewById(R.id.btn_enterpass);
    }
}