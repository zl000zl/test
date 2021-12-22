package com.example.newtext.SmartBus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.SmartBus.Activity.DetailsActivity;
import com.example.newtext.SmartBus.Activity.MessageActivity;
import com.example.newtext.SmartBus.BusBean.Buslist;
import com.example.newtext.SmartBus.BusBean.Show;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.InnerHolder> {
    private Context context;
    private List<Buslist> buslists;

    public static int line1;
    //定义公共的变量  给下面通过前面请求线路列表的id来取到每条线路上的站点信息
    int a = 1;
    //定义a=1 是给下面判断点击小按钮的时候展开时候的判断

    public static String station;

    public BusAdapter(Context context, List<Buslist> buslists) {
        this.context = context;
        this.buslists = buslists;

    }


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus, parent, false);
        return new InnerHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        holder.busXianlu.setText("" + buslists.get(position).getName());
        holder.busStart.setText("" + buslists.get(position).getFirst());
        holder.busEnd.setText("" + buslists.get(position).getEnd());
        holder.busMoney.setText("￥" + buslists.get(position).getPrice());
        holder.busTime1.setText("" + buslists.get(position).getStartTime());
        holder.busKm.setText("" + buslists.get(position).getMileage() + "KM");
        holder.busTime2.setText("" + buslists.get(position).getEndTime());


        holder.xiabiao.setOnClickListener(new View.OnClickListener() {
            //小按钮的展开点击事件
            @Override
            public void onClick(View view) {
                switch (position) {
                    //这个switch方法是用来点击四条线路下方小按钮显示站点的信息
                    case 0:
                    case 1:
                        ShowAdapter.numberone = 4;
                        //这里是想ShowAdapter的公共方法numberone传入值
                        break;
                    case 2:
                        ShowAdapter.numberone = 11;
                        //这里是想ShowAdapter的公共方法numberone传入值
                        break;
                    case 3:
                        ShowAdapter.numberone = 8;
                        //这里是想ShowAdapter的公共方法numberone传入值
                        break;
                }
                if (a == 1) {  //如果a=1的时候
                    holder.oneLlt.setVisibility(View.VISIBLE);
                    //点击触发事件展开下面显示的内容
                    a = 2;//这时候a=2
                } else { //再点一下a=2
                    holder.oneLlt.setVisibility(View.GONE);
                    //点击出发时间展开小按钮不显示
                    a = 1; //这时a=1
                }
                line1 = buslists.get(position).getId();
                //从线路列表的集合里取到id值  变于下面的请求
                Network.doGet("/prod-api/api/bus/stop/list?linesId=" + line1, new OkResult() {
                    //根据线路的id 请求到每一条线路的站点信息
                    @Override
                    public void succes(JSONObject jsonObject) {
                        List<Show> shows = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Show>>() {
                        }.getType());
                        holder.oneRecycleView.setLayoutManager(new LinearLayoutManager(context));
                        holder.oneRecycleView.setAdapter(new ShowAdapter(context, shows));
                    }
                });
            }
        });

        holder.busXianlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                line1=buslists.get(position).getId();
                context.startActivity(new Intent(context, DetailsActivity.class));

                /*这里是给messageActivity定义的公共的变量赋值 分别是价格和几号线*/
                MessageActivity.money1 = (int)buslists.get(position).getPrice();
                MessageActivity.path = ""+buslists.get(position).getName();
            }
        });
    }

    @Override
    public int getItemCount() {
        return buslists.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private Button busXianlu;
        private TextView busStart;
        private TextView busEnd;
        private TextView busMoney;
        private TextView busTime1;
        private TextView busKm;
        private TextView busTime2;
        private ImageView xiabiao;

        private LinearLayout oneLlt;
        private RecyclerView oneRecycleView;


        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            busXianlu = (Button) itemView.findViewById(R.id.bus_xianlu);
            busStart = (TextView) itemView.findViewById(R.id.bus_start);
            busEnd = (TextView) itemView.findViewById(R.id.bus_end);
            busMoney = (TextView) itemView.findViewById(R.id.bus_money);
            busTime1 = (TextView) itemView.findViewById(R.id.bus_time1);
            busKm = (TextView) itemView.findViewById(R.id.bus_km);
            busTime2 = (TextView) itemView.findViewById(R.id.bus_time2);
            xiabiao = (ImageView) itemView.findViewById(R.id.xiabiao);

            oneLlt = (LinearLayout) itemView.findViewById(R.id.one_llt);
            oneRecycleView = (RecyclerView) itemView.findViewById(R.id.one_recycleView);
        }
    }
}
