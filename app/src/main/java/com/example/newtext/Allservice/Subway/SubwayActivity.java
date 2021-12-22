package com.example.newtext.Allservice.Subway;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.Subway;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class SubwayActivity extends AppCompatActivity {

    private SearchView svSubway;
    private RecyclerView rvSubway;
    //绑定控件的id
    List<Subway> subways;
    //全局变量地铁的Bean集合类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway);
        setTitle("地铁查询");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();  //搜索框的事件处理
        initData();  //地铁站点的时间处理
    }

    private void initData() {
        rvSubway = (RecyclerView) findViewById(R.id.rv_subway);
        Network.doGet("/prod-api/api/metro/list?currentName=建国门", new OkResult() {
            //get请求地铁站点的数据
            @Override
            public void succes(JSONObject jsonObject) {
                subways = new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<Subway>>(){
                }.getType());
                rvSubway.setLayoutManager(new LinearLayoutManager(SubwayActivity.this));
                rvSubway.setAdapter(new SubwayAdapter(SubwayActivity.this,subways));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
       /* svSubway = (SearchView) findViewById(R.id.sv_subway);
        svSubway.setSubmitButtonEnabled(true);
        svSubway.setQueryHint("输入内容");
        svSubway.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Mytoken.isFastDoubleClick()){
                    return false;
                }else{
                    get_dialog(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String next) {
                return false;
            }
        });
    }

    private void get_dialog(String query) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        View view = LayoutInflater.from(this).inflate(R.layout.item_station,null);
        ab.setView(view);*/
    }
}