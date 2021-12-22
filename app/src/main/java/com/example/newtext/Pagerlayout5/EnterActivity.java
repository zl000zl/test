package com.example.newtext.Pagerlayout5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.MainActivity;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EnterActivity extends AppCompatActivity {
    private EditText et_username,et_password;
    private Button btn_denglv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
            getSupportActionBar().hide();
            initView();
            logn_go();
        }

    private void initView() {
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_denglv = findViewById(R.id.btn_denglv);
        btn_denglv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                logn_go();
            }
        });
    }

    private void logn_go() {
          String username = et_username.getText().toString().trim();
          String password = et_password.getText().toString().trim();
          //获取到用户的账号和密码来执行下面的每一条需要账号和密码的执行的
        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
        }else {

            OkHttpClient client = new OkHttpClient();
            //创建OkHttpClient
            JSONObject jsonObject = new JSONObject();
            //(只有在Post/Put请求的时候才创建) 就是{"username":"test01","password":"123456"}
            try {
                jsonObject.put("username","test01");
                jsonObject.put("password","123456");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MediaType parse = MediaType.parse("application/json;charset=utf-8");
            //固定写法  就这样写

            RequestBody requestBody =RequestBody.create(jsonObject.toString(),parse);  /* parse对应的就是MediaType*/
            /*这里的toString就是{"username":"test01","password":"123456"}*/

            Request request = new Request.Builder().url(Mytoken.URl+"/prod-api/api/login")  //固定写法
                    .post(requestBody).build();
            client.newCall(request).enqueue(new Callback() {  //固定写法 把数据传到服务器 服务器返回响应的方法
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {  //这里是子线程 修改UI线程只能在主线程上运行 所以用runOnUiThread开启主线程
                        @Override
                        public void run() {
                            Toast.makeText(EnterActivity.this, "网络连接异常！！！", Toast.LENGTH_SHORT).show();
                            //这里的请求是网络中断或者超时时的请求
                        }
                    });
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    //请求成功后执行的方法
                    String string =response.body().string(); //拿到请求的结果 转为String字符串
                    try {
                        JSONObject jsonObject1 =new JSONObject(string);
                        //如果我们想拿到JSON字符串中某个key对应的Value 就必须给String转换成JSONObject
                        if (jsonObject1.optInt("code")==200){
                            //如果这里的code的值为200就是操作成功 所以返回的时候就有token
                            Mytoken.token = jsonObject1.optString("token");//拿到token值 赋值到上面的私有变量token值

                            startActivity(new Intent(EnterActivity.this, MainActivity.class));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(EnterActivity.this, jsonObject1.optString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else {  //如果token值不是200为请求失败执行下面的Toast里的“msg”为用户不存在/密码错误
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(EnterActivity.this, jsonObject1.optString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}