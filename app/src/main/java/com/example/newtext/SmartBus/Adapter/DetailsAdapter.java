package com.example.newtext.SmartBus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.SmartBus.BusBean.Show;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.InnerHolder> {
    private Context context;
    private List<Show> shows;

    public DetailsAdapter(Context context, List<Show> shows) {
        this.context = context;
        this.shows = shows;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.busAddress.setText(""+shows.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView ivBus;
        private TextView busAddress;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            busAddress = (TextView) itemView.findViewById(R.id.bus_address);
        }
    }
}
