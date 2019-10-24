package com.yusq.videodemo.presenter;

import com.yusq.videodemo.imlp.ILifeCycle;
import com.yusq.videodemo.imlp.IMvpView;

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
public abstract  class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCycle {

    protected WeakReference<T> mWeakReference;

    protected LifeCircleMvpPresenter(){}

    public LifeCircleMvpPresenter(IMvpView pIMvpView){
        super();
        attachView(pIMvpView);
        MvpControler mvpControler = pIMvpView.getMvpControler();
        mvpControler.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView pIMvpView) {
          if(mWeakReference == null){
              mWeakReference = new WeakReference(pIMvpView);
          }else {
              T view  =  mWeakReference.get();
              if(view != pIMvpView){
                  mWeakReference = new WeakReference(pIMvpView);
              }
          }
    }


    @Override
    public void onDestroy() {
        mWeakReference.clear();
        mWeakReference = null;
    }

    protected  T getView(){
        T view = mWeakReference != null ? mWeakReference.get() : null;
        if(view == null){
            return  getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();
}
