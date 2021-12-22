package com.example.newtext.Movie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.CinemaAdapter;
import com.example.newtext.Movie.Bean.Film_xq;
import com.example.newtext.Movie.Bean.Session;
import com.example.newtext.Mytoken;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Buy_ticket extends BaseActivity {

    private ImageView buyImage;
    private TextView buyName;
    private TextView buyTitle;
    private RatingBar buyRab;
    private TextView buyDate;
    private TextView buyTime;
    private TextView buyThink;
    private TabLayout buyTab;
    private RecyclerView buyRecycle;

    private List<Session> sessions;

    public static String MovieAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        setTitle("特惠购票");
        initView();
        initData();
        initTab();
    }

    private void initTab() {
        Network.doGet("/prod-api/api/movie/theatre/times/list?movieId=" + Film_xingqing.filmId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                sessions = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Session>>(){
                }.getType());
                for (Session session: sessions){
                    buyTab.addTab(buyTab.newTab().setText(session.getPlayDate()));
                }
                loadData(sessions.get(0).getPlayDate());
            }
        });
        buyTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String strId = tab.getText().toString();
                loadData(strId);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadData(Object strId) {
        Network.doGet("/prod-api/api/movie/theatre/times/list?movieId=" + Film_xingqing.filmId + "&playDate=" + strId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    sessions = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Session>>(){
                    }.getType());
                    if (sessions.size()!=0){
                        CinemaAdapter cinemaAdapter = new CinemaAdapter();
                        buyRecycle.setLayoutManager(new LinearLayoutManager(Buy_ticket.this));
                        buyRecycle.setAdapter(cinemaAdapter);
                        cinemaAdapter.setSessions(sessions);
                        cinemaAdapter.notifyDataSetChanged();

                        cinemaAdapter.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                RondaActivity.cinname =sessions.get(position).getTheatreName();

                                RondaActivity.date_time = sessions.get(position).getPlayDate();

                                startActivity(new Intent(Buy_ticket.this,RondaActivity.class));

                            }
                        });
                    }else {
                        Toast.makeText(Buy_ticket.this, "您看不见我！！！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Buy_ticket.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/movie/film/detail/" + Film_xingqing.filmId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Film_xq film_xq = new Gson().fromJson(jsonObject.optString("data"), Film_xq.class);
                Glide.with(buyImage).load(Mytoken.URl + film_xq.getCover()).into(buyImage);
                buyName.setText("" + film_xq.getName());
                buyTitle.setText("" + film_xq.getLanguage());
                buyRab.setRating((int) +film_xq.getScore());
                buyDate.setText("上映：" + film_xq.getPlayDate());
                buyTime.setText("时长：" + film_xq.getDuration());
                buyThink.setText("想看：" + film_xq.getLikeNum() + "人");
            }
        });
    }

    private void initView() {
        buyImage = (ImageView) findViewById(R.id.buy_image);
        buyName = (TextView) findViewById(R.id.buy_name);
        buyTitle = (TextView) findViewById(R.id.buy_title);
        buyRab = (RatingBar) findViewById(R.id.buy_rab);
        buyDate = (TextView) findViewById(R.id.buy_date);
        buyTime = (TextView) findViewById(R.id.buy_time);
        buyThink = (TextView) findViewById(R.id.buy_think);
        buyTab = (TabLayout) findViewById(R.id.buy_tab);
        buyRecycle = (RecyclerView) findViewById(R.id.buy_recycle);
    }
}