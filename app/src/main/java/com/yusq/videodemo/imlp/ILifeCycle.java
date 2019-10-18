package com.yusq.videodemo.imlp;

import android.content.Intent;
import android.os.Bundle;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/8/1/16:43
 * <p>
 * 包 名：com.yusq.videodemo.presenter
 * <p>
 * 类 名：
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：p层的生命周期管理接口
 */
public interface ILifeCycle  {

    void onCreate(Bundle savedInstanceState , Intent intent , Bundle getArguments);

    void onActivityCreated(Bundle savedInstanceState , Intent intent , Bundle getArguments);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void destroyView();

    void onViewDestroy();

    void onNewIntent(Intent intent);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onSaveInstanceState(Bundle pBundle);

    void attachView(IMvpView pIMvpView);

}
