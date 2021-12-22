package com.example.newtext.WaiMai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.OnClickListener;
import com.example.newtext.R;
import com.example.newtext.WaiMai.Waimaibean.Address;

import java.util.List;

public class SureAdapter extends RecyclerView.Adapter<SureAdapter.InnerHolder> {
    private Context context;
    private List<Address> addresses;
    private OnClickListener onClickListener;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choice, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.titleAddress.setText("收货地址："+addresses.get(position).getAddressDetail());
        holder.titleName.setText("联系人："+addresses.get(position).getName());
        holder.titlePhone.setText("手机："+addresses.get(position).getPhone());
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
        return addresses.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView titleAddress;
        private TextView titleName;
        private TextView titlePhone;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            titleAddress = (TextView) itemView.findViewById(R.id.title_address);
            titleName = (TextView) itemView.findViewById(R.id.title_name);
            titlePhone = (TextView) itemView.findViewById(R.id.title_phone);
        }
    }
}
