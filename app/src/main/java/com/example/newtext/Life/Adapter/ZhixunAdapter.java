package com.example.newtext.Life.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Life.LifeBean.Zhixun;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

import java.util.List;

public class ZhixunAdapter extends RecyclerView.Adapter<ZhixunAdapter.InnerHolder> {
    private Context context;
    List<Zhixun> zhixuns;


    public ZhixunAdapter(Context context, List<Zhixun> zhixuns) {
        this.context = context;
        this.zhixuns = zhixuns;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhixun, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.zhixunImage).load(Mytoken.URl+zhixuns.get(position).getCover()).into(holder.zhixunImage);
        holder.zhixunTitle.setText(""+zhixuns.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView zhixunImage;
        private TextView zhixunTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            zhixunImage = (ImageView) itemView.findViewById(R.id.zhixun_image);
            zhixunTitle = (TextView) itemView.findViewById(R.id.zhixun_title);
        }
    }
}
