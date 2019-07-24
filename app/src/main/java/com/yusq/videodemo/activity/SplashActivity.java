package com.yusq.videodemo.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.yusq.videodemo.R;
import com.yusq.videodemo.utils.CustomCountTimer;

import java.io.File;
import java.net.URI;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/7/24/16:19
 * <p>
 * 包 名：com.yusq.videodemo
 * <p>
 * 类 名：
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class SplashActivity extends AppCompatActivity {

    private VideoView  mVPlay;
    private CustomCountTimer countTimer;
    private TextView mSplashTimeTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashTimeTv = findViewById(R.id.splash_time_tv);
        mSplashTimeTv.setClickable(false);

        mVPlay =  findViewById(R.id.vv_play);
        mVPlay.setVideoURI(Uri.parse("android.resource://"+getPackageName() + File.separator + R.raw.trailer));
        mVPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVPlay.start();
            }
        });

        mVPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVPlay.start();
            }
        });

        countTimer = new CustomCountTimer(5, new CustomCountTimer.ICountDownHandler() {
            @Override
            public void onTitcker(int time) {
                if(mSplashTimeTv != null){
                    mSplashTimeTv.setText(time+"s");

                }
            }

            @Override
            public void onFinish() {
                mSplashTimeTv.setText("跳过");
                mSplashTimeTv.setClickable(true);
                mSplashTimeTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SplashActivity.this , MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
        countTimer.start();

    }

    @Override
    protected void onDestroy() {
        countTimer.cancel();
        countTimer = null;
        super.onDestroy();
    }
}
