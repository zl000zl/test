package com.example.newtext.WaiMai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Pingjia;

import java.util.List;

public class PjAdapter extends RecyclerView.Adapter<PjAdapter.InnerHolder> {
    private Context context;
    private List<Pingjia> pingjias;



    public PjAdapter(Context context, List<Pingjia> pingjias) {
        this.context = context;
        this.pingjias = pingjias;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pingjia, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Pingjia pingjia = pingjias.get(position);
        holder.pjName.setText("" + pingjia.getUserName());
        holder.pjPingfen.setRating((float) +pingjia.getScore());
        holder.pjTime.setText("" + pingjia.getCommentDate());
        holder.pjContent.setText("" + pingjia.getContent());
        holder.shangjia.setText(""+pingjia.getReplyContent());
    }

    @Override
    public int getItemCount() {
        return pingjias.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView pjTx;
        private TextView pjName;
        private TextView pjTime;
        private RatingBar pjPingfen;
        private TextView pjContent;
        private TextView shangjia;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            pjTx = (ImageView) itemView.findViewById(R.id.pj_tx);
            pjName = (TextView) itemView.findViewById(R.id.pj_name);
            pjTime = (TextView) itemView.findViewById(R.id.pj_time);
            pjPingfen = (RatingBar) itemView.findViewById(R.id.pj_pingfen);
            pjContent = (TextView) itemView.findViewById(R.id.pj_content);
            shangjia = (TextView) itemView.findViewById(R.id.shangjia);
        }
    }
}
