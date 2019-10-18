package com.yusq.videodemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yusq.videodemo.controler.LifeCircleMvpActivity;
import com.yusq.videodemo.inject.SetViewInject;

import butterknife.ButterKnife;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/7/25/15:10
 * <p>
 * 包 名：com.yusq.videodemo.activity
 * <p>
 * 类 名：
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */

public class BaseActivity extends LifeCircleMvpActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetViewInject annotation = this.getClass().getAnnotation(SetViewInject.class);
        if(annotation != null){
            int mainLayoutId = annotation.mainLayoutId();
            if(mainLayoutId > 0){
                setContentView(mainLayoutId);
                ButterKnife.bind(this);
            }else{
                throw new RuntimeException("mainLayoutId is null");
            }
        }else{
            throw new RuntimeException("annotation is null");
        }
    }
}
