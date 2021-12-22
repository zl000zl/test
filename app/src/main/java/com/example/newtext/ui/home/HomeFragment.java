package com.example.newtext.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtext.Network;
import com.example.newtext.NewClass.Todaynews;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.FwBean;
import com.example.newtext.StringBean.NewType;
import com.example.newtext.StringBean.Notific;
import com.example.newtext.StringBean.Press;
import com.example.newtext.ui.home.SearchView.SearchViewActivity;
import com.example.newtext.Mytoken;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Banner mbanner;
    //首页轮播图控件定义

    private RecyclerView rv_grid_home, rv_grid_show, rv_grid_fuwu;
    //首页专题、推荐要闻、服务的RecycleView控件的定义

    private SearchView searchView;
    //首页搜索框的定义

    public static String search;
    //定义一个公共方法用来给搜索框中输入的字符与Press里的Title相比较，匹配就显示出来

    public List<Press> mpress = new ArrayList<>();
    //定义一个集合,里面放着新闻详情页请求的数据,用于点击轮播图跳转对应的相关内容


    private TabLayout tabLayout;

    //今日要闻TabLayout控件的定义
    /*-------------------------------------------------------------------------------------------------------------*/
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }  //固定写法

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);//固定写法内部逻辑

        tabLayout = view.findViewById(R.id.mtablayout);  //这是TabLayout方法的调用控件界面

        initSearchView();   //搜索框的事件处理
        initspecial();      //专题一栏的事件处理
        initgirde();        //新闻专题列表的事件处理
        initfuwu();         //推荐服务的事件处理
        initTabline();      //给TabLayout设置分割线
        /*定义方法名 来实现下面的逻辑判断*/
        /*-------------------------------------------------------------------------------------------------------------*/

                                            /*get请求轮播图的图片内容*/
        Network.doGet("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", new OkResult() {

            @Override
            public void succes(JSONObject jsonObject) {
                List<Notific> notifics = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Notific>>() {
                }.getType());
                //这是请求成功的方法,解析数据 为下面的逻辑判断
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mbanner = getView().findViewById(R.id.mbanner);
                        //调用banner界面布局
                        mbanner.setImages(notifics).setImageLoader(new ImageLoader() {
                            //所有设置参数方法都放在此方法之前执行
                            //setImages是设置轮播图片(参数是Bean类) 然后是设置图片加载器ImageLoader
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                //重写图片加载器的方法
                                Notific notific = (Notific) path;
                                //返回的图片路径是List<Notific>集合 用于下面的加载图片的绑定
                                Glide.with(imageView).load(Mytoken.URl + notific.getAdvImg()).into(imageView);
                                //加载图片的方法
                            }
                        });
                        banner_news_init();
                        //这个方法的定义是下面的点击轮播图跳转事件 get请求的数据 新闻详情页的传值

                        mbanner.setOnBannerListener(new OnBannerListener() {
                            //banner的点击事件 下标从0开始
                            @Override
                            public void OnBannerClick(int position) {
                                switch (position) {
                                    //switch的判断方法执行点击跳转每一张图片对应的新闻详情
                                    case 0:
                                    case 1:
                                    case 2:
                                        Todaynews.press = mpress.get(position);
                                        getActivity().startActivity(new Intent(getActivity(), Todaynews.class));
                                        break;
                                        //一定记得加break
                                }
                            }
                        });
                        mbanner.start();
                        //开启banner的轮播
                    }
                });
            }
        });
    }

    private void banner_news_init() {
        //这是banner轮播图点击进入新闻详情页的集合请求
        Network.doGet("/prod-api/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Press> presses = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Press>>() {
                }.getType());
                for (int i = 0; i < 3; i++) {
                    mpress.add(presses.get(i));
                    //一共有三张图片 所以循环三次
                }
            }
        });
    }
    /*-------------------------------------------------------------------------------------------------------*/

                                            /*搜素框的事件处理*/
    private void initSearchView() {
        searchView = getView().findViewById(R.id.searchView);
        //调用搜索框的界面布局
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        //将回车键设为点击事件
        searchView.setQueryHint("搜索");
        //显示提示内容
        searchView.setSubmitButtonEnabled(true);
        //设置文本框展开时是否显示提交按钮
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //搜索框里的点击事件
            @Override
            public boolean onQueryTextSubmit(String query) {
                search = query;
                //上面定义了一个公用的方法来相对比较 而query就是输入框里输入的内容等于Press里的Title
                if (Mytoken.isFastDoubleClick()) {
                    //这里是设置防止第二次点击事件,防止重复点击事件
                    return false;
                } else {
                    startActivity(new Intent(getActivity(), SearchViewActivity.class));
                    //输入搜索的内容点击跳转到内容详情界面(再点击进入新闻详情页面)
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //内容改变时触发这个方法
                return false;
            }
        });
    }
    /*-------------------------------------------------------------------------------------------------------*/

                                    /*推荐服务的请求事件处理*/
    private void initfuwu() {
        Network.doGet("/prod-api/api/service/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<FwBean> fwBeans = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<FwBean>>() {
                }.getType());
                rv_grid_fuwu = (RecyclerView) getView().findViewById(R.id.rv_grid_fuwu);
                //调用RecycleView界面的布局
                rv_grid_fuwu.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                //样式是网格布局一行5个
                rv_grid_fuwu.setAdapter(new fuwuAdapter(getActivity(), fwBeans));
                //绑定适配器
            }
        });
    }
    /*-------------------------------------------------------------------------------------------------------*/

                                    /*TabLayout新闻列表的请求事件处理方法*/
    private void initTabline() {
        //给TabLayout中间设置分割线
        tabLayout = (TabLayout) getView().findViewById(R.id.mtablayout);
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(25);  //分割线的长度
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.rv_line)); }

    private void initgirde() {
        //TabLayout新闻专题列表的请求方法
        rv_grid_show = (RecyclerView) getView().findViewById(R.id.rv_grid_show);
        //因为要用到RecycleView 所以调用RecycleView的布局界面
        Network.doGet("/prod-api/press/category/list", new OkResult() {
            //get请求到TabLayout专题列表的数据是新闻分类
            @Override
            public void succes(JSONObject jsonObject) {
                List<NewType> newTypes = new Gson().fromJson(jsonObject.optString("data"), new TypeToken<List<NewType>>() {
                }.getType());    //得到想要的专题列表的新闻集合数据(标题、图片、内容)

                for (NewType newType : newTypes) {
                    //for(类型 变量名:集合) 为了循环取出专题列表里的每个名字
                    tabLayout.addTab(tabLayout.newTab().setText(newType.getName()).setTag(newType.getId()));
                    //添加新的Tab里面放上刚取出的专题类表的值  缓存变量中专题类表的id
                }
                loadData(newTypes.get(0).getId());
                //这个方法是决定从专题列表中取出每一个专题的id  从下标0开始
                //(id值由下面的点击事件中你点击那一个专题栏 就传入哪一个专题的id转换成字符串形式带入到RecycleView的适配器中)
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //TabLayout的点击事件的方法
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String strId = tab.getTag().toString();
                //定义每个新闻专题的id类别装换为toString类型
                loadData(strId);
                //把strId的值带到loadData方法中去
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
                                /*这里是TabLayout总的新闻专题的新闻详情的请求*/
    private void loadData(Object strId) {
        Network.doGet("/prod-api/press/press/list?type=" + strId, new OkResult() {
            //get请求获取新闻列表的type(是新闻列表的id) 带上上面请求到的新闻类别的id值
            @Override
            public void succes(JSONObject jsonObject) {
                List<Press> presses = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Press>>() {
                }.getType());  //得到总的新闻集合(标题、图片、集合)
                rv_grid_show.setLayoutManager(new LinearLayoutManager/*线性布局管理器，支持水平方向和垂直方法*/(getActivity()));
                //设置布局管理器
                rv_grid_show.setAdapter(new SpecialAdapter(getActivity(), presses));
                //给RecycleView设置Adapter绑定数据 并把集合的值传入到适配器中
            }
        });
    }
    /*-------------------------------------------------------------------------------------------------------*/

                                        /*专题一栏的请求方法*/
    private void initspecial() {
        rv_grid_home = (RecyclerView) getView().findViewById(R.id.rv_grid_home);
        Network.doGet("/prod-api/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<Press> presses = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Press>>() {
                }.getType());
                rv_grid_home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rv_grid_home.setAdapter(new StoreListAdapter(presses, getActivity()));
            }
        });
    }
}