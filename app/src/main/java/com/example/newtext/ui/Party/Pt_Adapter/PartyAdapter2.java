package com.example.newtext.ui.Party.Pt_Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;

import java.util.ArrayList;
import java.util.List;

public class PartyAdapter2 extends RecyclerView.Adapter<PartyAdapter2.InnerHolder> {
    private Context context;
    private String[] title2 = {"我们想做点什么？我们还能再多做点什么？", "YCx创显科教抗战胜利75周年纪念日主题党日活动"};
    List<Integer> image2 = new ArrayList<>();


    public PartyAdapter2(Context context) {
        this.context = context;
        image2.add(R.drawable.activity1);
        image2.add(R.drawable.activit2);

    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_partyone2, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.pyImage2.setImageResource(image2.get(position));
        holder.pyTitle2.setText(title2[position]);
    }

    @Override
    public int getItemCount() {
        return title2.length;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView pyImage2;
        private TextView pyTitle2;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            pyImage2 = (ImageView) itemView.findViewById(R.id.py_image2);
            pyTitle2 = (TextView) itemView.findViewById(R.id.py_title2);
        }
    }
}
