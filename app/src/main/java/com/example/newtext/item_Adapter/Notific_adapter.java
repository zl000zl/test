package com.example.newtext.item_Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.NewClass.Todaynews;
import com.example.newtext.R;
import com.example.newtext.StringBean.Press;
import com.example.newtext.Mytoken;

import java.util.List;

public class Notific_adapter extends RecyclerView.Adapter<Notific_adapter.LinearViewHolder> {
    private Context Ncontent;
    private List<Press> presses;

    public Notific_adapter(Context ncontent, List<Press> presses) {
        Ncontent = ncontent;
        this.presses = presses;
    }

    @NonNull
    @Override
    public Notific_adapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(Ncontent,R.layout.item_notific,null);
        return new LinearViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull Notific_adapter.LinearViewHolder holder, int position) {
        Glide.with(holder.iv_notific).load(Mytoken.URl+presses.get(position).getCover()).into(holder.iv_notific);
        holder.tv_notific.setText(Html.fromHtml(""+presses.get(position).getTitle(),1));
        holder.tv_like.setText(""+presses.get(position).getLikeNum());
        holder.tv_data.setText(""+presses.get(position).getPublishDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Todaynews.press = presses.get(position);

                Ncontent.startActivity(new Intent(Ncontent,Todaynews.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return presses.size();
    }

    public class LinearViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_notific;
        private TextView tv_notific;
        private TextView tv_like;
        private TextView tv_data;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_notific = itemView.findViewById(R.id.iv_notific);
            tv_notific = itemView.findViewById(R.id.tv_notific);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_data = itemView.findViewById(R.id.tv_data);
        }
    }
}
