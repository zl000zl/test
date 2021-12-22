package com.example.newtext.Movie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.Activity.Movie_news;
import com.example.newtext.Movie.Activity.Recommend;
import com.example.newtext.Movie.Activity.Trailers;
import com.example.newtext.R;

import java.util.ArrayList;
import java.util.List;

public class FastAdapter extends RecyclerView.Adapter<FastAdapter.InnerHolder> {
    private Context context;
    private String[] title = {"推荐", "预告", "影评", "新闻"};
    List<Integer> images = new ArrayList<>();


    public FastAdapter(Context context) {
        this.context = context;
        images.add(R.drawable.room1);
        images.add(R.drawable.room2);
        images.add(R.drawable.room3);
        images.add(R.drawable.room4);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fast, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.fastImage.setImageResource(images.get(position));
        holder.fastTitle.setText(title[position]);
        switch (title[position]){
            case "推荐":
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, Recommend.class));
                    }
                });
                break;
            case "预告":
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, Trailers.class));
                    }
                });
                break;
            case "新闻":
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, Movie_news.class));
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView fastImage;
        private TextView fastTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            fastImage = (ImageView) itemView.findViewById(R.id.fast_image);
            fastTitle = (TextView) itemView.findViewById(R.id.fast_title);
        }
    }
}
