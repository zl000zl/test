package com.example.newtext.Movie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Movie.Activity.RondaActivity;
import com.example.newtext.Movie.Bean.Fuck;
import com.example.newtext.Movie.Bean.Session;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.InnerHolder> {
    private Context context;
    private List<Session> sessions;
    private OnClickListener onClickListener;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cinema, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.cinemaName.setText(""+sessions.get(position).getTheatreName());
        holder.cinemaPrice.setText("￥"+sessions.get(position).getPrice());
        holder.cinemaSale.setText("销量："+sessions.get(position).getTotalNum());
        Network.doGet("/prod-api/api/movie/theatre/" + sessions.get(position).getTheaterId(), new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    Fuck fuck = new Gson().fromJson(jsonObject.optString("data"),Fuck.class);
                    holder.cinemaAddress.setText("地址："+fuck.getAddress());
                    RondaActivity.cinaddress = fuck.getAddress();
                    //向RondaAtivity中传地址值
                }else {
                    Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener!=null){
                    onClickListener.onClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView cinemaName;
        private TextView cinemaPrice;
        private TextView cinemaSale;
        private TextView cinemaAddress;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            cinemaName = (TextView) itemView.findViewById(R.id.cinema_name);
            cinemaPrice = (TextView) itemView.findViewById(R.id.cinema_price);
            cinemaSale = (TextView) itemView.findViewById(R.id.cinema_sale);
            cinemaAddress = (TextView) itemView.findViewById(R.id.cinema_address);
        }
    }
}
