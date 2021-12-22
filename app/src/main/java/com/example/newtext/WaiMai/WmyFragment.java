package com.example.newtext.WaiMai;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.User;
import com.example.newtext.WaiMai.Activity.My_comment;
import com.example.newtext.WaiMai.Activity.ShouhuoActivity;
import com.google.gson.Gson;

import org.json.JSONObject;

public class WmyFragment extends Fragment {


    private ImageView myTongx;
    private TextView myUser;
    private TextView myAddress;
    private TextView myComment;
    private TextView myOrder;

    public static String myname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wmy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                User user = new Gson().fromJson(jsonObject.optString("user"), User.class);
                myUser.setText(""+user.getNickName());
                Glide.with(myTongx).load("https://c-ssl.duitang.com/uploads/blog/202107/09/20210709105552_7c4aa.thumb.1000_0.jpeg")
                        .into(myTongx);
            }
        });

        myAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ShouhuoActivity.class));
            }
        });
        myComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), My_comment.class));
            }
        });
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_worder);
            }
        });
    }

    private void initView(View getView) {
        myTongx = (ImageView) getView.findViewById(R.id.my_tongx);
        myUser = (TextView) getView.findViewById(R.id.my_user);
        myAddress = (TextView) getView.findViewById(R.id.my_address);
        myComment = (TextView) getView.findViewById(R.id.my_comment);
        myOrder = (TextView) getView.findViewById(R.id.my_order);
    }
}