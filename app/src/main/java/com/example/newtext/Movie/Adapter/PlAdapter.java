package com.example.newtext.Movie.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.Bean.Pl_film;
import com.example.newtext.R;

import java.util.List;

public class PlAdapter extends RecyclerView.Adapter<PlAdapter.InnerHolder> {
    private Context context;
    private List<Pl_film> pl_films;


    public PlAdapter(Context context, List<Pl_film> pl_films) {
        this.context = context;
        this.pl_films = pl_films;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plfilm, parent, false);
        return new InnerHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.plName.setText(""+pl_films.get(position).getNickName());
        holder.plRab.setRating((int)+pl_films.get(position).getScore());
        holder.plLike.setText("喜欢："+pl_films.get(position).getLikeNum());
        holder.plContent.setText(Html.fromHtml(""+pl_films.get(position).getContent(),1));
        holder.plDate.setText("发表时间："+pl_films.get(position).getCommentDate());
    }

    @Override
    public int getItemCount() {
        return pl_films.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView plName;
        private RatingBar plRab;
        private TextView plLike;
        private TextView plContent;
        private TextView plDate;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            plName = (TextView) itemView.findViewById(R.id.pl_name);
            plRab = (RatingBar) itemView.findViewById(R.id.pl_rab);
            plLike = (TextView) itemView.findViewById(R.id.pl_like);
            plContent = (TextView) itemView.findViewById(R.id.pl_content);
            plDate = (TextView) itemView.findViewById(R.id.pl_date);
        }
    }
}
