package com.example.newtext.WaiMai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Non_payment;
import com.example.newtext.WaiMai.WorderFragment;
import com.example.newtext.Mytoken;

import java.util.List;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.InnerHolder> {
    private Context context;
    private List<Non_payment> non_payments;
    private OnClickListener onClickListener;

    public static String click;
    //公共的变量 方便判断你点击了哪个按钮


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Non_payment> getNon_payments() {
        return non_payments;
    }

    public void setNon_payments(List<Non_payment> non_payments) {
        this.non_payments = non_payments;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payments, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        switch (WorderFragment.atype) {
            case "待评价":
                //这是处理在一个RecycleView中改变不同tab里的recycleView的值
                holder.dzGo.setText("评价");
                holder.dzGo1.setVisibility(View.VISIBLE);
                break;
            case "完成":
                holder.dzGo.setVisibility(View.GONE);
                break;
            case "退款":
                holder.dzGo.setVisibility(View.INVISIBLE);
                break;

        }
        holder.dzState.setText(non_payments.get(position).getOrderInfo().getStatus());
        holder.dzName.setText(non_payments.get(position).getSellerInfo().getName());
        Glide.with(holder.dzTheme).load(Mytoken.URl + non_payments.get(position).getSellerInfo().getImgUrl()).into(holder.dzTheme);

//        你选择的每一个订单的每个菜品样式的RecycleView
        holder.neiRecycle.setLayoutManager(new LinearLayoutManager(context));
        holder.neiRecycle.setAdapter(new NeiAdapter(context, non_payments.get(position).getOrderInfo().getOrderItemList()));
//       得到Non_payment大bean类里的小bean传到NeiAdapter中

        holder.dzGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = "评价";
                if (onClickListener != null) {
                    onClickListener.onClick(view, position);
                }
            }
        });
        holder.dzGo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = "退款";
                if (onClickListener != null) {
                    onClickListener.onClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return non_payments.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView dzName;
        public TextView dzState;
        private ImageView dzTheme;
        private RecyclerView neiRecycle;
        private Button dzGo;
        private Button dzGo1;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            dzName = (TextView) itemView.findViewById(R.id.dz_name);
            dzState = (TextView) itemView.findViewById(R.id.dz_state);
            dzTheme = (ImageView) itemView.findViewById(R.id.dz_theme);
            neiRecycle = (RecyclerView) itemView.findViewById(R.id.nei_recycle);
            dzGo = (Button) itemView.findViewById(R.id.dz_go);
            dzGo1 = (Button) itemView.findViewById(R.id.dz_go1);
        }
    }
}
