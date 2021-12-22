package com.example.newtext.WaiMai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Comment_my;
import com.example.newtext.Mytoken;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.InnerHolder> {
    private Context context;
    private List<Comment_my> comment_my;

    public MyAdapter(Context context, List<Comment_my> comment_my) {
        this.context = context;
        this.comment_my = comment_my;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.meImage).load(Mytoken.URl+comment_my.get(position).getImgUrl()).into(holder.meImage);
        holder.meName.setText("店名："+comment_my.get(position).getSellerName());
        holder.meBar.setRating((int)+comment_my.get(position).getScore());
        holder.meXiaoliang.setText("销量："+comment_my.get(position).getSaleQuantity());
        holder.meAddress.setText("地址："+comment_my.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return comment_my.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView meImage;
        private TextView meName;
        private RatingBar meBar;
        private TextView meXiaoliang;
        private TextView meAddress;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            meImage = (ImageView) itemView.findViewById(R.id.me_image);
            meName = (TextView) itemView.findViewById(R.id.me_name);
            meBar = (RatingBar) itemView.findViewById(R.id.me_bar);
            meXiaoliang = (TextView) itemView.findViewById(R.id.me_xiaoliang);
            meAddress = (TextView) itemView.findViewById(R.id.me_address);
        }
    }
}
