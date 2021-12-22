package com.example.newtext.WaiMai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Theme;
import com.example.newtext.Mytoken;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.InnerHolder> {
    private List<Theme> themes = new ArrayList<>();
    private OnClickListener onClickListener;

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        switch (position){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                Glide.with(holder.themeImage).load(Mytoken.URl + themes.get(position).getImgUrl()).into(holder.themeImage);
                holder.themeName.setText("" + themes.get(position).getName());
                holder.themeRab.setRating((float) +themes.get(position).getScore());
                holder.themeXiaoliang.setText("销量：" + themes.get(position).getSaleQuantity() + " 份");
                holder.themeTime.setText("到店时间：" + themes.get(position).getDeliveryTime() + " 分钟");
                holder.themeJuli.setText("距离：" + themes.get(position).getDistance() + " km");
                holder.themePrise.setText("每人/" + themes.get(position).getAvgCost() + " 元");
                break;
        }
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
        return themes.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView themeImage;
        private TextView themeName;
        private RatingBar themeRab;
        private TextView themeXiaoliang;
        private TextView themeTime;
        private TextView themeJuli;
        private TextView themePrise;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            themeImage = (ImageView) itemView.findViewById(R.id.theme_image);
            themeName = (TextView) itemView.findViewById(R.id.theme_name);
            themeRab = (RatingBar) itemView.findViewById(R.id.theme_rab);
            themeXiaoliang = (TextView) itemView.findViewById(R.id.theme_xiaoliang);
            themeTime = (TextView) itemView.findViewById(R.id.theme_time);
            themeJuli = (TextView) itemView.findViewById(R.id.theme_juli);
            themePrise = (TextView) itemView.findViewById(R.id.theme_prise);
        }
    }
}
