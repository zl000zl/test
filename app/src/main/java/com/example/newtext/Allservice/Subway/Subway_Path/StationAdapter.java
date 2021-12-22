package com.example.newtext.Allservice.Subway.Subway_Path;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.StringBean.Station;


public class StationAdapter extends RecyclerView.Adapter <StationAdapter.InnerHolder> {
    private Context context;
    private Station station;

    public StationAdapter(Context context, Station station) {
        this.context = context;
        this.station = station;
    }

    @NonNull
    @Override
    public StationAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_station,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationAdapter.InnerHolder holder, int position) {
        holder.station_address.setText(""+station.getMetroStepList().get(position).getName());
        if (station.getRunStationsName().equals/*比较两个值是否相同*/(station.getMetroStepList().get(position).getName())){
            holder.iv_subway.setVisibility(View.VISIBLE);
            //求当前的地铁停在哪个站
        }else {
            holder.iv_subway.setVisibility(View.INVISIBLE);
        }
        holder.station_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,Map_station.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return station.getMetroStepList().size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView station_address;
        private ImageView iv_subway;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            station_address = itemView.findViewById(R.id.station_address);
            iv_subway = itemView.findViewById(R.id.iv_subway);
        }
    }
}
