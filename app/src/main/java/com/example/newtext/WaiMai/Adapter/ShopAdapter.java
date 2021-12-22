package com.example.newtext.WaiMai.Adapter;

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
import com.example.newtext.WaiMai.Waimaibean.Goodshop;
import com.example.newtext.Mytoken;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.InnerHolder> {
    private List<Goodshop> goodshops;
    private OnClickListener onClickListener;

    public List<Goodshop> getGoodshops() {
        return goodshops;
    }

    public void setGoodshops(List<Goodshop> goodshops) {
        this.goodshops = goodshops;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goodshop, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.nearbyImage).load(Mytoken.URl+goodshops.get(position).getImgUrl()).into(holder.nearbyImage);
        holder.nearbyTitle.setText("店家名:"+goodshops.get(position).getName());
        holder.nearbyGrade.setRating((float)+goodshops.get(position).getScore());
        holder.nearbyNumber.setText("销量:"+goodshops.get(position).getSaleQuantity());
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
        return goodshops.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView nearbyImage;
        private TextView nearbyTitle;
        private RatingBar nearbyGrade;
        private TextView nearbyNumber;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            nearbyImage = (ImageView) itemView.findViewById(R.id.nearby_image);
            nearbyTitle = (TextView) itemView.findViewById(R.id.nearby_title);
            nearbyGrade = (RatingBar) itemView.findViewById(R.id.nearby_grade);
            nearbyNumber = (TextView) itemView.findViewById(R.id.nearby_number);
        }
    }
}
