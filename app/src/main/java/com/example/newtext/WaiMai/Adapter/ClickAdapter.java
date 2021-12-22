package com.example.newtext.WaiMai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Activity.Order_sure;
import com.example.newtext.WaiMai.Waimaibean.Cailist;
import com.example.newtext.Mytoken;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClickAdapter extends RecyclerView.Adapter<ClickAdapter.InnerHolder> {
    private Context context;
    public  List<Cailist> cais;

    public ClickAdapter(Context context, Set<Cailist> cais) {
        this.context = context;
        this.cais = new ArrayList<>(cais);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_click, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Cailist cailist = cais.get(position);
        holder.dianjiaName.setText(Order_sure.theme_name);
        holder.dainjiaTitle.setText(cailist.getName());
        holder.caiShuoliang.setText(cailist.getCount() + "");
        Glide.with(holder.dianjiaImage).load(Mytoken.URl + cailist.getImgUrl()).into(holder.dianjiaImage);
    }

    @Override
    public int getItemCount() {
        return cais.size();
    }


    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView dianjiaName;
        private ImageView dianjiaImage;
        private TextView dainjiaTitle;
        private TextView caiShuoliang;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            dianjiaName = (TextView) itemView.findViewById(R.id.dianjia_name);
            dianjiaImage = (ImageView) itemView.findViewById(R.id.dianjia_image);
            dainjiaTitle = (TextView) itemView.findViewById(R.id.dainjia_title);
            caiShuoliang = (TextView) itemView.findViewById(R.id.cai_shuoliang);
        }
    }
}
