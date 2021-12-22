package com.example.newtext.item_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Allservice.PackThree;
import com.example.newtext.R;
import com.example.newtext.StringBean.Allservice;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.InnerHolder> {
    private Context context;
    //绑定上下文
    private List<Allservice> allservices;
    //绑定停哪儿Bean数据集合

    public ServiceAdapter(Context context, List<Allservice> allservices) {
        this.context = context;
        this.allservices = allservices;
        //实例化两个方法
    }

    @NonNull
    @Override
    public ServiceAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_allservice, null);
        return new InnerHolder(view);
        //显示出布局内容的界面
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.InnerHolder holder, int position) {
        /*给TextView赋上想请求得到的值*/
        holder.tv_show1.setText("" + allservices.get(position).getParkName());
        holder.tv_show2.setText("" + allservices.get(position).getDistance() + "公里");
        holder.tv_show3.setText("空位" + allservices.get(position).getVacancy() + "个");
        holder.tv_show4.setText("停车费" + allservices.get(position).getRates() + "￥/小时");
        holder.tv_show5.setText("" + allservices.get(position).getAddress());
        holder.iv_show6.setOnClickListener(new View.OnClickListener() {
            //停车场查询的按钮的点击事件
            @Override
            public void onClick(View view) {
                PackThree.allservice = allservices.get(position);
                //把从PackActivity中请求到的Bean数据列表赋值到PackThree中(带下标)
                context.startActivity(new Intent(context, PackThree.class));
                //点击跳转到停车场详情界面
            }
        });
    }

    @Override
    public int getItemCount() {
        return allservices.size();
        //显示你要用的每一个界面的数据
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView tv_show1;
        private TextView tv_show2;
        private TextView tv_show3;
        private TextView tv_show4;
        private TextView tv_show5;
        private ImageView iv_show6;
        //绑定控件的id

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tv_show1 = itemView.findViewById(R.id.tv_show1);
            tv_show2 = itemView.findViewById(R.id.tv_show2);
            tv_show3 = itemView.findViewById(R.id.tv_show3);
            tv_show4 = itemView.findViewById(R.id.tv_show4);
            tv_show5 = itemView.findViewById(R.id.tv_show5);
            iv_show6 = itemView.findViewById(R.id.iv_show6);
            //调用控件的假面布局
        }
    }
}
