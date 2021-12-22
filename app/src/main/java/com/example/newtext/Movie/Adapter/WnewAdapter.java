package com.example.newtext.Movie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Movie.cinemaBean.Wnews;
import com.example.newtext.Mytoken;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;

import java.util.List;

public class WnewAdapter extends RecyclerView.Adapter<WnewAdapter.InnerHolder> {
    private List<Wnews> wnews;
    private OnClickListener onClickListener;

    public List<Wnews> getWnews() {
        return wnews;
    }

    public void setWnews(List<Wnews> wnews) {
        this.wnews = wnews;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mnews, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.mnewImage).load(Mytoken.URl + wnews.get(position).getCover()).into(holder.mnewImage);
        holder.mnewName.setText("" + wnews.get(position).getTitle());
        holder.mnewDate.setText("发表日期："+wnews.get(position).getPublishDate());
        holder.zanSum.setText("点赞数：" + wnews.get(position).getLikeNum());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wnews.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView mnewImage;
        private TextView mnewName;
        private TextView mnewDate;
        private TextView zanSum;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mnewImage = (ImageView) itemView.findViewById(R.id.mnew_image);
            mnewName = (TextView) itemView.findViewById(R.id.mnew_name);
            mnewDate = (TextView) itemView.findViewById(R.id.mnew_date);
            zanSum = (TextView) itemView.findViewById(R.id.zan_sum);
        }
    }
}
