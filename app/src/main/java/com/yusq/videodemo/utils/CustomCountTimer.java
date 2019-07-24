package com.yusq.videodemo.utils;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/7/24/17:49
 * <p>
 * 包 名：com.yusq.videodemo.utils
 * <p>
 * 类 名：
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：1. 实时设置Activity的倒计时间  2. 支持设置总时间
 */
public class CustomCountTimer implements  Runnable {

    private int mCount;
    private int mTimeVaule;
    private ICountDownHandler mCountDownHandler;
    private boolean isRun;
    Handler handler;

    public CustomCountTimer(int timeCount , ICountDownHandler  countDownHandler) {
             handler = new Handler();
             mCount = mTimeVaule =  timeCount;
             mCountDownHandler = countDownHandler;
    }


    @Override
    public void run() {
        if(isRun){
             if(mCountDownHandler  != null){
                 mCountDownHandler.onTitcker(mCount);
             }
             if(mCount == 0  && mCountDownHandler != null){
                 mCountDownHandler.onFinish();
             }else{
                 mCount = mTimeVaule--;
             }
            handler.postDelayed(this, 1000);
        }
    }


    public void start(){
        isRun  = true;
        handler.post(this);
    }

    public void cancel(){
        isRun = false ;
        handler.removeCallbacks(this);
    }

    public interface  ICountDownHandler{
        void onTitcker(int time);
        void onFinish();
   }

}
