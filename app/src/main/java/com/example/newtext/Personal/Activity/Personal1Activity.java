package com.example.newtext.Personal.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Personal1Activity extends AppCompatActivity {

    private ImageView ivHead;
    private TextView userName;
    private RadioButton sex0;
    private RadioButton sex1;
    private TextView midCard;
    private TextView mphonenumber;
    private Button btn_go;
    private ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal1);
        getSupportActionBar().hide();
        //隐藏原来的标题框
        initView();
        initinquire();
        initchange();
    }

    private void initchange() {
        btn_go.setOnClickListener(new View.OnClickListener() {
            //修改数据按钮的点击事件
            @Override
            public void onClick(View view) {
                String mnickName = userName.getText().toString().trim();
                String phonenumber = mphonenumber.getText().toString().trim();
                //取得名字和手机号上输入的文本值
                String sex;
                if (sex0.isChecked()){
                    //判断男如果被点击的情况下
                    sex="0";  //赋给它的值就为“0”
                }else {  //否则的话就为“1”
                    sex="1";
                }
                if (phonenumber.length()!=11){
                    //如果手机号不是11位的情况下
                    Toast.makeText(Personal1Activity.this, "请输入正确的各式！", Toast.LENGTH_SHORT).show();
                }else {  //否则就修改数据

                    JSONObject jsonObject = new JSONObject();

                    try {   /*把你在输入框里输入的值带到jsonObject中  等下放入请求中 修改数据*/ //这里需要抛异常
                        jsonObject.put("nickName",mnickName)
                                .put("phonenumber",phonenumber)
                                .put("sex",sex);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Network.doPut("/prod-api/api/common/user", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code") ==200){
                                Toast.makeText(Personal1Activity.this, "修改成功，退出查看！", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(Personal1Activity.this, "错误信息，参数错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    /*获取到个人的全部信息*/
    private void initinquire() {
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                User user =  new Gson().fromJson(jsonObject.optString("user"),User.class);
                //请求得到数据
                Glide.with(ivHead).load("https://c-ssl.duitang.com/uploads/blog/202107/09/20210709105552_7c4aa.thumb.1000_0.jpeg")
                        .into(ivHead);  //获取头像
                userName.setText(user.getNickName());  //修改时的姓名
                if (user.getSex().equals("0")){   //修改时的性别
                    sex0.setChecked(true);
                }else {
                    sex1.setChecked(true);
                }
                mphonenumber.setText(user.getPhonenumber());  //修改的电话号码
                String idCard_hide = user.getIdCard().substring(0,2);//只展示2个数
                idCard_hide = idCard_hide+"************";//中间加上12个*号
                idCard_hide = idCard_hide+user.getIdCard().substring(user.getIdCard().length()-4);
                //前面有2个数中间加上12个*号后面跟上4个数
                midCard.setText(idCard_hide);
                //展示证件号
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    private void initView() {
        ivHead = (ImageView) findViewById(R.id.iv_head);
        userName = (TextView) findViewById(R.id.userName);
        sex0 = (RadioButton) findViewById(R.id.sex0);
        sex1 = (RadioButton) findViewById(R.id.sex1);
        midCard = (TextView) findViewById(R.id.midCard);
        mphonenumber = (TextView) findViewById(R.id.mphonenumber);
        btn_go = (Button) findViewById(R.id.btn_go);

        ivBack = (ImageView) findViewById(R.id.iv_back);

        ivBack.setOnClickListener(new View.OnClickListener() {
            //退出时的事件处理
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}