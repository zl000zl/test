package com.example.newtext.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Allservice.ParkActivity;
import com.example.newtext.Allservice.Subway.SubwayActivity;
import com.example.newtext.Hospital.HospitalActivity;
import com.example.newtext.Job.JobActivity;
import com.example.newtext.Life.LifeActivity;
import com.example.newtext.Movie.MovieActivity;
import com.example.newtext.Mytoken;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.Room.RoomActivity;
import com.example.newtext.SmartBus.Activity.BusActivity;
import com.example.newtext.StringBean.Service;
import com.example.newtext.Traffic.Activity.TrafficActivity;
import com.example.newtext.WaiMai.TakeoutActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardFragment extends Fragment {
    private ListView listView;
    private RecyclerView recycleView;
    private String [] serviceTypes = {"????????????","????????????","????????????"};
    private int currentIndex = 0;
    private BaseAdapter listAdapter;

    private SearchView dash_search;
    private TextView dash_content;
    private RecyclerView dash_recycle;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListView();
        initRecycleView();
        initSearchView();

    }

    private void initSearchView() {
        dash_search = getView().findViewById(R.id.dash_search);
        dash_search.setSubmitButtonEnabled(true);
        dash_search.setQueryHint("???????????????");
        dash_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Mytoken.isFastDoubleClick()){
                    return false;
                }else {
                    //?????????????????????
                    get_dialog(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String next) {
                return false;
            }
        });
    }

    private void get_dialog(String query) {
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setNegativeButton("??????", new DialogInterface.OnClickListener() {
            //????????????????????????
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                //???????????????
            }
        });
        View view =LayoutInflater.from(getActivity()).inflate(R.layout.item_dash,null);
        //????????????item????????????????????????
        ad.setView(view);
        //???????????????item???
        Network.doGet("/prod-api/api/service/list?serviceName=" + query, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Service> services = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Service>>(){
                }.getType());
                //???????????????item?????????????????????????????????
                dash_content = view.findViewById(R.id.dash_content);
                dash_recycle = view.findViewById(R.id.dash_recycle);
                if (services.size() !=0){
                    //??????????????????????????????????????????????????????RecycleView
                    dash_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                    dash_recycle.setAdapter(new DashAdapter(getActivity(),services));
                }else {
                    //????????????RecycleView???????????????TextView?????????
                    dash_recycle.setVisibility(View.GONE);
                    dash_content.setVisibility(View.VISIBLE);
                }
            }
        });
        ad.show();
    }

    private void initRecycleView() {
        RecyclerView.Adapter<RecyclerView.ViewHolder> recycleAdapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_gridview_item,parent,false)) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                final TextView tv_Title= (TextView) holder.itemView.findViewById(R.id.tv_title);
                final GridView gridView = (GridView) holder.itemView.findViewById(R.id.gridView);
                tv_Title.setText(serviceTypes[position]);

                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        if (Mytoken.token!=null){
                            builder.addHeader("Authorization",Mytoken.token);
                        }
                        return chain.proceed(builder.build());
                    }
                }).build();
                Request request = new Request.Builder().url(Mytoken.URl+"/prod-api/api/service/list?serviceType="+serviceTypes[position] )
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "???????????????????????????", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String string = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            final List<Service> services = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Service>>(){
                            }.getType());

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    gridView.setAdapter(new BaseAdapter() {
                                        @Override
                                        public int getCount() {
                                            return services.size();
                                        }

                                        @Override
                                        public Object getItem(int i) {
                                            return null;
                                        }

                                        @Override
                                        public long getItemId(int i) {
                                            return 0;
                                        }

                                        @Override
                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            if (convertView == null){
                                                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tj_item, parent, false);
                                            }
                                            ImageView imgTj = (ImageView) convertView.findViewById(R.id.img_tj);
                                            TextView tvTjText = (TextView) convertView.findViewById(R.id.tv_tj_text);
                                            Glide.with(imgTj).load(Mytoken.URl+services.get(position).getImgUrl()).into(imgTj);
                                            tvTjText.setText(services.get(position).getServiceName());
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                  gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                      //????????????????????????????????????  ListView???????????????
                                                      @Override
                                                      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                          switch (services.get(position).getServiceName()) {
                                                              case "?????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), ParkActivity.class));
                                                                  break;
                                                              case "????????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), SubwayActivity.class));
                                                                  break;
                                                              case "?????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), JobActivity.class));
                                                                  break;
                                                              case "????????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), TrafficActivity.class));
                                                                  break;
                                                              case "????????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), TakeoutActivity.class));
                                                                  break;
                                                              case "?????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), RoomActivity.class));
                                                                  break;
                                                              case "????????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), HospitalActivity.class));
                                                                  break;
                                                              case "????????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), BusActivity.class));
                                                                  break;
                                                              case "????????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), LifeActivity.class));
                                                                  break;
                                                              case "?????????":
                                                                  getActivity().startActivity(new Intent(getActivity(), MovieActivity.class));
                                                                  break;
                                                          }
                                                      }
                                                  });
                                                }
                                            });
                                            return convertView;
                                        }
                                    });
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                return serviceTypes.length;
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(recycleAdapter);
    }

    /*-----------------------------------------------------------------------------------------------*/

    private void initListView() {
        listAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return serviceTypes.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null){
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_textview_item,parent,false);
                }
                TextView textView =(TextView)convertView;
                if (position==0){
                    textView.setBackgroundColor(Color.WHITE);
                }
                textView.setText(serviceTypes[position]);
                return convertView;
            }
        };
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getChildAt(currentIndex).setBackgroundColor(Color.TRANSPARENT);
                //???????????????????????????????????????
                parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                //??????????????????????????????????????????
                currentIndex = position;
                //??????????????????
            }
        });
    }

    private void initView(View getView) {
        listView = (ListView) getView.findViewById(R.id.listView);
        recycleView = (RecyclerView) getView.findViewById(R.id.recycleView);
    }
}