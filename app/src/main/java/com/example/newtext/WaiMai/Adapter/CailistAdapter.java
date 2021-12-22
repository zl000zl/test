package com.example.newtext.WaiMai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.CaipNum;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Activity.Order_sure;
import com.example.newtext.WaiMai.Waimaibean.Cailist;
import com.example.newtext.Mytoken;

import java.util.ArrayList;
import java.util.List;

public class CailistAdapter extends RecyclerView.Adapter<CailistAdapter.InnerHolder> {
    private List<Cailist> cailists = new ArrayList<>();
    //定于菜品列表bean类  为一个集合
    private CaipNum caipNum;
    //这里是在外面定义了一个接口(处理下面的购物车钱数和分数的增加和减去的) 然后拿出去

    public List<Cailist> getCailists() {
        return cailists;
    }

    public void setCailists(List<Cailist> cailists) {
        this.cailists = cailists;
    }

    public CailistAdapter(List<Cailist> cailists) {
        this.cailists = cailists;
    }

    public CailistAdapter() {
    }

    public CaipNum getCaipNum() {
        return caipNum;
    }

    public void setCaipNum(CaipNum caipNum) {
        this.caipNum = caipNum;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cailist, parent, false);
        return new InnerHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Cailist cailist = cailists.get(position);
        Glide.with(holder.listImage).load(Mytoken.URl + cailist.getImgUrl()).into(holder.listImage);
        holder.listName.setText("" + cailist.getName());
        holder.listXiaoliang.setText("销量：" + cailist.getSaleQuantity()+"份");
        holder.listHaoping.setText(""+cailist.getFavorRate()+"%");
        holder.listPrice.setText("每人/"+cailist.getPrice()+"￥");
        holder.listNumber.setText(cailist.getCount()+"");
        if (cailist.getCount()==0) holder.listRemove.setEnabled(false);
        //当菜品列表的里的菜品数量为0的时候对减号做禁用处理使其无法点击  这里的getCount是在菜品列表bean类里定义的
        holder.listAdd.setOnClickListener(new View.OnClickListener() {
            //增加按钮的点击事件
            @Override
            public void onClick(View v) {
                int count = cailist.getCount();
                //这里定义了count 就等于菜品列表里的getCount
                count++;
                //数量的相加
                if (count == 1){
                    //如果count数量增加变为1的时候
                    holder.listRemove.setEnabled(true);
                    //减号按钮就可以使用了
                }
                holder.listNumber.setText(count+"");
                //适配器里的xml定义的加减好中间的数量 进行 赋值相加

                Order_sure.shuoliang=holder.listNumber.getText().toString().trim();
                //这里是提交订单时 选择各个商品的数量 从详情页面加号里赋值

                cailist.setCount(count);
                //给菜品列表的bean类Cailist的setCount赋值为这里增加count 方便Activity 中的购物车增加
                if (caipNum!=null){
                    //如果接口里的值为空的话
                    caipNum.add(cailist,Double.parseDouble(String.format("%.2f",cailist.getPrice())),position);
                    //定义的接口就进行添加 Double.parseDouble就是把括号的内容变为double类型 带上下标值
                }
            }
        });
        holder.listRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = cailist.getCount();
                count--;
                if (count==0){
                    holder.listRemove.setEnabled(false);
                }
                holder.listNumber.setText(count+"");
                cailist.setCount(count);
                if (caipNum!=null){
                    caipNum.remove(cailist,Double.parseDouble(String.format("%.2f",cailist.getPrice())),position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cailists.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView listImage;
        private TextView listName;
        private TextView listXiaoliang;
        private TextView listHaoping;
        private TextView listPrice;
        private ImageView listAdd;
        private TextView listNumber;
        private ImageView listRemove;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            listImage = (ImageView) itemView.findViewById(R.id.list_image);
            listName = (TextView) itemView.findViewById(R.id.list_name);
            listXiaoliang = (TextView) itemView.findViewById(R.id.list_xiaoliang);
            listHaoping = (TextView) itemView.findViewById(R.id.list_haoping);
            listPrice = (TextView) itemView.findViewById(R.id.list_price);
            listAdd = (ImageView) itemView.findViewById(R.id.list_add);
            listNumber = (TextView) itemView.findViewById(R.id.list_number);
            listRemove = (ImageView) itemView.findViewById(R.id.list_remove);
        }
    }
}
