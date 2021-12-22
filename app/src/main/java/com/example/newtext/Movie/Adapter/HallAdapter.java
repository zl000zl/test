package com.example.newtext.Movie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.Bean.Session;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;

import java.util.List;

public class HallAdapter extends RecyclerView.Adapter<HallAdapter.InnerHolder> {
    private List<Session> sessions;
    private OnClickListener onClickListener;


    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hall, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.startTime.setText(""+sessions.get(position).getStartTime());
        holder.endTime.setText(""+sessions.get(position).getEndTime()+"散场");
        switch (sessions.get(position).getPlayType()){
            case "1":
                holder.hallYuyan.setText("国语2D");
                break;
            case "2":
                holder.hallYuyan.setText("国语3D");
                break;
            case "3":
                holder.hallYuyan.setText("国语IMAX");
                break;
            case "4":
                holder.hallYuyan.setText("国语4DX");
                break;
        }
        holder.hallType.setText(""+sessions.get(position).getRoomName());
        holder.hallPrice.setText("￥"+sessions.get(position).getPrice());
        holder.hallShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener!=null){
                    onClickListener.onClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView startTime;
        private TextView endTime;
        private TextView hallYuyan;
        private TextView hallType;
        private TextView hallPrice;
        private Button hallShop;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            startTime = (TextView) itemView.findViewById(R.id.start_time);
            endTime = (TextView) itemView.findViewById(R.id.end_time);
            hallYuyan = (TextView) itemView.findViewById(R.id.hall_yuyan);
            hallType = (TextView) itemView.findViewById(R.id.hall_type);
            hallPrice = (TextView) itemView.findViewById(R.id.hall_price);
            hallShop = (Button) itemView.findViewById(R.id.hall_shop);
        }
    }
}
