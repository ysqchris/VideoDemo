package com.yusq.videodemo.presenter;

import android.util.Log;

import com.yusq.videodemo.activity.SplashActivity;
import com.yusq.videodemo.controler.BaseAbstractLifePresenter;
import com.yusq.videodemo.imlp.IMvpView;
import com.yusq.videodemo.imlp.ISpashActivityConstract;
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
public class SplashTimerPresenter  extends  BaseAbstractLifePresenter<ISpashActivityConstract.Iview> implements ISpashActivityConstract.Ipresenter {

    private static final String TAG = "CHRIS";

    private CustomCountTimer countTimer;

    public SplashTimerPresenter(ISpashActivityConstract.Iview pIMvpView) {
        super(pIMvpView);
    }

    /**
     * 初始化计时器
     */
    @Override
    public void initTimerCount() {
        countTimer = new CustomCountTimer(5, new CustomCountTimer.ICountDownHandler() {
            @Override
            public void onTitcker(int time) {
                getView().setTimerStatusTv(String.valueOf(time));
            }

            @Override
            public void onFinish() {
                getView().setTimerStatusTv("跳过");
                getView().setTimerStatus(true);
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

    @Override
    protected ISpashActivityConstract.Iview getEmptyView() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: = SplashTimerPresenter");
        cancelTime();
    }
}
