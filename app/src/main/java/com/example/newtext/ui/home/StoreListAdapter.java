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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.NewClass.Todaynews;
import com.example.newtext.R;
import com.example.newtext.StringBean.Press;
import com.example.newtext.Mytoken;

import java.util.List;

public class StoreListAdapter extends RecyclerView.Adapter <StoreListAdapter.LinearViewHolder> {
    private List<Press> presses;
    //绑定post请求的新闻数据
    private Context mContext;
    //绑定上下文
    public StoreListAdapter(List<Press> presses, Context mContext) {
        this.presses = presses;
        this.mContext = mContext;
        //ALT+insert的方法构造
    }

    /*注意在RecycleView中的中调用布局控件时 要用itemView的方法*/

    public StoreListAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传一个item布局
        View view = View.inflate(mContext,R.layout.item_list,null);
        return new LinearViewHolder(view);
    }


    //这里的方法是展示数据的
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onBindViewHolder(@NonNull StoreListAdapter.LinearViewHolder holder, int position) {
        Glide.with(holder.image_store).load(Mytoken.URl+presses.get(position).getCover()).into(holder.image_store);
        //加载图片(使用holder开头加载图片+presses.get(position).getcover是取出每个位置的图片)后面的参数与前面的with相同
        holder.tv_message.setText(Html.fromHtml("&#12288;"+presses.get(position).getTitle(),1));//去除网页元素
        //相同的方法取出文本的内容 HTml.fromHtmL(",1)去除网页因素的作用

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //RecycleView的点击事件
            @Override
            public void onClick(View view) {
                Todaynews.news_name_Id= presses.get(position).getId();
                //从要跳转的Activity(定义了一个公共静态的bean类(Press))中获取到请求的所有数据 取出他们的id值利于新闻评论的传入
                Todaynews.press = presses.get(position);
                //从要跳转的Activity(定义了一个公共静态的bean类(Press))中获取到请求的所有数据 再得到他们的位置
                /*NewclassActivity.press = presses.get(position);*/
                //从要跳转的Activity(定义了一个公共静态的bean类(Press))中获取到请求的所有数据 再得到他们的位置
                mContext.startActivity(new Intent(mContext,Todaynews.class));

                //跳转到NewclassActivity页面
            }
        });
    }

//需要展示的个数
    public int getItemCount() {
        return 4;
    }
//需要定义一个内部类继承ViewHolder
    public class LinearViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_store;
        private TextView tv_message;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);

            image_store = itemView.findViewById(R.id.image_store);
            tv_message = itemView.findViewById(R.id.tv_message);
        }
    }
}
