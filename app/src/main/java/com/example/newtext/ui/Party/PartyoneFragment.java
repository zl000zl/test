package com.example.newtext.ui.Party;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.R;
import com.example.newtext.ui.Party.Pt_Adapter.PartyAdapter;
import com.example.newtext.ui.Party.Pt_Adapter.PartyAdapter2;
import com.example.newtext.ui.Party.Pt_Adapter.PartyAdapter3;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PartyoneFragment extends Fragment {
    private Banner partBanner;
    private List<Integer> partylist;
    PartImageLoader partImageLoader = new PartImageLoader();

    private RecyclerView pt_recycleView;
    private RecyclerView pt_recycleView2;

    private TabLayout pt_tablayout;
    private RecyclerView Pt_recycleView3;
    private String[] tabtitle = {"党务要闻","抗疫模范","党建风采","学习园地"};

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.partyone_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initRecycleView();
        initRecycleView2();
        initTabline();
        initTablayout();
    }

    private void initTablayout() {
        String strId="0";
        loadData( "0");
        for (int i=0;i<4;i++){
            pt_tablayout.addTab(pt_tablayout.newTab().setText(tabtitle[i]).setTag(i));
        }
        pt_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String strId = tab.getTag().toString();
                //定义每个新闻专题的类别装换为toString类型
                loadData(strId);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadData(String strId) {
        Pt_recycleView3 = getView().findViewById(R.id.pt_recycleView3);
        Pt_recycleView3.setLayoutManager(new LinearLayoutManager(getActivity()));
        Pt_recycleView3.setAdapter(new PartyAdapter3(getActivity(),strId));
        //后面的strId是tablayout每个标题的id值 现在把它传入适配器中
    }

    private void initTabline() {
        pt_tablayout = getView().findViewById(R.id.pt_tablayout);
        LinearLayout linearLayout = (LinearLayout) pt_tablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(25);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.rv_line));
    }

    private void initRecycleView2() {
        pt_recycleView2 = getView().findViewById(R.id.pt_recycleView2);
        pt_recycleView2.setLayoutManager(new GridLayoutManager(getActivity(),2));
        pt_recycleView2.setAdapter(new PartyAdapter2(getActivity()));
    }

    private void initRecycleView() {
        pt_recycleView = getView().findViewById(R.id.pt_recycleView);
        pt_recycleView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        pt_recycleView.setAdapter(new PartyAdapter(getActivity()));
    }

    private void initData() {
        partBanner = getActivity().findViewById(R.id.part_banner);
        partylist = new ArrayList<>();
        partylist.add(R.drawable.dangjian1);
        partylist.add(R.drawable.dangjian2);
        partylist.add(R.drawable.dangjian3);
        partylist.add(R.drawable.dangjian4);

        partBanner.setImageLoader(partImageLoader);
        partBanner.setImages(partylist);
        partBanner.start();
    }

    private class PartImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(imageView).load(path).into(imageView);
        }
    }
}