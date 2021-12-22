package com.example.newtext.Job.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Job.Activity.Job_xiangqing;
import com.example.newtext.Job.Jobbean.Joblist;
import com.example.newtext.R;

import java.util.List;

public class JoblistAdapter extends RecyclerView.Adapter<JoblistAdapter.InnerHolder> {
    private Context context;
    List<Joblist> joblists;

    public JoblistAdapter(Context context, List<Joblist> joblists) {
        this.context = context;
        this.joblists = joblists;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joblist, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.zwName.setText("" + joblists.get(position).getName());
        holder.gwZhize.setText("" + joblists.get(position).getObligation());
        holder.companyAddress.setText("" + joblists.get(position).getAddress());
        holder.moneyDaiyu.setText("" + joblists.get(position).getSalary());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Job_xiangqing.postname = joblists.get(position).getName();

                Job_xiangqing.jobId = joblists.get(position).getId();
                context.startActivity(new Intent(context,Job_xiangqing.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return joblists.size();
    }
    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView zwName;
        private TextView gwZhize;
        private TextView companyAddress;
        private TextView moneyDaiyu;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            zwName = (TextView) itemView.findViewById(R.id.zw_name);
            gwZhize = (TextView) itemView.findViewById(R.id.gw_zhize);
            companyAddress = (TextView) itemView.findViewById(R.id.company_address);
            moneyDaiyu = (TextView) itemView.findViewById(R.id.money_daiyu);
        }
    }
}
