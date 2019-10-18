package com.yusq.videodemo.presenter;

import com.yusq.videodemo.activity.SplashActivity;
import com.yusq.videodemo.utils.CustomCountTimer;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/10/18
 * <p>
 * 包 名：com.yusq.videodemo.presenter
 * <p>
 * 类 名：SplashTimerPresenter
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class SplashTimerPresenter {

    private CustomCountTimer countTimer;
    private SplashActivity  mActivity;

    public SplashTimerPresenter(SplashActivity pActivity) {
        mActivity = pActivity;
    }

    /**
     * 初始化计时器
     */
    public void initTimerCount() {
        countTimer = new CustomCountTimer(5, new CustomCountTimer.ICountDownHandler() {
            @Override
            public void onTitcker(int time) {
                mActivity.setTimerStatusTv(String.valueOf(time));
            }

            @Override
            public void onFinish() {
                mActivity.setTimerStatusTv("跳过");
                mActivity.setTimerStatus(true);
            }
        });
        countTimer.start();
    }

    public void cancelTime(){
        if(countTimer != null) {
            countTimer.cancel();
            countTimer = null;
        }
    }

}
