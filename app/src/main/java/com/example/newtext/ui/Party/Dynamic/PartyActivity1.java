package com.example.newtext.ui.Party.Dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtext.NewClass.CommentActivity;
import com.example.newtext.R;

import java.util.ArrayList;
import java.util.List;

public class PartyActivity1 extends AppCompatActivity {

    private TextView tvParty1;
    private ImageView ivParty1;
    private TextView tvParty2;
    private EditText etParty1;

    private String[] title1 = {"技术赋能“党建文化+直播”智慧党建新模式"};
    List<Integer> image1 = new ArrayList<>();
    private String[] content1 = {"我们想做点什么？我们还能再多做点什么？这一强烈意愿表达了YC和创显两个党支部共同的心声。经过数次的探讨交流，大家理清了工作思路，策划出精彩脚本，决定以直播的形式赋能党组织开启新时代智慧党建新模式，打造党建智慧新阵地,印象中的党建活动常以线下座谈会或参观红色教育基地的形式进行，一次党建活动需要前期进行人员组织和活动方案规划，但线下的活动仅限于党员的时间安排和到场人员数量，随着线上活动的普及，同样的组织者（主播+助理），同样的活动规划（直播脚本），运用线上直播的形式也可以同时让身在各处的人们同时参加党建活动。"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party1);
        setTitle("党建动态");
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
        int i = 0;
        image1.add(R.drawable.one);
        ivParty1.setImageResource(image1.get(i));
        tvParty1.setText(title1[i]);
        tvParty2.setText("\t\t"+content1[i]);
        etParty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PartyActivity1.this, CommentActivity.class));
            }
        });
    }

    private void initView() {
        tvParty1 = (TextView) findViewById(R.id.tv_party1);
        ivParty1 = (ImageView) findViewById(R.id.iv_party1);
        tvParty2 = (TextView) findViewById(R.id.tv_party2);
        etParty1 = (EditText) findViewById(R.id.et_party1);
    }
}