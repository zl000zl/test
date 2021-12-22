package com.example.newtext.SmartBus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.SmartBus.BusBean.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.InnerHolder> {
    private Context context;
    List<Order> orders;
    private RecyclerView orderRecycle;

    public OrderAdapter(Context context, List<Order> orders, RecyclerView orderRecycle) {
        this.context = context;
        this.orders = orders;
        this.orderRecycle = orderRecycle;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.derNumber.setText(""+orders.get(position).getOrderNum());
        holder.derPrice.setText("￥"+orders.get(position).getPrice());
        holder.derFirst.setText(""+orders.get(position).getStart());
        holder.derEnd.setText(""+orders.get(position).getEnd());
        holder.derLine.setText(""+orders.get(position).getPath());
        holder.derName.setText(""+orders.get(position).getUserName());
        holder.derPhone.setText(""+orders.get(position).getUserTel());

        if (orders.get(position).getStatus()==1){
            holder.derPay.setVisibility(View.GONE);
        }   //判断立即支付按钮在已支付界面为不显示

        holder.derPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderCode = holder.derNumber.getText().toString().trim();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("orderNum",orderCode).put("paymentType","电子支付");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/bus/pay", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(context, "支付成功！！！", Toast.LENGTH_SHORT).show();

                            /*下面是做立即刷新*/
                            Network.doGet("/prod-api/api/bus/order/list?status=0", new OkResult() {
                                //get请求到订单未支付列表
                                @Override
                                public void succes(JSONObject jsonObject) {
                                    List<Order> orders = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Order>>(){
                                    }.getType());
                                    orderRecycle.setLayoutManager(new LinearLayoutManager(context));
                                    orderRecycle.setAdapter(new OrderAdapter(context,orders,orderRecycle));
                                }
                            });
                        }else{
                            Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView derNumber;
        private TextView derPrice;
        private ImageView imageView10;
        private TextView derFirst;
        private ImageView imageView11;
        private TextView derEnd;
        private TextView derLine;
        private TextView derName;
        private TextView derPhone;
        private Button derPay;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            derNumber = (TextView) itemView.findViewById(R.id.der_number);
            derPrice = (TextView) itemView.findViewById(R.id.der_price);
            imageView10 = (ImageView) itemView.findViewById(R.id.imageView10);
            derFirst = (TextView) itemView.findViewById(R.id.der_first);
            imageView11 = (ImageView) itemView.findViewById(R.id.imageView11);
            derEnd = (TextView) itemView.findViewById(R.id.der_end);
            derLine = (TextView) itemView.findViewById(R.id.der_line);
            derName = (TextView) itemView.findViewById(R.id.der_name);
            derPhone = (TextView) itemView.findViewById(R.id.der_phone);
            derPay = (Button) itemView.findViewById(R.id.der_pay);
        }
    }
}
