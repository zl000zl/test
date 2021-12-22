package com.example.newtext.WaiMai.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Caitype;

import java.util.ArrayList;
import java.util.List;

public class CaiAdapter extends RecyclerView.Adapter<CaiAdapter.InnerHolder> {
    private List<Caitype> caitypes = new ArrayList<>();
    //定义菜品类别bean类为数组类型的
    private OnClickListener onClickListener;
    //因为要点击左边的菜品类别的条目 而在适配器里不能设置点击事件  所有要在适配器里点击  然后拿出去

    public List<Caitype> getCaitypes() {
        return caitypes;
    }

    public void setCaitypes(List<Caitype> caitypes) {
        this.caitypes = caitypes;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caitype, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.cailist.setText(""+caitypes.get(position).getName());
        if (position==0) holder.itemView.setBackgroundColor(Color.WHITE);
        //如果当下标值为0的时候  也就是刚进去的时候 左边的条目第一个值就为白色
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //在这里设置点击事件然后拿出去
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    //如果点击事件不为空
                    onClickListener.onClick(v,position);
                    //就触发点击事件按钮带适配器里的下标值
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return caitypes.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView cailist;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            cailist = (TextView) itemView.findViewById(R.id.cai_list);
        }
    }
}
