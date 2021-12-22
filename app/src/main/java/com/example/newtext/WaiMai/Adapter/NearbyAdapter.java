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
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Nearby;
import com.example.newtext.Mytoken;

import java.util.List;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.InnerHolder> {
    private Context context;
    private List<Nearby> nearbies;
    private OnClickListener onClickListener;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Nearby> getNearbies() {
        return nearbies;
    }

    public void setNearbies(List<Nearby> nearbies) {
        this.nearbies = nearbies;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.shopImage).load(Mytoken.URl + nearbies.get(position).getImgUrl()).into(holder.shopImage);
        holder.shopName.setText("" + nearbies.get(position).getName());
        holder.ratingBar3.setRating((float) +nearbies.get(position).getScore());
        holder.shopXiaoliang.setText("销量：" + nearbies.get(position).getSaleQuantity() + " 份");
        holder.shopTime.setText("到店时间：" + nearbies.get(position).getDeliveryTime() + " 分钟");
        holder.shopJuli.setText("距离：" + nearbies.get(position).getDistance() + " km");
        holder.shopPrise.setText("每人/" + nearbies.get(position).getAvgCost() + " 元");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener !=null){
                    onClickListener.onClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nearbies.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView shopImage;
        private TextView shopName;
        private RatingBar ratingBar3;
        private TextView shopXiaoliang;
        private TextView shopTime;
        private TextView shopJuli;
        private TextView shopPrise;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            shopImage = (ImageView) itemView.findViewById(R.id.shop_image);
            shopName = (TextView) itemView.findViewById(R.id.shop_name);
            ratingBar3 = (RatingBar) itemView.findViewById(R.id.ratingBar3);
            shopXiaoliang = (TextView) itemView.findViewById(R.id.shop_xiaoliang);
            shopTime = (TextView) itemView.findViewById(R.id.shop_time);
            shopJuli = (TextView) itemView.findViewById(R.id.shop_juli);
            shopPrise = (TextView) itemView.findViewById(R.id.shop_prise);
        }
    }
}
