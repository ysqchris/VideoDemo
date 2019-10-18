package com.yusq.videodemo.presenter;

import java.lang.ref.WeakReference;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/10/18
 * <p>
 * 包 名：com.yusq.videodemo.presenter
 * <p>
 * 类 名：LifeCircleMvpPresenter
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public abstract  class LifeCircleMvpPresenter<T extends  IMvpView> implements ILifeCycle{

    protected WeakReference mWeakReference;

    protected LifeCircleMvpPresenter(){

    }

    public LifeCircleMvpPresenter(IMvpView pIMvpView){
        super();
        attachView(pIMvpView);
    }

    @Override
    public void attachView(IMvpView pIMvpView) {
          if(mWeakReference == null){
              mWeakReference = new WeakReference(pIMvpView);
          }else {
              T view  = (T) mWeakReference.get();
          }
    }
}
