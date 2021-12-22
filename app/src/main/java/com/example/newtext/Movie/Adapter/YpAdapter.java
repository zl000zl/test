package com.example.newtext.Movie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Movie.Bean.HotMovie;
import com.example.newtext.Mytoken;
import com.example.newtext.OnClickListener;
import com.example.newtext.R;

import java.util.List;

public class YpAdapter extends RecyclerView.Adapter<YpAdapter.InnerHolder> {
    private List<HotMovie> hotMovies;
    private OnClickListener onClickListener;


    public List<HotMovie> getHotMovies() {
        return hotMovies;
    }

    public void setHotMovies(List<HotMovie> hotMovies) {
        this.hotMovies = hotMovies;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yplist, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.ypImage).load(Mytoken.URl+hotMovies.get(position).getCover()).into(holder.ypImage);
        holder.ypName.setText(""+hotMovies.get(position).getName());
        switch (hotMovies.get(position).getCategory()){
            case "1":
                holder.ypType.setText("故事");
                break;
            case "2":
                holder.ypType.setText("爱情");
                break;
            case "3":
                holder.ypType.setText("动作");
                break;
            case "4":
                holder.ypType.setText("喜剧");
                break;
            case "5":
                holder.ypType.setText("恐怖");
                break;
            case "6":
                holder.ypType.setText("惊悚");
                break;
            case "7":
                holder.ypType.setText("战争");
                break;
            case "8":
                holder.ypType.setText("科幻");
                break;
        }
        holder.ypRab.setRating((int)+hotMovies.get(position).getScore());
        switch (hotMovies.get(position).getPlayType()){
            case "1":
                holder.ypVideo.setText("国语2D");
                break;
            case "2":
                holder.ypVideo.setText("国语3D");
                break;
            case "3":
                holder.ypVideo.setText("国语IMAX");
                break;
            case "4":
                holder.ypVideo.setText("国语4DX");
                break;
        }
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
        return hotMovies.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView ypImage;
        private TextView ypName;
        private TextView ypType;
        private RatingBar ypRab;
        private TextView ypVideo;
        private Button ypGo;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ypImage = (ImageView) itemView.findViewById(R.id.yp_image);
            ypName = (TextView) itemView.findViewById(R.id.yp_name);
            ypType = (TextView) itemView.findViewById(R.id.yp_type);
            ypRab = (RatingBar) itemView.findViewById(R.id.yp_rab);
            ypVideo = (TextView) itemView.findViewById(R.id.yp_video);
            ypGo = (Button) itemView.findViewById(R.id.yp_go);
        }
    }
}
