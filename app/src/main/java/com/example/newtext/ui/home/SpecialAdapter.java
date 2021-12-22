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

class SpecialAdapter extends RecyclerView.Adapter <SpecialAdapter.LinearViewHolder> {
    private Context sContext;
    private List<Press> presses;
    //绑定上下文和新闻的集合



    public SpecialAdapter(Context sContext, List<Press> presses) {
        this.sContext = sContext;
        this.presses = presses;
        //快捷键构造
    }

    @NonNull
    @Override
    public SpecialAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(sContext,R.layout.item_list1,null);
        //传入一个item布局
        return new LinearViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    //展示数据的
    public void onBindViewHolder(@NonNull SpecialAdapter.LinearViewHolder holder, int position) {
        Glide.with(holder.image_stu).load(Mytoken.URl +presses.get(position).getCover()).into(holder.image_stu);
        //加载图片资源的(使用holder来加载图片+presses.get(position).getCover()是请求图片时加载的每一张图片的位置）后面的into的参数与前面你的参数一样
        holder.tv_show.setText(Html.fromHtml(""+presses.get(position).getTitle(),1));
        //来取得每一张图片对应的内容的位置
        holder.tv_like.setText(""+presses.get(position).getLikeNum());
        holder.tv_data.setText(""+presses.get(position).getPublishDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //RecycleView方法的点击事件
            @Override
            public void onClick(View view) {
                Todaynews.press = presses.get(position);
                //从Todaynews里带来的参数来绑定每一个新闻文本的位置

                Intent intent = new Intent(sContext, Todaynews.class);
                intent.setClass(sContext,Todaynews.class);
                sContext.startActivity(intent);
                //来启动跳转到Todaynews那个页面
            }
        });
    }

    @Override
    public int getItemCount() {
        return presses.size();
    }

    public class LinearViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_stu;
        private TextView tv_show;
        private TextView tv_like;
        private TextView tv_data;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);

            image_stu = itemView.findViewById(R.id.image_stu);
            tv_show = itemView.findViewById(R.id.tv_show);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_data =itemView.findViewById(R.id.tv_data);
        }
    }
}