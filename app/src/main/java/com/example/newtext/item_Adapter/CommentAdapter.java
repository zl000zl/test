package com.example.newtext.item_Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.StringBean.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.InnerHolder> {
    private Context context;
    private List<Comment> comments;
    //绑定Bean集合 和适配器的上下文 方便RecycleView的绑定

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
        //实例化他们
    }

    @NonNull
    @Override
    public CommentAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_comment, null);
        return new InnerHolder(view);
        //显示适配器中布局
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.InnerHolder holder, int position) {
        holder.tv_test01.setText("User：" + comments.get(position).getUserName());
        holder.time_data.setText("Data：" + comments.get(position).getCommentDate());
        holder.tv_content.setText("Content：" + comments.get(position).getContent());
        //取到每一条新闻评论的用户名、评论的日期、评论的内容
    }

    @Override
    public int getItemCount() {
        return comments.size();
        //取到所有的评论
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView tv_test01;
        private TextView time_data;
        private TextView tv_content;
        //绑定控件

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tv_test01 = itemView.findViewById(R.id.tv_test01);
            time_data = itemView.findViewById(R.id.time_data);
            tv_content = itemView.findViewById(R.id.tv_content);
            //调取他们的布局
        }
    }
}
