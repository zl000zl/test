package com.example.newtext.Hospital.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Hospital.Activity.QuerenActivity;
import com.example.newtext.HospitalBean.Classi;
import com.example.newtext.R;

import java.util.List;

public class Classify extends RecyclerView.Adapter<Classify.InnerHolder> {
    private Context context;
    List<Classi> classis;

    public Classify(Context context, List<Classi> classis) {
        this.context = context;
        this.classis = classis;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_classify, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.classTitle.setText(""+classis.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*这里是从这个适配器中向querenActivity中传值的*/
                QuerenActivity.keshi = classis.get(position).getCategoryName();
                QuerenActivity.qian = (int) classis.get(position).getMoney();

                QuerenActivity.ksId = classis.get(position).getId();
                //这里传入金额是数值 所以前面要加int进行强转
                context.startActivity(new Intent(context, QuerenActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return classis.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView classTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            classTitle = (TextView) itemView.findViewById(R.id.class_title);
        }
    }
}
