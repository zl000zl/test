package com.example.newtext.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Allservice.ParkActivity;
import com.example.newtext.Allservice.Subway.SubwayActivity;
import com.example.newtext.Hospital.HospitalActivity;
import com.example.newtext.Life.LifeActivity;
import com.example.newtext.Movie.MovieActivity;
import com.example.newtext.Mytoken;
import com.example.newtext.R;
import com.example.newtext.Room.RoomActivity;
import com.example.newtext.SmartBus.Activity.BusActivity;
import com.example.newtext.StringBean.FwBean;
import com.example.newtext.Traffic.Activity.TrafficActivity;
import com.example.newtext.WaiMai.TakeoutActivity;

import java.util.List;

public class fuwuAdapter extends RecyclerView.Adapter <fuwuAdapter.LinearViewHolder > {
    private Context mContext;
    private List<FwBean> fwBeans;
    private FwBean fwBean;

    public fuwuAdapter(Context mContext, List<FwBean> fwBeans) {
        this.mContext = mContext;
        this.fwBeans = fwBeans;
    }


    @NonNull
    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.item_fuwu,null);
        return new LinearViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, int position) {
        if (position==9) {
            holder.image_show.setImageResource(R.drawable.fuwu);
            holder.tv_one.setText("更多服务");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.navigation_dashboard);
                    //从一个Fragment中跳转到另一个Fragment调用此方法
                }
            });
        }else {
            fwBean = fwBeans.get(position);
            Glide.with(holder.image_show).load(Mytoken.URl +fwBeans.get(position)
                    .getImgUrl()).into(holder.image_show);
            holder.tv_one.setText(Html.fromHtml(""+fwBeans.get(position).getServiceName(),1));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击选择性事件用switch case"名字" 的方法来实现点击事件
                    switch (fwBeans.get(position).getServiceName()){
                        case "停哪儿":
                            mContext.startActivity(new Intent(mContext, ParkActivity.class));
                            break;
                        case "城市地铁":
                            mContext.startActivity(new Intent(mContext, SubwayActivity.class));
                            break;
                        case "智慧交管":
                            mContext.startActivity(new Intent(mContext, TrafficActivity.class));
                            break;
                        case "外卖订餐":
                            mContext.startActivity(new Intent(mContext, TakeoutActivity.class));
                            break;
                        case "找房子":
                            mContext.startActivity(new Intent(mContext, RoomActivity.class));
                            break;
                        case "门诊预约":
                            mContext.startActivity(new Intent(mContext, HospitalActivity.class));
                            break;
                        case "智慧巴士":
                            mContext.startActivity(new Intent(mContext, BusActivity.class));
                            break;
                        case "生活缴费":
                            mContext.startActivity(new Intent(mContext, LifeActivity.class));
                            break;
                        case "看电影":
                            mContext.startActivity(new Intent(mContext, MovieActivity.class));
                            break;
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public class LinearViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_show;
        private TextView tv_one;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            image_show =itemView.findViewById(R.id.image_show);
            tv_one = itemView.findViewById(R.id.tv_one);
        }
    }
}
