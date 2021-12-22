package com.example.newtext.NewClass;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.example.newtext.StringBean.Press;
import com.example.newtext.Mytoken;

public class NewclassActivity extends AppCompatActivity {

    /*这个界面就作为新闻详情里面的推荐新闻的跳转*/

    private TextView tvTitle;
    private ImageView ivImage;
    private TextView tvNews;
    public static Press press;
    //绑定新闻的公共类的静态方法
    //定义一个公共新闻id方便后面在哪条新闻里发表评论
    private RelativeLayout rl_rlt;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newclass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //加屏幕上方小箭头
        setTitle("新闻详情"); //小箭头上的标题内容
        initView();
        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
        //点击小箭头返回事件
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        Glide.with(ivImage).load(Mytoken.URl +press.getCover()).into(ivImage); //得到图片资源
        tvTitle.setText(press.getTitle());//得到网页资源
        tvNews.setText(Html.fromHtml(press.getContent(),1));//去除网页元素
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvNews = (TextView) findViewById(R.id.tv_news);
        rl_rlt = (RelativeLayout) findViewById(R.id.rl_rlt);
        rl_rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewclassActivity.this,CommentActivity.class));
            }
        });
    }
}