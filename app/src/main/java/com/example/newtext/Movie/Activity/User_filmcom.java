package com.example.newtext.Movie.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.Movie.Adapter.PlAdapter;
import com.example.newtext.Movie.Bean.Pl_film;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class User_filmcom extends BaseActivity {
    public static String filmName;
    private RecyclerView filmCom;
    private EditText comContent;
    private Button comPinglun;
    private RatingBar comRab;

    public static int Movie_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_filmcom);
        setTitle("影片：" + filmName);
        initRecycle();
        initView();
        initsubmit();
    }

    private void initsubmit() {
        comPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment_content = comContent.getText().toString().trim();
                int comment_fen = (int) comRab.getRating();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("content",comment_content).put("movieId",Movie_id)
                            .put("score",comment_fen);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/movie/film/comment", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Network.doGet("/prod-api/api/movie/film/comment/list?movieId=" + Film_xingqing.filmId, new OkResult() {
                                @Override
                                public void succes(JSONObject jsonObject) {
                                    List<Pl_film> pl_films = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Pl_film>>() {
                                    }.getType());
                                    filmCom.setLayoutManager(new LinearLayoutManager(User_filmcom.this));
                                    filmCom.setAdapter(new PlAdapter(User_filmcom.this, pl_films));
                                }
                            });
                            Toast.makeText(User_filmcom.this, "评论成功！！！", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(User_filmcom.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initRecycle() {
        Network.doGet("/prod-api/api/movie/film/comment/list?movieId=" + Film_xingqing.filmId, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Pl_film> pl_films = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Pl_film>>() {
                }.getType());
                filmCom.setLayoutManager(new LinearLayoutManager(User_filmcom.this));
                filmCom.setAdapter(new PlAdapter(User_filmcom.this, pl_films));
            }
        });
    }

    private void initView() {
        filmCom = (RecyclerView) findViewById(R.id.film_com);
        comContent = (EditText) findViewById(R.id.com_content);
        comPinglun = (Button) findViewById(R.id.com_pinglun);
        comRab = (RatingBar) findViewById(R.id.com_rab);
        comRab.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            //触发星星的点击事件
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                comRab.getRating();
            }
        });
    }
}