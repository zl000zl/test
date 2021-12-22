package com.example.newtext.NewClass;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.example.newtext.StringBean.Press;
import com.example.newtext.Mytoken;

public class NotificActivity extends AppCompatActivity {
    public static Press press;
    private TextView tvNotifictitle;
    private ImageView ivNotificimage;
    private TextView tvContent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notific);
        setTitle("今日要闻");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        Glide.with(ivNotificimage).load(Mytoken.URl+press.getCover()).into(ivNotificimage);
        tvNotifictitle.setText(press.getTitle());
        tvContent.setText(Html.fromHtml(press.getContent(),1));
    }

    private void initView() {
        tvNotifictitle = (TextView) findViewById(R.id.tv_notifictitle);
        ivNotificimage = (ImageView) findViewById(R.id.iv_notificimage);
        tvContent = (TextView) findViewById(R.id.tv_content);
    }
}