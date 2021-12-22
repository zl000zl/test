package com.example.newtext.Hospital.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Hospital.Activity.JiuzhengActivity;
import com.example.newtext.HospitalBean.One;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

import java.util.List;

public class YuyueAdapter extends RecyclerView.Adapter<YuyueAdapter.InnerHolder> {
    private Context context;
    List<One> ones;


    public YuyueAdapter(Context context, List<One> ones) {
        this.context = context;
        this.ones = ones;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_yuyue, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.yuyueImage).load(Mytoken.URl+ones.get(position).getImgUrl()).into(holder.yuyueImage);
        holder.yuyueTitle.setText(""+ones.get(position).getHospitalName());
        holder.ratingBar2.setRating(ones.get(position).getLevel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, JiuzhengActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ones.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView yuyueImage;
        private TextView yuyueTitle;
        private RatingBar ratingBar2;
        private ImageView yuyueGo;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            yuyueImage = (ImageView) itemView.findViewById(R.id.yuyue_image);
            yuyueTitle = (TextView) itemView.findViewById(R.id.yuyue_title);
            ratingBar2 = (RatingBar) itemView.findViewById(R.id.ratingBar2);
            yuyueGo = (ImageView) itemView.findViewById(R.id.yuyue_go);
        }
    }
}
