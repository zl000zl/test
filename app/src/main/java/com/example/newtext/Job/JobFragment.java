package com.example.newtext.Job;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Job.Adapter.JobAdapter;
import com.example.newtext.Job.Adapter.JoblistAdapter;
import com.example.newtext.Job.Jobbean.Joblist;
import com.example.newtext.Job.Jobbean.Jobtitle;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.Mytoken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JobFragment extends Fragment {

    private SearchView jobSearch;
    private Banner banner;
    private RecyclerView jobRecycleView;

    private List<Integer> listImages = new ArrayList<>();


    JobImageLoader jobImageLoader = new JobImageLoader();

    public static RecyclerView listRecycleView;

    public static TextView jobShow;

    public static String job_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_job, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initDots();
        initChick();
        initSearch();
    }

    private void initChick() {
        Network.doGet("/prod-api/api/job/post/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<Joblist> joblists = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Joblist>>(){
                    }.getType());
                    JobFragment.jobShow.setVisibility(View.GONE);
                    listRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    listRecycleView.setAdapter(new JoblistAdapter(getActivity(),joblists));
                }else {
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initSearch() {
        jobSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        jobSearch.setSubmitButtonEnabled(true);
        jobSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Mytoken.isFastDoubleClick()){
                    return false;
                }else {
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
        Network.doGet("/prod-api/api/job/post/list?name=" + query, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<Joblist> joblists = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Joblist>>(){
                    }.getType());

                    if (joblists.size()==0){
                        JobFragment.jobShow.setVisibility(View.VISIBLE);
                        JobFragment.listRecycleView.setVisibility(View.GONE);
                    }else {
                        JobFragment.jobShow.setVisibility(View.GONE);
                        JobFragment.listRecycleView.setVisibility(View.VISIBLE);
                    }
                    listRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    listRecycleView.setAdapter(new JoblistAdapter(getActivity(),joblists));
                }else {
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void initDots() {
        Network.doGet("/prod-api/api/job/profession/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Jobtitle> jobtitles = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Jobtitle>>() {
                }.getType());
                jobRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                jobRecycleView.setAdapter(new JobAdapter(getActivity(), jobtitles, listRecycleView));
            }
        });
    }

    private void initData() {
        listImages.add(R.drawable.gongzuo);
        listImages.add(R.drawable.gongzuo1);
        listImages.add(R.drawable.gongzuo2);
        banner.setImages(listImages).setImageLoader(jobImageLoader);
        banner.start();
    }

    private class JobImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(imageView).load(path).into(imageView);
        }
    }

    private void initView(View getView) {
        jobSearch = (SearchView) getView.findViewById(R.id.job_search);
        banner = (Banner) getView.findViewById(R.id.banner);
        jobRecycleView = (RecyclerView) getView.findViewById(R.id.job_recycleView);
        listRecycleView = (RecyclerView) getView.findViewById(R.id.list_recycleView);
        jobShow = (TextView) getView.findViewById(R.id.job_show);
    }
}