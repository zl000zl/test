package com.example.newtext.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.Room;
import com.example.newtext.Mytoken;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private SearchView searchView;
    private Banner banner;
    private RecyclerView recycleView;
    private TabLayout tablayout;
    private RecyclerView mesRecycleView;
    private TextView seachContent;
    List<Room> rooms;
    List<Integer> images = new ArrayList<>();
    //定义集合放轮播图
    List<String> houseType = new ArrayList<>();

    //定义集合放请求的Tab分类方便下面拿来使用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        setTitle("房源展示");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initRecycle();
        initTablayout();
        initsearchView();
    }

    private void initsearchView() {
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Mytoken.isFastDoubleClick()) {
                    return false;
                } else {
                    search_content(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void search_content(String query) {
        Network.doGet("/prod-api/api/house/housing/list?houseType=" + query, new OkResult() {
            //这里的请求需要带上houseType房子的类型  才能点击每条分类展示不同的数据
            @Override
            public void succes(JSONObject jsonObject) {
                List<Room> rooms = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Room>>() {
                }.getType());

                if (rooms.size() == 0) {
                    seachContent.setVisibility(View.VISIBLE);
                    mesRecycleView.setVisibility(View.GONE);
                }else {
                    seachContent.setVisibility(View.GONE);
                    mesRecycleView.setVisibility(View.VISIBLE);
                }

                mesRecycleView.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
                mesRecycleView.setAdapter(new TabAdapter(RoomActivity.this, rooms));
            }
        });
    }

    private void initTablayout() {
        //定义分类里的分割线
        LinearLayout linearLayout = (LinearLayout) tablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(20);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.rv_line));
    }

    private void initRecycle() {
        //功能分类
        recycleView.setLayoutManager(new GridLayoutManager(this, 4));
        recycleView.setAdapter(new RoomAdapter(this,mesRecycleView));
    }

    private void initData() {
        Network.doGet("/prod-api/api/house/housing/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                rooms = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Room>>() {
                }.getType());
                //轮播图
                banner.setImages(rooms).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Room room = (Room) path;
                        Glide.with(imageView).load(Mytoken.URl + room.getPic()).into(imageView);
                    }
                }).start();
                /*-------------------------------------------------------------------------------------------------------*/
                //循环取出功能分类的4条数据
                for (int i = 0; i < rooms.size(); i++) {
                    //for循环取出rooms里的15条数据
                    int b = 0;  //定义b为0  为了判断rooms不同数据里的tab类别
                    if (houseType.size() == 0) {  //如果定义的集合里数据为0的情况下
                        houseType.add(rooms.get(i).getHouseType());
                        //就添加一条数据
                    } else {  //否则的话 集合就有数据了
                        for (int a = 0; a < houseType.size(); a++) {
                            // for循环集合里添加的每一条数据比较是否相同
                            if (rooms.get(i).getHouseType().equals(houseType.get(a))) {
                                //有相同的类型 b为1 就跳出循环了
                                b = 1;
                            }
                        }
                        if (b == 0) {  //如果b为0的情况下
                            houseType.add(rooms.get(i).getHouseType());
                            //就取得不同的数据
                        }
                    }
                }
                for (int i = 0; i < houseType.size(); i++) {
                    //for循环取出集合里的所有数据
                    tablayout.addTab(tablayout.newTab().setText(houseType.get(i)).setTag(houseType.get(i)));
                    //添加到tab列表中去  setTag（取出tab里的每一个类别的类型值）
                }
                loadDate(rooms.get(0).getHouseType());
                //取出从集合houseType里得到的4条分类里的数据放进loadDate中
                /*-------------------------------------------------------------------------------------------------------*/
            }
        });
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //tab分类的点击事件
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String houseType = tab.getTag().toString();
                //定义每个功能分类的类别装换为toString类型
                loadDate(houseType);
                //把集合houseType的值带到loadData方法中去
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void loadDate(String houseType) {  //请求每条功能分类下面的展示数据
        Network.doGet("/prod-api/api/house/housing/list?houseType=" + houseType, new OkResult() {
            //这里的请求需要带上houseType房子的类型  才能点击每条分类展示不同的数据
            @Override
            public void succes(JSONObject jsonObject) {
                List<Room> rooms = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Room>>() {
                }.getType());
                mesRecycleView.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
                mesRecycleView.setAdapter(new TabAdapter(RoomActivity.this, rooms));
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        searchView = (SearchView) findViewById(R.id.searchView);
        banner = (Banner) findViewById(R.id.banner);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        mesRecycleView = (RecyclerView) findViewById(R.id.mes_recycleView);
        seachContent = (TextView) findViewById(R.id.seach_content);
    }
}