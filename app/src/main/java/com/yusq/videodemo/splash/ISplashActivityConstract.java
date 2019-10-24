package com.yusq.videodemo.splash;

import com.yusq.videodemo.imlp.ILifeCycle;
import com.yusq.videodemo.imlp.IMvpView;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/10/18
 * <p>
 * 包 名：com.yusq.videodemo.imlp
 * <p>
 * 类 名：ISplashActivityConstract
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public interface ISplashActivityConstract {

    interface Iview extends IMvpView {
          void setTimerStatusTv(String pTvtimer);
          void setTimerStatus(boolean pStatusTv);
    }

    interface Ipresenter extends ILifeCycle {
          void initTimerCount();
    }

}
