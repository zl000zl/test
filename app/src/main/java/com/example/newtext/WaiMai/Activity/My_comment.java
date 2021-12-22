package com.example.newtext.WaiMai.Activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Adapter.MyAdapter;
import com.example.newtext.WaiMai.Waimaibean.Comment_my;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class My_comment extends BaseActivity {


    private RecyclerView commentRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        setTitle("我的收藏");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/takeout/collect/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Comment_my> comment_my = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Comment_my>>() {
                }.getType());
                commentRecycle.setLayoutManager(new LinearLayoutManager(My_comment.this));
                commentRecycle.setAdapter(new MyAdapter(My_comment.this,comment_my));
            }
        });
    }

    private void initView() {
        commentRecycle = (RecyclerView) findViewById(R.id.comment_recycle);
    }
}