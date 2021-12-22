package com.example.newtext.Job;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Job.Adapter.ResumeAdapter;
import com.example.newtext.Job.Jobbean.Resume;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class RecordFragment extends Fragment {

    private RecyclerView recordRecycle;
    private Button btnRecord;

    List<Resume> resumes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_record, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/job/deliver/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                resumes = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Resume>>() {
                }.getType());
                recordRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                recordRecycle.setAdapter(new ResumeAdapter(getActivity(),resumes.subList(0,5)));
            }
        });
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordRecycle.setAdapter(new ResumeAdapter(getActivity(),resumes));
            }
        });
    }

    private void initView(View getView) {
        recordRecycle = (RecyclerView) getView.findViewById(R.id.record_recycle);
        btnRecord = (Button) getView.findViewById(R.id.btn_record);

    }
}