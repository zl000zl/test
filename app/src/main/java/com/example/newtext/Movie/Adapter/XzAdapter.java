package com.example.newtext.Movie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.Activity.Ticker_order;
import com.example.newtext.R;

import java.util.ArrayList;
import java.util.List;

public class XzAdapter extends RecyclerView.Adapter<XzAdapter.InnerHolder> {
    private Context context;
    private List<Integer> pai = new ArrayList<>();
    private List<Integer> zuo = new ArrayList<>();
    //适配器里传入的数据 排和座位


    public XzAdapter(Context context, List<Integer> pai, List<Integer> zuo) {
        this.context = context;
        this.pai = pai;
        this.zuo = zuo;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xz, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.xzSuccess.setText(""+pai.get(position)+"排"+zuo.get(position)+"座");
        Ticker_order.paiId = pai.get(position);
        Ticker_order.zuoId = pai.get(position);
    }

    @Override
    public int getItemCount() {
        return pai.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView xzSuccess;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            xzSuccess = (TextView) itemView.findViewById(R.id.xz_success);
        }
    }
}
