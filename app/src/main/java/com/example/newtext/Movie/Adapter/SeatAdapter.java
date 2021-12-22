package com.example.newtext.Movie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.Activity.SeatActivity;
import com.example.newtext.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.InnerHolder> {
    private Context context;

    public SeatAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.seatImage.setImageResource(R.drawable.box);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeatActivity.pai = new ArrayList<>();
                SeatActivity.zuo = new ArrayList<>();

                //初始化数组 点击加载或取消
                if (holder.a[position]==0){
                    //判断 如果刚进去没选择的时候

                    SeatActivity.count++;
                    SeatActivity.seatNumber.setText(""+SeatActivity.count+"张");
                    //点击座位 数量相加

                    DecimalFormat df = new DecimalFormat("#####0.00");
                    SeatActivity.price += SeatActivity.movie_money;
                    SeatActivity.moviestr = df.format(SeatActivity.price);
                    SeatActivity.seatMoney.setText("￥"+SeatActivity.moviestr);
                    //根据电影票数量的多少 价钱相加

                    SeatActivity.b[position]=(int)( Math.ceil(position/16)+1);
                    //排号就是SeatActivity中定义的集合80除以16 下标是从15开始的所以要加1
                    if (position>15){
                        //如果点击的下标(80)大于16 (下标是0-15)
                        SeatActivity.c[position]=(int) (Math.ceil(position%16)+1);
                        //座位号就是SeatActivity中定义的集合80 根据你点击超过16的下标余 的数 下标从15开始 加一
                    }else {
                        SeatActivity.c[position]=position+1;
                        //当你点击第一个的时候 下标从0开始 所以要加一
                    }

                    holder.seatImage.setImageResource(R.drawable.box1);
                    //点击座位时变化颜色为红色
                    holder.a[position]=1;
                    //这时下标变为1

                }else {

                    if (SeatActivity.count!=0){
                        //如果选择的座位为0的时候
                        SeatActivity.count--;
                        SeatActivity.seatNumber.setText(""+SeatActivity.count+"张");
                        //座位相减

                        DecimalFormat df = new DecimalFormat("#####0.00");
                        SeatActivity.price -= SeatActivity.movie_money;
                        SeatActivity.moviestr = df.format(SeatActivity.price);
                        SeatActivity.seatMoney.setText("￥"+SeatActivity.moviestr);
                        //根据电影票数量的多少 价钱相减
                    }

                    //点击取消选座的时候  所有数据从0开始
                    SeatActivity.b[position]=0;
                    SeatActivity.c[position]=0;

                    holder.seatImage.setImageResource(R.drawable.box);
                    //再次点击时 图标变成变为白色
                    holder.a[position]=0;
                    //重新赋值为0 方便下次点击取消
                }

                for (int i=0; i<80; i++){
                    //循环取出80个数据
                    if (SeatActivity.b[i] !=0 ){
                        //如果排里面的数据集合不为0
                        SeatActivity.pai.add(SeatActivity.b[i]);
                        //那么排的数据集合进行添加
                    }
                    if (SeatActivity.c[i] !=0){
                        //如果座位里面的数据集合不为0
                        SeatActivity.zuo.add(SeatActivity.c[i]);
                        //座位里的数据进行添加
                    }
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                SeatActivity.listRecycle.setLayoutManager(linearLayoutManager);
                SeatActivity.listRecycle.setAdapter(new XzAdapter(context,SeatActivity.pai,SeatActivity.zuo));
                //点击座位时候下面实时显示选的座位信息；
            }
        });
    }

    @Override
    public int getItemCount() {
        return 80;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView seatImage;
        private int[] a=new int[80];

        int count = 0;
        //定义一个int类型 一共有80个座位  根据你点击了哪个来判断是否选择座位


        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            seatImage = (ImageView) itemView.findViewById(R.id.seat_image);
        }
    }
}
