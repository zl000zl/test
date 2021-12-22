package com.example.newtext;

import android.app.Application;

public class Mytoken extends Application {
    public static String token = null;
    //绑定token请头的值方便后面的方法请求头
//    public static String URl ="http://192.168.1.103:10001";
    public static String URl ="http://124.93.196.45:10001";
    //绑定公共的静态方法方便后面使用
    private static long lastClickTime;

    public static boolean isFastDoubleClick(){
        long time =System.currentTimeMillis();
        long timeD = time-lastClickTime;

        if (0<timeD && timeD<800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
