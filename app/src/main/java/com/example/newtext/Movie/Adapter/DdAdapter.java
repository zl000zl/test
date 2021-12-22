package com.example.newtext.Movie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.cinemaBean.Dd_type;
import com.example.newtext.R;

import java.util.List;

public class DdAdapter extends RecyclerView.Adapter<DdAdapter.InnerHolder> {
    private List<Dd_type> dd_types;


    public List<Dd_type> getDd_types() {
        return dd_types;
    }

    public void setDd_types(List<Dd_type> dd_types) {
        this.dd_types = dd_types;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dd, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.ddOrder.setText("订单号："+dd_types.get(position).getOrderNo());
        holder.ddDate.setText("生成日期："+dd_types.get(position).getCreateTime());
        holder.ddTheatreName.setText(""+dd_types.get(position).getTheatreName());
        holder.ddRoomName.setText(""+dd_types.get(position).getRoomName());
        holder.ddMovieName.setText("影名："+dd_types.get(position).getMovieName());
        holder.ddWeizhi.setText("座位：" + dd_types.get(position).getSeatRow() + "排" + dd_types.get(position).getSeatCol() + "座");
        holder.ddTime.setText("开始："+dd_types.get(position).getStartTime()+"结束："+dd_types.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        return dd_types.size();
    }
    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView ddOrder;
        private TextView ddDate;
        private TextView ddTheatreName;
        private TextView ddRoomName;
        private TextView ddMovieName;
        private TextView ddWeizhi;
        private TextView ddTime;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ddOrder = (TextView) itemView.findViewById(R.id.dd_order);
            ddDate = (TextView) itemView.findViewById(R.id.dd_date);
            ddTheatreName = (TextView) itemView.findViewById(R.id.dd_theatreName);
            ddRoomName = (TextView) itemView.findViewById(R.id.dd_roomName);
            ddMovieName = (TextView) itemView.findViewById(R.id.dd_movieName);
            ddWeizhi = (TextView) itemView.findViewById(R.id.dd_weizhi);
            ddTime = (TextView) itemView.findViewById(R.id.dd_time);
        }
    }
}
