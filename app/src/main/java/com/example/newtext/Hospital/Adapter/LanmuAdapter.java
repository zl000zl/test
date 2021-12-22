package com.example.newtext.Hospital.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Hospital.Activity.JiuzhengActivity;
import com.example.newtext.Hospital.Activity.YuyueActivity;
import com.example.newtext.R;

import java.util.ArrayList;
import java.util.List;

public class LanmuAdapter extends RecyclerView.Adapter<LanmuAdapter.InnerHolder> {
    private Context context;
    List<Integer> images = new ArrayList<>();
    private String[] title = {"医院","科室","疾病","专家"};


    public LanmuAdapter(Context context) {
        this.context = context;
        images.add(R.drawable.yiyuan);
        images.add(R.drawable.keshi);
        images.add(R.drawable.jibing);
        images.add(R.drawable.zhuanjia);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_lanmu, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.lmImage.setImageResource(images.get(position));
        holder.lmTitle.setText(title[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (title[position]){
                    case "医院":
                        context.startActivity(new Intent(context, YuyueActivity.class));
                        break;
                    case "科室":
                        context.startActivity(new Intent(context, JiuzhengActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView lmImage;
        private TextView lmTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            lmImage = (ImageView) itemView.findViewById(R.id.lm_image);
            lmTitle = (TextView) itemView.findViewById(R.id.lm_title);
        }
    }
}
