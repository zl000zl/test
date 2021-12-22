package com.example.newtext.Hospital;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Hospital.Adapter.YuyueAdapter;
import com.example.newtext.HospitalBean.One;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;


public class GuahaoFragment extends Fragment {
    private SearchView searchView;
    private RecyclerView ghRecycleView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guahao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/hospital/hospital/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<One> ones = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<One>>(){
                }.getType());
                ghRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                ghRecycleView.setAdapter(new YuyueAdapter(getActivity(),ones));
            }
        });
    }

    private void initView() {
        searchView = (SearchView)getView().findViewById(R.id.searchView);
        ghRecycleView = (RecyclerView)getView().findViewById(R.id.gh_recycleView);
    }
}