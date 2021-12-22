package com.example.newtext.Movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.Activity.Cinema_xingq;
import com.example.newtext.Movie.Adapter.RimAdapter;
import com.example.newtext.Movie.Bean.Rim_cinema;
import com.example.newtext.Movie.cinemaBean.Weizhi;
import com.example.newtext.Mytoken;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Movie_cinema extends Fragment {


    private SearchView pianSearch;
    private RecyclerView pianRecycle;
    private TextView dingwei;
    private TextView pianVisi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_cinema, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initRecycleView();
        initSearchView();
    }

    private void initSearchView() {
        pianSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        pianSearch.setSubmitButtonEnabled(true);
        pianSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Mytoken.isFastDoubleClick()) {
                    return false;
                } else {
                    search_count(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String next) {
                return false;
            }
        });
    }

    private void search_count(String query) {
        Network.doGet("/prod-api/api/movie/theatre/list?address=" + query, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Rim_cinema> rim_cinemas = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Rim_cinema>>() {
                }.getType());

                if (rim_cinemas.size()!=0){
                    RimAdapter rimAdapter = new RimAdapter();
                    pianRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                    pianRecycle.setAdapter(rimAdapter);

                    rimAdapter.setRim_cinemas(rim_cinemas);
                    rimAdapter.notifyDataSetChanged();
                }else {
                    pianRecycle.setVisibility(View.GONE);
                    pianVisi.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initRecycleView() {
        Network.doGet("/prod-api/api/movie/theatre/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Rim_cinema> rim_cinemas = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Rim_cinema>>() {
                }.getType());

                RimAdapter rimAdapter = new RimAdapter();
                pianRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                pianRecycle.setAdapter(rimAdapter);

                rimAdapter.setRim_cinemas(rim_cinemas);
                rimAdapter.notifyDataSetChanged();
                rimAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Cinema_xingq.cinemaId = rim_cinemas.get(position).getId();
                        startActivity(new Intent(getActivity(), Cinema_xingq.class));
                    }
                });
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/common/gps/location", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Weizhi weizhi = new Gson().fromJson(jsonObject.optString("data"), Weizhi.class);
                dingwei.setText("" + weizhi.getCity() + "-" + weizhi.getArea() + "-" + weizhi.getLocation());
            }
        });
    }

    private void initView(View getView) {
        pianSearch = (SearchView) getView.findViewById(R.id.pian_search);
        pianRecycle = (RecyclerView) getView.findViewById(R.id.pian_recycle);
        dingwei = (TextView) getView.findViewById(R.id.dingwei);
        pianVisi = (TextView) getView.findViewById(R.id.pian_visi);
    }
}