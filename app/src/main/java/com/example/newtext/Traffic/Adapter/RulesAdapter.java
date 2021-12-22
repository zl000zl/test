package com.example.newtext.Traffic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.Traffic.Activity.ParticularsActivity;
import com.example.newtext.Traffic.Jiaotongbean.Rules;

import java.util.List;

public class RulesAdapter extends RecyclerView.Adapter<RulesAdapter.InnerHolder> {
    private Context context;
    private List<Rules> rules;

    public RulesAdapter(Context context, List<Rules> rules) {
        this.context = context;
        this.rules = rules;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rules, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.carId.setText(""+rules.get(position).getLicencePlate());
        holder.carType.setText("" + rules.get(position).getDisposeState());
        holder.carDate.setText("违章时间："+rules.get(position).getBadTime());
        holder.carMoney.setText("处罚金额："+rules.get(position).getMoney()+"￥");
        holder.carFen.setText("违纪计分："+rules.get(position).getDeductMarks()+"分");
        holder.carAddress.setText("处罚地点："+rules.get(position).getIllegalSites());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //违章详情页里的公共变量 从适配器中传入值到违章详情页面
                ParticularsActivity.date = rules.get(position).getBadTime();
                ParticularsActivity.address = rules.get(position).getIllegalSites();
                ParticularsActivity.xingwei = rules.get(position).getTrafficOffence();
                ParticularsActivity.shuhao = rules.get(position).getNoticeNo();
                ParticularsActivity.koufen = rules.get(position).getDeductMarks();
                ParticularsActivity.jinen = rules.get(position).getMoney();

                context.startActivity(new Intent(context, ParticularsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return rules.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView carId;
        private TextView carType;
        private TextView carDate;
        private TextView carMoney;
        private TextView carFen;
        private TextView carAddress;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            carId = (TextView) itemView.findViewById(R.id.car_Id);
            carType = (TextView) itemView.findViewById(R.id.car_type);
            carDate = (TextView) itemView.findViewById(R.id.car_date);
            carMoney = (TextView) itemView.findViewById(R.id.car_money);
            carFen = (TextView) itemView.findViewById(R.id.car_fen);
            carAddress = (TextView) itemView.findViewById(R.id.car_address);
        }
    }
}
