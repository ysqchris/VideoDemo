package com.yusq.videodemo.model.shanghai.presenter;

import android.support.v4.app.Fragment;

import com.yusq.videodemo.imlp.ILifeCycle;
import com.yusq.videodemo.imlp.IMvpView;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.model.shanghai.presenter
 * <p>
 * 类 名：IShangHaiDetailActivityConstract
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public interface IShangHaiDetailActivityConstract {

    interface Iview extends IMvpView {


    }

    interface Ipresenter extends ILifeCycle {
        void getNetData();
    }

}
