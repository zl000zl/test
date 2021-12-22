package com.example.newtext.ui.home.SearchView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.Press;
import com.example.newtext.ui.home.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class SearchViewActivity extends AppCompatActivity {

    private RecyclerView searchRecycle;
    private TextView textnull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        setTitle("内容详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        textnull = (TextView)findViewById(R.id.textnull);

        searchRecycle = (RecyclerView) findViewById(R.id.search_recycle);
        Network.doGet("/prod-api/press/press/list?title="+ HomeFragment.search, new OkResult() {
            //这里的HomeFragment.search 就是HomeFragment里的公共方法search
            @Override
            public void succes(JSONObject jsonObject) {
                List<Press> presses = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Press>>(){
                }.getType());
                if (presses.size() !=0){
                    //如果搜索的内容与Title里的内容匹配时触发该方法
                searchRecycle.setLayoutManager(new LinearLayoutManager(SearchViewActivity.this));
                searchRecycle.setAdapter(new SearchAdapter(SearchViewActivity.this,presses));
                textnull.setVisibility(View.GONE);
                //设置TextView里的内容为不显示且不占据位置
                }else {
                    textnull.setVisibility(View.VISIBLE);
                    //否则显示
                }
            }
        });

    }
}