package com.example.newtext.Room;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.Room;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.InnnerHolder> {
    private Context context;
    List<Integer> images = new ArrayList<>();
    private String[] title = {"二手","租房","楼盘","中介"};
    List<Room> rooms;
    private RecyclerView mesRecycleView;

    public RoomAdapter(Context context,RecyclerView mesRecycleView) {
        this.context = context;
        this.mesRecycleView = mesRecycleView;
        images.add(R.drawable.room4);
        images.add(R.drawable.room3);
        images.add(R.drawable.room1);
        images.add(R.drawable.room2);
    }

    @NonNull
    @Override
    public InnnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_room, null);
        return new InnnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnnerHolder holder, int position) {
        holder.roomImage.setImageResource(images.get(position));
        holder.roomTitle.setText(title[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Network.doGet("/prod-api/api/house/housing/list?houseType=" + title[position], new OkResult() {
                    //这里的请求需要带上houseType房子的类型  才能点击每条分类展示不同的数据
                    @Override
                    public void succes(JSONObject jsonObject) {
                        List<Room> rooms = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Room>>() {
                        }.getType());
                        mesRecycleView.setLayoutManager(new LinearLayoutManager(context));
                        mesRecycleView.setAdapter(new TabAdapter(context, rooms));
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class InnnerHolder extends RecyclerView.ViewHolder {
        private ImageView roomImage;
        private TextView roomTitle;
        public InnnerHolder(@NonNull View itemView) {
            super(itemView);
            roomImage = (ImageView) itemView.findViewById(R.id.room_image);
            roomTitle = (TextView) itemView.findViewById(R.id.room_title);
        }
    }
}
