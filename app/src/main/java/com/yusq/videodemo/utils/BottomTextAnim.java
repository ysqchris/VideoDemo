package com.yusq.videodemo.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yusq.videodemo.R;

public class BottomTextAnim {

    public static  void  showAnim(View showView){
        showView.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(showView.getContext() , R.anim.bottom_show_anim);
        showView.startAnimation(animation);
        showView.setVisibility(View.VISIBLE);
        showView.setClickable(true);
    }


    public static  void  hideAnim(View hideView){
        hideView.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(hideView.getContext() , R.anim.bottom_hide_anim);
        hideView.startAnimation(animation);
        hideView.setVisibility(View.INVISIBLE);
        hideView.setClickable(false);

    }


}
