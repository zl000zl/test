package com.example.newtext.Movie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Movie.Bean.Bemovie;
import com.example.newtext.Mytoken;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;

import java.util.List;

public class YgAdapter extends RecyclerView.Adapter<YgAdapter.InnerHolder> {
    private List<Bemovie> bemovies;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yglist, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.ygImage).load(Mytoken.URl+bemovies.get(position).getCover()).into(holder.ygImage);
        holder.ygName.setText(""+bemovies.get(position).getName());
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
        return bemovies.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView ygName;
        private ImageView ygImage;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ygName = (TextView) itemView.findViewById(R.id.yg_name);
            ygImage = (ImageView) itemView.findViewById(R.id.yg_image);
        }
    }
}
