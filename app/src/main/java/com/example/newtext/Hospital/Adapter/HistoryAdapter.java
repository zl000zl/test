package com.example.newtext.Hospital.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.HospitalBean.History;
import com.example.newtext.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.InnerHoler> {
    private Context context;
    List<History> histories;


    public HistoryAdapter(Context context, List<History> histories) {
        this.context = context;
        this.histories = histories;
    }

    @NonNull
    @Override
    public InnerHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new InnerHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHoler holder, int position) {
        holder.lsDingdan.setText(""+histories.get(position).getOrderNo());
        holder.lsMoney.setText(""+histories.get(position).getMoney()+"元");
        holder.lsName.setText(""+histories.get(position).getPatientName());
        holder.lsRoom.setText(""+histories.get(position).getCategoryName());

        if (histories.get(position).getType()!=null) {
            if (histories.get(position).getType().equals("1")) {
                holder.lsClass.setText("专家号");
            } else {
                holder.lsClass.setText("普通号");
            }
        }

        holder.lsTime.setText(""+histories.get(position).getReserveTime());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class InnerHoler extends RecyclerView.ViewHolder {
        private TextView lsDingdan;
        private TextView lsMoney;
        private TextView lsName;
        private TextView lsRoom;
        private TextView lsClass;
        private TextView lsTime;
        public InnerHoler(@NonNull View itemView) {
            super(itemView);
            lsDingdan = (TextView) itemView.findViewById(R.id.ls_dingdan);
            lsMoney = (TextView) itemView.findViewById(R.id.ls_money);
            lsName = (TextView) itemView.findViewById(R.id.ls_name);
            lsRoom = (TextView) itemView.findViewById(R.id.ls_room);
            lsClass = (TextView) itemView.findViewById(R.id.ls_class);
            lsTime = (TextView) itemView.findViewById(R.id.ls_time);
        }
    }
}
