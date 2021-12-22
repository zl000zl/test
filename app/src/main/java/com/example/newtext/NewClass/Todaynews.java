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

public class Todaynews extends AppCompatActivity {

    private TextView tvTitle1;
    private ImageView ivImage1;
    private TextView tvNews1;
    private RelativeLayout rl_rlt;

    public static int news_name_Id;
    //定义一个公共新闻id方便后面在哪条新闻里发表评论
    public static Press press;
    //绑定一个静态的方法来便于后面适配器的传值


    private ImageView imageStu;
    private TextView tvShow;
    private TextView tvLike;
    private TextView tvData;
    private RelativeLayout rl_going;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaynews);
        setTitle("新闻详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //这是屏幕上方的小按钮
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
        Glide.with(ivImage1).load(Mytoken.URl + press.getCover()).into(ivImage1);
        //显示每个列表里的图片
        tvTitle1.setText(press.getTitle());
        //显示每个列表里的标题
        tvNews1.setText(Html.fromHtml("\t\t" + press.getContent(), 1));
        //显示每个列表里的文章
        news_name_Id = press.getId();
        //取出新闻详情页的id值 放标TabLayout新闻类别的使用(根据每条新闻的id得到不同的新闻)\

                            /*新闻详情页面的推荐新闻的请求方法*/
        Glide.with(imageStu).load(Mytoken.URl+press.getCover()).into(imageStu);
        tvShow.setText(""+press.getTitle());
        tvLike.setText(""+press.getLikeNum());
        tvData.setText(""+press.getPublishDate());
        rl_going.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    NewclassActivity.press = press;
                    startActivity(new Intent(Todaynews.this,NewclassActivity.class));
                }

        });
    }

    private void initView() {
        imageStu = (ImageView) findViewById(R.id.image_stu);
        tvShow = (TextView) findViewById(R.id.tv_show);
        tvLike = (TextView) findViewById(R.id.tv_like);
        tvData = (TextView) findViewById(R.id.tv_data);
        rl_going = (RelativeLayout)findViewById(R.id.rl_going);


        tvTitle1 = (TextView) findViewById(R.id.tv_title1);
        ivImage1 = (ImageView) findViewById(R.id.iv_image1);
        tvNews1 = (TextView) findViewById(R.id.tv_news1);
        rl_rlt = (RelativeLayout) findViewById(R.id.rl_rlt);
        rl_rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Todaynews.this, CommentActivity.class));
                //点击新闻详情下面的评论跳转到评论列表
            }
        });
    }
}