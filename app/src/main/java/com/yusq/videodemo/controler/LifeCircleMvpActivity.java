package com.yusq.videodemo.controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yusq.videodemo.imlp.IMvpView;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/10/18
 * <p>
 * 包 名：com.yusq.videodemo.presenter
 * <p>
 * 类 名：LifeCircleMvpActivity
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class LifeCircleMvpActivity extends AppCompatActivity implements IMvpView {

    private MvpControler mvpControler;

    @Override
    public MvpControler getMvpControler() {
        if(this.mvpControler == null){
            this.mvpControler = new MvpControler();
        }
        return mvpControler;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent == null){
            intent = new Intent();
        }
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onCreate(savedInstanceState , intent , null);
            mvpControler.onActivityCreated(savedInstanceState , intent , null);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onStart();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onNewIntent(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onSaveInstanceState(outState);
        }
    }

}
