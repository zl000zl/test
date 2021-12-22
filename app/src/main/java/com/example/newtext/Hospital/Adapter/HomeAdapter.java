package com.example.newtext.Hospital.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.HospitalBean.One;
import com.example.newtext.R;
import com.example.newtext.Mytoken;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.InnerHolder> {
    private Context context;
    List<One> ones;

    public HomeAdapter(Context context, List<One> ones) {
        this.context = context;
        this.ones = ones;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_home, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.homeImage).load(Mytoken.URl+ones.get(position).getImgUrl()).into(holder.homeImage);
        holder.homeTitle.setText(""+ones.get(position).getHospitalName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "你点击了"+ones.get(position).getHospitalName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView homeImage;
        private TextView homeTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            homeImage = (ImageView) itemView.findViewById(R.id.home_image);
            homeTitle = (TextView) itemView.findViewById(R.id.home_title);
        }
    }
}
