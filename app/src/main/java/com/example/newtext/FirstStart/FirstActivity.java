package com.example.newtext.FirstStart;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.R;
import com.example.newtext.ViewPager.YindaoActivity;

public class FirstActivity extends AppCompatActivity {
    private TextView textView;
    /*private ImageView imageView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        /* imageView = (ImageView)findViewById(R.id.imageView) ;*/
       /*Glide.with(imageView).load(Mytoken.URl+"/prod-api/profile/upload/image/2021/05/06/d2aeef1a-7c47-46bc-8534-20b3d14cd8eb.png")
              .into(imageView);*/

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }  //隐藏上面的标题
        /*-------------------------------------------------------------------------------------------------------------*/
        CountDownTimer timer = new CountDownTimer(2000, 1000) {
            //实现倒计时功能，millisInFuture:倒计时的总长，countDownInterval:每次间隔的时间，单位为毫秒
            //带下面两个参数：
            @Override
            public void onTick(long millisInFuture) {
                textView = (TextView) findViewById(R.id.textView);
                textView.setText(millisInFuture / 1000 + "");
                //剩余的时间除于每次间隔的时间得到倒计时
            }
            @Override
            /*这个是倒计时结束的回调*/
            public void onFinish() {
            }
        }.start();
        /*-------------------------------------------------------------------------------------------------------------*/
        //使用Handler让线程在此页面停留3秒
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            //handler.postDelayed()方法用于intent延迟跳转，然后使用Runable()方法
            @Override
            public void run() {
                startActivity(new Intent(FirstActivity.this, YindaoActivity.class));
                finish();
            }
        }, 1000);//延迟的时间
    }
}