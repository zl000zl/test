package com.example.newtext.SmartBus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.SmartBus.BusBean.Show;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.InnerHolder> {
    private Context context;
    List<Show> shows;
    public  static int numberone;
    //定义一个公共的变量 来分辨你点击了那一条线路的站点信息 然后传入BusAdapter中定义的固定的number id
    public static int number;
    //定义公共的变量 用它来取得shows集合里的Sequence()站点的值


    public ShowAdapter(Context context, List<Show> shows) {
        this.context = context;
        this.shows = shows;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.oneLine.setText("" + shows.get(position).getName());
        holder.oneFirst.setText("起点：");
        //首先给所有的站点位置取名为“站点”来占据位置
        number = shows.get(position).getSequence();
        //上面的定义公共的变量来取得集合里的站点的顺序
        if (number==1){  //判断如果为1的情况下 改TextView为起点
            holder.oneFirst.setText("起点：");
        }else{
            if (number==numberone){  //判断如果number里面的数值等于前面BusAdapter定义的值一样的情况下
                holder.oneFirst.setText("终点：");  //赋值为“终点”
            }else{  //否则的话其他的就不显示
                holder.oneFirst.setVisibility(View.INVISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView oneLine;
        private TextView oneFirst;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            oneLine = (TextView) itemView.findViewById(R.id.one_line);
            oneFirst = (TextView) itemView.findViewById(R.id.one_first);
        }
    }
}
