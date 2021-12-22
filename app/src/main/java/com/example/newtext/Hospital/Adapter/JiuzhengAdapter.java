package com.example.newtext.Hospital.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Hospital.Activity.QuerenActivity;
import com.example.newtext.Hospital.Activity.XiugaiActivity;
import com.example.newtext.HospitalBean.Chaxun;
import com.example.newtext.R;

import java.util.List;

public class JiuzhengAdapter extends RecyclerView.Adapter<JiuzhengAdapter.InnerHolder> {
    private Context context;
    List<Chaxun> chaxuns;

    public JiuzhengAdapter(Context context, List<Chaxun> chaxuns) {
        this.context = context;
        this.chaxuns = chaxuns;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_chaxun, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.jzName.setText("Name："+chaxuns.get(position).getName());
        holder.jzIdCard.setText("身份证号："+chaxuns.get(position).getCardId());
        holder.jzPhone.setText("手机号："+chaxuns.get(position).getTel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuerenActivity.name=chaxuns.get(position).getName();
                context.startActivity(new Intent(context, XiugaiActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return chaxuns.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView jzName;
        private TextView jzIdCard;
        private TextView jzPhone;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            jzName = (TextView) itemView.findViewById(R.id.jz_name);
            jzIdCard = (TextView) itemView.findViewById(R.id.jz_idCard);
            jzPhone = (TextView) itemView.findViewById(R.id.jz_phone);
        }
    }
}
