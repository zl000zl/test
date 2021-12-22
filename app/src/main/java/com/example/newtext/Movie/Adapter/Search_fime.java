package com.example.newtext.Movie.Adapter;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Movie.Bean.HotMovie;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

import java.util.List;

public class Search_fime extends RecyclerView.Adapter<Search_fime.InnerHolder> {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new InnerHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.reImage).load(Mytoken.URl+hotMovies.get(position).getCover()).into(holder.reImage);
        holder.seName.setText("片名："+hotMovies.get(position).getName());
        holder.seTitle.setText(""+hotMovies.get(position).getLanguage());
        holder.reRab.setRating((int)+hotMovies.get(position).getScore());
        holder.reTime.setText("时长："+hotMovies.get(position).getDuration());
        holder.reLike.setText("喜欢："+hotMovies.get(position).getFavoriteNum()+"人");
        holder.reJieshao.setText(Html.fromHtml("简介："+hotMovies.get(position).getIntroduction(),1));


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
        private ImageView reImage;
        private TextView seName;
        private TextView seTitle;
        private RatingBar reRab;
        private TextView reTime;
        private TextView reLike;
        private TextView reJieshao;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            reImage = (ImageView) itemView.findViewById(R.id.re_image);
            seName = (TextView) itemView.findViewById(R.id.se_name);
            seTitle = (TextView) itemView.findViewById(R.id.se_title);
            reRab = (RatingBar) itemView.findViewById(R.id.re_rab);
            reTime = (TextView) itemView.findViewById(R.id.re_time);
            reLike = (TextView) itemView.findViewById(R.id.re_like);
            reJieshao = (TextView) itemView.findViewById(R.id.re_jieshao);
        }
    }
}
