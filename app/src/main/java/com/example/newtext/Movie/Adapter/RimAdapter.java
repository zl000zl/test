package com.example.newtext.Movie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Movie.Bean.Rim_cinema;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

import java.util.List;

public class RimAdapter extends RecyclerView.Adapter<RimAdapter.InnerHolder> {
    List<Rim_cinema> rim_cinemas;
    private OnClickListener onClickListener;


    public List<Rim_cinema> getRim_cinemas() {
        return rim_cinemas;
    }

    public void setRim_cinemas(List<Rim_cinema> rim_cinemas) {
        this.rim_cinemas = rim_cinemas;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rim, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.rimImage).load(Mytoken.URl+rim_cinemas.get(position).getCover()).into(holder.rimImage);
        holder.rimName.setText("影院名："+rim_cinemas.get(position).getName());
        holder.rimAddress.setText("地址："+rim_cinemas.get(position).getAddress());
        holder.rimRab.setRating((int)+rim_cinemas.get(position).getScore());
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
        return rim_cinemas.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView rimImage;
        private TextView rimName;
        private TextView rimAddress;
        private RatingBar rimRab;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            rimImage = (ImageView) itemView.findViewById(R.id.rim_image);
            rimName = (TextView) itemView.findViewById(R.id.rim_name);
            rimAddress = (TextView) itemView.findViewById(R.id.rim_address);
            rimRab = (RatingBar) itemView.findViewById(R.id.rim_rab);
        }
    }
}
