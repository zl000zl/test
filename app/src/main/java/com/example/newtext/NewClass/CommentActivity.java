package com.example.newtext.NewClass;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.Comment;
import com.example.newtext.item_Adapter.CommentAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CommentActivity extends AppCompatActivity {
    private RecyclerView rv_comment;
    private Button btn_comment;
    private EditText et_content;
    //绑定控件的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("全部评论");
        initView();
        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();           /*上放按钮的返回事件*/
        return super.onSupportNavigateUp();
    }

    private void initView() {
        et_content = (EditText) findViewById(R.id.et_content);
        btn_comment = (Button) findViewById(R.id.btn_comment);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = et_content.getText().toString().trim();
                //取到你在评论框里输入的文字
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("newsId", Todaynews.news_name_Id).put("content", content);
                    //发表评论需要带新闻的id值    从Todaynews的公共方法中取新闻的id值
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/press/pressComment", jsonObject.toString(), new OkResult() {
                    //post请求发表新闻评论 里面需要带上在文本框里输入的内容接
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code") == 200) {
                            Toast.makeText(CommentActivity.this, "评论成功！！！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CommentActivity.this, "评论失败！！！", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    private void initData() {
        rv_comment = (RecyclerView) findViewById(R.id.rv_comment);
        Network.doGet("/prod-api/press/comments/list?newsId=" + Todaynews.news_name_Id, new OkResult() {
            //get请求根据新闻的id值 获取到哪一条新闻的全部评论
            //这里的 (?newsId=) 就是取Press新闻集合里的id值
            @Override
            public void succes(JSONObject jsonObject) {
                List<Comment> comments = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Comment>>() {
                }.getType());
                rv_comment.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
                rv_comment.setAdapter(new CommentAdapter(CommentActivity.this, comments));
                //使用适配器来传值
            }
        });

    }
}