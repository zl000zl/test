package com.example.newtext.Movie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Movie.Bean.HotMovie;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

import java.util.List;

public class HotmovieAdapter extends RecyclerView.Adapter<HotmovieAdapter.InnerHolder> {
    List<HotMovie> hotMovies;
    private OnClickListener onClickListener;


    public List<HotMovie> getHotMovies() {
        return hotMovies;
    }

    public void setHotMovies(List<HotMovie> hotMovies) {
        this.hotMovies = hotMovies;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotmovie, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.hotImages).load(Mytoken.URl+hotMovies.get(position).getCover()).into(holder.hotImages);
        holder.hotName.setText(""+hotMovies.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener!=null){
                    onClickListener.onClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotMovies.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView hotImages;
        private TextView hotName;
        private Button hotShop;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            hotImages = (ImageView) itemView.findViewById(R.id.hot_images);
            hotName = (TextView) itemView.findViewById(R.id.hot_name);
            hotShop = (Button) itemView.findViewById(R.id.hot_shop);
        }
    }
}
