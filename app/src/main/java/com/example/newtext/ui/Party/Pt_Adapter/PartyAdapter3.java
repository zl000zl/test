package com.example.newtext.ui.Party.Pt_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.R;
import com.example.newtext.ui.Party.YiActivity;

import java.util.ArrayList;
import java.util.List;

public class PartyAdapter3 extends RecyclerView.Adapter<PartyAdapter3.InnerHolder> {
    private Context context;
    private String strId;
    //先定义值的Id；
    List<Integer> image3 = new ArrayList<>();
    private String[] title3 = {"建立信息资讯平台，把握舆论宣传的主动权，传播主流价值，发挥引领作用",
            "建立业务管理平台，实现业务高效办理，推动党建工作规范化、标准化、科学化",
            "建立学习教育平台，打造24小时在线课堂，实现学习教育常态化，学习效果显性化",
            "建立线上活动平台，线上线下相结合，扩大活动覆盖面，增强党建工作影响力"};

    public PartyAdapter3(Context context,String strId) {
        this.strId = strId;
        //实现它
        this.context = context;
        image3.add(R.drawable.yaowen1);
        image3.add(R.drawable.yaowen2);
        image3.add(R.drawable.yaowen3);
        image3.add(R.drawable.yaowen4);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        //要先定义View的值为空 再往下判断
        switch (strId){
            case "0":
                //点击第一个的时候出现内容
             view = View.inflate(context, R.layout.item_partyone3, null);
            break;
            case "1":
                view = View.inflate(context, R.layout.item_partyone4, null);
                break;
            default:
                break;
        }
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        switch (strId){
            case "0":
                //要显示哪些内容  也要判断
                holder.pyImage3.setImageResource(image3.get(position));
                holder.pyTitle3.setText(title3[position]);
            break;
            case "1":
                holder.one4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, YiActivity.class));
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        //绑定子控件也要先定义为空
        int a =0;
        switch (strId){
            //当点击第一个的时候显示多少内容
            case "0":
                a=title3.length;
                break;
            case "1":
                a=1;
            default:
                break;
        }
        return a;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView pyImage3;
        private TextView pyTitle3;
        private Button one4;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            pyImage3 = (ImageView) itemView.findViewById(R.id.py_image3);
            pyTitle3 = (TextView) itemView.findViewById(R.id.py_title3);
            one4 = (Button) itemView.findViewById(R.id.one4);
        }
    }
}
