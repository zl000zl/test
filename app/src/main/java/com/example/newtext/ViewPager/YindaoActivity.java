package com.example.newtext.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.newtext.Pagerlayout5.EnterActivity;
import com.example.newtext.Pagerlayout5.PortActivity;
import com.example.newtext.R;

import java.util.ArrayList;

public class YindaoActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout lltDots;
    private Button networkBtn;
    private Button enterBtn;
    private LinearLayout circle;
    private int currentIndex = 0;

    private int[] data = {R.drawable.yindao1,R.drawable.yindao2,R.drawable.yindao3,
            R.drawable.yindao4,R.drawable.yindao5,};
    //定义图片的集合

    int a=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindao);
        getSupportActionBar().hide();
        initView();

        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int img : data){
            //for循环来执行图片显示的的效果和ViewPager滑动的效果
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(img);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }

        viewPager.setAdapter(new PagerAdapter() {
            //设置viewPager的适配器
            @Override
            public int getCount() {
                return imageViews.size();
                //需要显示的子控件的个数
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = imageViews.get(position);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View)object);
            }
        });
        for (int i=0; i<imageViews.size(); i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20,20);
            View view = new View(this);
            view.setBackgroundResource(R.drawable.select_backgroud);
            view.setEnabled(false);
            if (i != 0){
                layoutParams.leftMargin = 10;
            }else view.setEnabled(true);{
                circle.addView(view,layoutParams);
            }

            networkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a=a+1;
                    startActivity(new Intent(YindaoActivity.this, PortActivity.class));
                }
            });
                enterBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (a==1){
                            Toast.makeText(YindaoActivity.this, "请先设置IP端口！！！", Toast.LENGTH_SHORT).show();
                        }else {
                            startActivity(new Intent(YindaoActivity.this, EnterActivity.class));
                        }
                    }
                });

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    //设置在第五张图片上显示的按钮，前面不显示
                    if (position == imageViews.size()-1){
                        enterBtn.setVisibility(View.VISIBLE);
                        networkBtn.setVisibility(View.VISIBLE);
                    }else {
                        enterBtn.setVisibility(View.INVISIBLE);
                        networkBtn.setVisibility(View.INVISIBLE);
                    }
                    circle.getChildAt(currentIndex).setEnabled(false);
                    circle.getChildAt(position).setEnabled(true);
                    currentIndex = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        networkBtn = (Button) findViewById(R.id.networkBtn);
        enterBtn = (Button) findViewById(R.id.enterBtn);
        circle = (LinearLayout) findViewById(R.id.circle);
    }
}