package com.example.newtext.Life.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Life.Activity.DianfeiActivity;
import com.example.newtext.Life.LifeBean.Jiaofei;
import com.example.newtext.R;
import com.example.newtext.SmartBus.Activity.PayFessActivity;
import com.example.newtext.Mytoken;

import java.util.List;

public class LifeAdapter extends RecyclerView.Adapter<LifeAdapter.InnerHolder> {
    private Context context;
    List<Jiaofei> jiaofeis;


    public LifeAdapter(Context context, List<Jiaofei> jiaofeis) {
        this.context = context;
        this.jiaofeis = jiaofeis;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jiaofei, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.jiaofeiImage).load(Mytoken.URl+jiaofeis.get(position).getImgUrl()).into(holder.jiaofeiImage);
        holder.jiaofeiTitle.setText(""+jiaofeis.get(position).getLiveName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (jiaofeis.get(position).getLiveName()){
                    case "水费":
                        context.startActivity(new Intent(context, PayFessActivity.class));
                        break;
                    case "电费":
                        context.startActivity(new Intent(context, DianfeiActivity.class));
                        break;
                    default:
                        Toast.makeText(context, "您点击了"+jiaofeis.get(position).getLiveName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jiaofeis.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView jiaofeiImage;
        private TextView jiaofeiTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            jiaofeiImage = (ImageView) itemView.findViewById(R.id.jiaofei_image);
            jiaofeiTitle = (TextView) itemView.findViewById(R.id.jiaofei_title);
        }
    }
}
