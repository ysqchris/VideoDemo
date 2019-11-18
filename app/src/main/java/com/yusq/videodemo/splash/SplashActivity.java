package com.yusq.videodemo.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.TextView;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseActivity;
import com.yusq.videodemo.model.main.MainActivity;
import com.yusq.videodemo.inject.SetViewInject;
import com.yusq.videodemo.view.FullScreenVideoView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/7/24/16:19
 * <p>
 * 包 名：com.yusq.videodemo
 * <p>
  * 类 名：SplashActivity
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：启动页
 */
@SetViewInject(mainLayoutId = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityConstract.Iview {

    @BindView(R.id.vv_play)
    FullScreenVideoView vvPlay;
    @BindView(R.id.splash_time_tv)
    TextView splashTimeTv;

    private ISplashActivityConstract.Ipresenter  timerPresenter;


    @Override
    public void afterBindView() {
        initTimePresenter();
        inVideoView();
        initListener();
    }

    /**
     * 初始化p层引用
     */
    private void initTimePresenter() {
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimerCount();
    }



    /**
     * 初始化视频播放器
     */

    private void inVideoView() {
        vvPlay.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.trailer));
    }

    /**
     * 初始化监听
     *
     */
    private void initListener() {
        vvPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vvPlay.start();
            }
        });

        vvPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vvPlay.start();
            }
        });

    }


    @OnClick(R.id.splash_time_tv)
    public void onViewClicked() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 设置显示文字 presenter回调
     * @param text
     */
    @Override
    public void setTimerStatusTv(String text) {
        if(splashTimeTv != null){
            splashTimeTv.setText(text);
        }

    }

    /**
     * 设置显示状态 presenter回调
     * @param pStatus
     */
    @Override
    public void setTimerStatus(boolean pStatus) {
        if(splashTimeTv != null){
            splashTimeTv.setClickable(pStatus);
        }
    }

}
