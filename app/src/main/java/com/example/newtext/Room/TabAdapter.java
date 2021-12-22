package com.example.newtext.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.example.newtext.StringBean.Room;
import com.example.newtext.Mytoken;

import java.util.List;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.InnerHodler> {
    private Context context;
    List<Room> rooms;


    public TabAdapter(Context context, List<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public InnerHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_tab, null);
        return new InnerHodler(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull InnerHodler holder, int position) {
        Glide.with(holder.tabImage).load(Mytoken.URl+rooms.get(position).getPic()).into(holder.tabImage);
        holder.tabTitle.setText("位置："+rooms.get(position).getSourceName());
        holder.tabPrice.setText("房价："+rooms.get(position).getPrice());
        holder.tabAcreage.setText("面积:"+rooms.get(position).getAreaSize()+"/㎡");
        holder.tabContent.setText("简介："+rooms.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入房子的详情页
                XiangqingActivity.room=rooms.get(position);
                //把这个适配器里的所有数据通过 从详情页定义的公共的bean 传递过去带上下标点击每一条内容显示对应的详情
                context.startActivity(new Intent(context,XiangqingActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class InnerHodler extends RecyclerView.ViewHolder {
        private ImageView tabImage;
        private TextView tabTitle;
        private TextView tabPrice;
        private TextView tabAcreage;
        private TextView tabContent;
        public InnerHodler(@NonNull View itemView) {
            super(itemView);
            tabImage = (ImageView) itemView.findViewById(R.id.tab_image);
            tabTitle = (TextView) itemView.findViewById(R.id.tab_title);
            tabPrice = (TextView) itemView.findViewById(R.id.tab_price);
            tabAcreage = (TextView) itemView.findViewById(R.id.tab_acreage);
            tabContent = (TextView) itemView.findViewById(R.id.tab_content);
        }
    }
}
