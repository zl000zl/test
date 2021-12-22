package com.example.newtext.Hospital.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.HospitalBean.Hot;
import com.example.newtext.R;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.InnerHolder> {
    private Context context;
    private List<Hot> hots;

    public HotAdapter(Context context, List<Hot> hots) {
        this.context = context;
        this.hots = hots;

    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_hot, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

            holder.hotTitle.setText(""+hots.get(position).getCategoryName());
            holder.hotTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "您点击了"+hots.get(position).getCategoryName(), Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private Button hotTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            hotTitle = (Button) itemView.findViewById(R.id.hot_title);
        }
    }
}
