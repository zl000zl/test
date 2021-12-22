package com.example.newtext.Allservice.Subway;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Allservice.Subway.Subway_Path.BjStation;
import com.example.newtext.R;
import com.example.newtext.StringBean.Subway;

import java.util.List;

public class SubwayAdapter extends RecyclerView.Adapter<SubwayAdapter.InnerHolder> {
    private Context context;
    private List<Subway> subways;
    //定义上下文和集合(利于SubwayActivity适配器的传值)

    public SubwayAdapter(Context context, List<Subway> subways) {
        this.context = context;
        this.subways = subways;
        //实例化上面的定义
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_subway, null);
        return new InnerHolder(view);
        //绑定显示界面
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.tvPath.setText(""+subways.get(position).getLineName());
        holder.tvTimeshow.setText(""+subways.get(position).getReachTime()+"分钟后");
        holder.tvNext.setText("下一站："+subways.get(position).getNextStep().getName());
        //显示所求的数据在TextView中
        holder.rl_station.setOnClickListener(new View.OnClickListener() {
            //地铁查询页的一栏点击事件
            @Override
            public void onClick(View view) {
                BjStation.id = subways.get(position).getLineId();
                //把这个页面请求到的地铁哪个站id值传到BjStation页面中
                context.startActivity(new Intent(context, BjStation.class));
                //点击跳转到具体站的所有路线
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView tvPath;
        private TextView tvTimeshow;
        private TextView tvNext;
        private RelativeLayout rl_station;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            rl_station = (RelativeLayout) itemView.findViewById(R.id.rl_station);
            tvPath = (TextView) itemView.findViewById(R.id.tv_path);
            tvTimeshow = (TextView) itemView.findViewById(R.id.tv_timeshow);
            tvNext = (TextView) itemView.findViewById(R.id.tv_next);
        }
    }
}
