package com.example.newtext.ui.home.SearchView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.NewClass.Todaynews;
import com.example.newtext.R;
import com.example.newtext.StringBean.Press;
import com.example.newtext.Mytoken;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.InnerHolder> {
    private Context context;
    private List<Press> presses;


    public SearchAdapter(Context context, List<Press> presses) {
        this.context = context;
        this.presses = presses;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_searchview, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.imageStu).load(Mytoken.URl+presses.get(position).getCover()).into(holder.imageStu);
        holder.tvShow.setText("\n\n"+presses.get(position).getTitle());
        holder.tvLike.setText(""+presses.get(position).getReadNum());
        holder.tvData.setText(""+presses.get(position).getPublishDate());

        holder.rl_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Todaynews.press = presses.get(position);
                context.startActivity(new Intent(context, Todaynews.class));
                //重复操作引用HomeFragment中的跳转
            }
        });
    }

    @Override
    public int getItemCount() {
        return presses.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView imageStu;
        private TextView tvShow;
        private TextView tvLike;
        private TextView tvData;
        private RelativeLayout rl_search;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            imageStu = (ImageView) itemView.findViewById(R.id.image_stu);
            tvShow = (TextView) itemView.findViewById(R.id.tv_show);
            tvLike = (TextView) itemView.findViewById(R.id.tv_like);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            rl_search = itemView.findViewById(R.id.rl_search);
        }
    }
}
