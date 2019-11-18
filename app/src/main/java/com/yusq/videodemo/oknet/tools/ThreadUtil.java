package com.yusq.videodemo.oknet.tools;


import android.os.Handler;
import android.os.Looper;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.tools
 * <p>
 * 类 名：ThreadUtil
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ThreadUtil {

    //主线程的handler
    private final static Handler MAIN = new Handler(Looper.getMainLooper());


    public static void postMainThread(Runnable pRunnable){
        MAIN.post(new Runnable() {
            @Override
            public void run() {
                try{
                    pRunnable.run();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static int getCoreCpuNum() {
        return  Runtime.getRuntime().availableProcessors();
    }
}
