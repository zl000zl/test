package com.example.newtext.WaiMai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Module;
import com.example.newtext.Mytoken;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.InnerHolder> {
    private List<Module> modules;
    private OnClickListener onClickListener;

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_module, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.moduleImage).load(Mytoken.URl+modules.get(position).getImgUrl()).into(holder.moduleImage);
        holder.moduleTitle.setText(""+modules.get(position).getThemeName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener!=null) {
                    onClickListener.onClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView moduleImage;
        private TextView moduleTitle;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            moduleImage = (ImageView) itemView.findViewById(R.id.module_image);
            moduleTitle = (TextView) itemView.findViewById(R.id.module_title);
        }
    }
}
