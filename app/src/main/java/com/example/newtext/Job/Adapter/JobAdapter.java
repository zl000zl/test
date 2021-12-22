package com.example.newtext.Job.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Job.JobFragment;
import com.example.newtext.Job.Jobbean.Joblist;
import com.example.newtext.Job.Jobbean.Jobtitle;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.InnerHolder> {
    private Context context;
    List<Jobtitle> jobtitles;
    private RecyclerView listRecycleView;

    public JobAdapter(Context context, List<Jobtitle> jobtitles,RecyclerView listRecycleView) {
        this.context = context;
        this.jobtitles = jobtitles;
        this.listRecycleView = listRecycleView;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.btnJob.setText(""+jobtitles.get(position).getProfessionName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Network.doGet("/prod-api/api/job/post/list?professionId=" + jobtitles.get(position).getId(), new OkResult() {
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
                            listRecycleView.setLayoutManager(new LinearLayoutManager(context));
                            listRecycleView.setAdapter(new JoblistAdapter(context,joblists));
                        }else {
                            Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobtitles.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView btnJob;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            btnJob = (TextView) itemView.findViewById(R.id.btn_job);
        }
    }
}
