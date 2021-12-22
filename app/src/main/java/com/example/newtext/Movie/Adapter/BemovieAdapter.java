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
import com.example.newtext.Movie.Bean.Bemovie;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

import java.util.List;

public class BemovieAdapter extends RecyclerView.Adapter<BemovieAdapter.InnerHolder> {
    List<Bemovie> bemovies;
    private OnClickListener onClickListener;

    public List<Bemovie> getBemovies() {
        return bemovies;
    }

    public void setBemovies(List<Bemovie> bemovies) {
        this.bemovies = bemovies;
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
        Glide.with(holder.hotImages).load(Mytoken.URl + bemovies.get(position).getCover()).into(holder.hotImages);
        holder.hotName.setText("" + bemovies.get(position).getName());
        holder.hotShop.setText("想看");
    }

    @Override
    public int getItemCount() {
        return bemovies.size();
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
