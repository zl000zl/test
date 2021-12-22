package com.example.newtext.item_Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.StringBean.PackTwo;

import java.util.List;

public class PtAdapter extends RecyclerView.Adapter<PtAdapter.InnerHolder> {
    private Context context;
    private List<PackTwo> packTwos;

    public PtAdapter(Context context, List<PackTwo> packTwos) {
        this.context = context;
        this.packTwos = packTwos;
    }

    @NonNull
    @Override
    public PtAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.activity_item__pkrecord,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PtAdapter.InnerHolder holder, int position) {
        holder.tv_pt1.setText("" + packTwos.get(position).getPlateNumber());
        holder.tv_pt2.setText("收费金额:" + packTwos.get(position).getMonetary());
        holder.tv_pt3.setText("" + packTwos.get(position).getEntryTime());
        holder.tv_pt4.setText("" + packTwos.get(position).getOutTime());
        holder.tv_pt5.setText(""+packTwos.get(position).getParkName());
    }

    @Override
    public int getItemCount() {
        return packTwos.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView tv_pt1;
        private TextView tv_pt2;
        private TextView tv_pt3;
        private TextView tv_pt4;
        private TextView tv_pt5;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tv_pt1 = itemView.findViewById(R.id.tv_pt1);
            tv_pt2 = itemView.findViewById(R.id.tv_pt2);
            tv_pt3 = itemView.findViewById(R.id.tv_pt3);
            tv_pt4 = itemView.findViewById(R.id.tv_pt4);
            tv_pt5 = itemView.findViewById(R.id.tv_pt5);
        }
    }
}
