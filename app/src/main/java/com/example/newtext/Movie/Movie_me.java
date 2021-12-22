package com.example.newtext.Movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.newtext.Movie.My.Dd_list;
import com.example.newtext.Personal.Activity.FeedbackActivity;
import com.example.newtext.Personal.Activity.PassActivity;
import com.example.newtext.Personal.Activity.Personal1Activity;
import com.example.newtext.R;

public class Movie_me extends Fragment {

    private ImageView grTongx;
    private TextView grXinxi;
    private TextView grDingdan;
    private TextView grMima;
    private TextView grYijian;
    private Button back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initSkip();
    }

    private void initSkip() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void initData() {
        Glide.with(grTongx).load("https://c-ssl.duitang.com/uploads/blog/202107/09/20210709105552_7c4aa.thumb.1000_0.jpeg")
                .into(grTongx);

        grXinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Personal1Activity.class));
            }
        });
        grMima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PassActivity.class));
            }
        });
        grDingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Dd_list.class));
            }
        });
        grYijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
            }
        });
    }

    private void initView(View getView) {
        grTongx = (ImageView) getView.findViewById(R.id.gr_tongx);
        grXinxi = (TextView) getView.findViewById(R.id.gr_xinxi);
        grDingdan = (TextView) getView.findViewById(R.id.gr_dingdan);
        grMima = (TextView) getView.findViewById(R.id.gr_mima);
        grYijian = (TextView) getView.findViewById(R.id.gr_yijian);
        back = (Button) getView.findViewById(R.id.back);
    }
}