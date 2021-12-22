package com.example.newtext.Job.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Job.Jobbean.Resume;
import com.example.newtext.R;

import java.util.List;

public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.InnerHolder> {
    private Context context;
    List<Resume> resumes;

    public ResumeAdapter(Context context, List<Resume> resumes) {
        this.context = context;
        this.resumes = resumes;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resume, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.jobName.setText(""+resumes.get(position).getPostName());
        holder.companyName.setText(""+resumes.get(position).getCompanyName());
        holder.tvMoney.setText(""+resumes.get(position).getMoney());
        holder.tvDate.setText(""+resumes.get(position).getSatrTime());
    }

    @Override
    public int getItemCount() {
        return resumes.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView jobName;
        private TextView companyName;
        private TextView tvMoney;
        private TextView tvDate;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            jobName = (TextView) itemView.findViewById(R.id.job_name);
            companyName = (TextView) itemView.findViewById(R.id.company_name);
            tvMoney = (TextView) itemView.findViewById(R.id.tv_money);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
