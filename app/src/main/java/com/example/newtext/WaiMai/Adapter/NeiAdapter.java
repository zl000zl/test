package com.example.newtext.WaiMai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Non_payment;
import com.example.newtext.Mytoken;

import java.util.List;

public class NeiAdapter extends RecyclerView.Adapter<NeiAdapter.InnerHolder> {
    private Context scontext;
    private List<Non_payment.OrderInfoBean.OrderItemListBean> orderItemListBeans;
    //接RecycleView里面的小bean值


    public NeiAdapter(Context scontext, List<Non_payment.OrderInfoBean.OrderItemListBean> orderItemListBeans) {
        this.scontext = scontext;
        this.orderItemListBeans = orderItemListBeans;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nei, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.shopLogo).load(Mytoken.URl + orderItemListBeans.get(position).getProductImage())
                .into(holder.shopLogo);
        holder.neiTime.setText("" + orderItemListBeans.get(position).getCreateTime());
        holder.neiPrice.setText("总价：￥" + orderItemListBeans.get(position).getTotalPrice());
        holder.neiSum.setText(""+orderItemListBeans.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return orderItemListBeans.size();
    }


    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView shopLogo;
        private TextView neiTime;
        private TextView neiPrice;
        private TextView neiFnagshi;
        private TextView neiSum;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            shopLogo = (ImageView) itemView.findViewById(R.id.shop_logo);
            neiTime = (TextView) itemView.findViewById(R.id.nei_time);
            neiPrice = (TextView) itemView.findViewById(R.id.nei_price);
            neiFnagshi = (TextView) itemView.findViewById(R.id.nei_fnagshi);
            neiSum = (TextView) itemView.findViewById(R.id.nei_sum);
        }
    }
}
