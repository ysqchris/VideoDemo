package com.yusq.videodemo.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.yusq.videodemo.imlp.IMvpView;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/10/18
 * <p>
 * 包 名：com.yusq.videodemo.presenter
 * <p>
 * 类 名：BaseAbstractLifePresenter
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：p层的中间抽象类 平层的其他类继承自该类
 */
public abstract class BaseAbstractLifePresenter<T extends IMvpView> extends LifeCircleMvpPresenter<T> {

    public BaseAbstractLifePresenter() {
    }

    public BaseAbstractLifePresenter(IMvpView pIMvpView) {
        super(pIMvpView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle pBundle) {

    }
}
