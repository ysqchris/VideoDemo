package com.yusq.videodemo.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.yusq.videodemo.imlp.ILifeCycle;
import com.yusq.videodemo.imlp.IMvpView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/10/18
 * <p>
 * 包 名：com.yusq.videodemo.controler
 * <p>
 * 类 名：MvpControler
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class MvpControler implements ILifeCycle {


    //存放的是P 层的实例
    private Set<ILifeCycle> mILifeCycles = new HashSet<>();


    public Set<ILifeCycle> getPresenter() {
        return mILifeCycles;
    }

    public void savePresenter(ILifeCycle pILifeCycles) {
        mILifeCycles.add(pILifeCycles);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            if(intent  == null){
                intent = new Intent();
            }
            if(getArguments == null){
                getArguments = new Bundle();
            }
            leftCycle.onCreate(savedInstanceState , intent , getArguments);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            if(intent  == null){
                intent = new Intent();
            }
            if(getArguments == null){
                getArguments = new Bundle();
            }
            leftCycle.onActivityCreated(savedInstanceState , intent , getArguments);
        }
    }

    @Override
    public void onStart() {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.onStart();
        }
    }

    @Override
    public void onResume() {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.onResume();
        }
    }

    @Override
    public void onPause() {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.onPause();
        }
    }

    @Override
    public void onStop() {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.onStop();
        }
    }

    @Override
    public void onDestroy() {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.onDestroy();
        }
    }

    @Override
    public void destroyView() {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.destroyView();
        }
    }

    @Override
    public void onViewDestroy() {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.onViewDestroy();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            if(intent == null){
                intent = new Intent();
            }
            leftCycle.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            if(data == null){
                data = new Intent();
            }
            leftCycle.onActivityResult(requestCode , resultCode , data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle pBundle) {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.onSaveInstanceState(pBundle);
        }
    }

    @Override
    public void attachView(IMvpView pIMvpView) {
        Iterator<ILifeCycle> iterator = this.mILifeCycles.iterator();
        while (iterator.hasNext()){
            ILifeCycle leftCycle = iterator.next();
            leftCycle.attachView(pIMvpView);
        }
    }
}
