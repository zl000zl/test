package com.example.newtext.ui.Party.Pt_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.ui.Party.Dynamic.PartyActivity1;
import com.example.newtext.ui.Party.Dynamic.PartyActivity2;
import com.example.newtext.ui.Party.Dynamic.PartyActivity3;
import com.example.newtext.ui.Party.Dynamic.PartyActivity4;
import com.example.newtext.ui.Party.Dynamic.PartyActivity5;

import java.util.ArrayList;
import java.util.List;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.InnerHolder> {
    private Context context;
    private String[] title = {"党建动态", "党员学习", "组织活动", "建言献策", "随手拍"};
    List<Integer> images = new ArrayList<>();

    public PartyAdapter(Context context) {
        this.context = context;
        images.add(R.drawable.dongtai);
        images.add(R.drawable.xuexi);
        images.add(R.drawable.huodong);
        images.add(R.drawable.xiance);
        images.add(R.drawable.pai);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_partyone, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.pyImage.setImageResource(images.get(position));
        holder.pyTitle.setText(title[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (title[position]){
                    case "党建动态":
                        context.startActivity(new Intent(context, PartyActivity1.class));
                        break;
                    case "党员学习":
                        context.startActivity(new Intent(context, PartyActivity2.class));
                        break;
                    case "组织活动":
                        context.startActivity(new Intent(context, PartyActivity3.class));
                        break;
                    case "建言献策":
                        context.startActivity(new Intent(context, PartyActivity4.class));
                        break;
                    case "随手拍":
                        context.startActivity(new Intent(context, PartyActivity5.class));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView pyImage;
        private TextView pyTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            pyImage = (ImageView) itemView.findViewById(R.id.py_image);
            pyTitle = (TextView) itemView.findViewById(R.id.py_title);
        }
    }
}
