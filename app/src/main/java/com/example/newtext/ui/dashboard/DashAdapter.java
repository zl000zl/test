package com.example.newtext.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Mytoken;
import com.example.newtext.R;
import com.example.newtext.StringBean.Service;

import java.util.List;

public class DashAdapter extends RecyclerView.Adapter<DashAdapter.InnerHolder> {
    private Context context;
    private List<Service> services;


    public DashAdapter(Context context, List<Service> services) {
        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dash_show,parent,false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.showImage).load(Mytoken.URl+services.get(position).getImgUrl()).into(holder.showImage);
        holder.showContent.setText(""+services.get(position).getServiceName());

    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView showImage;
        private TextView showContent;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            showImage = (ImageView) itemView.findViewById(R.id.show_image);
            showContent = (TextView) itemView.findViewById(R.id.show_content);
        }
    }
}
