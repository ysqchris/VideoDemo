package com.yusq.videodemo.view.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseRefreshManager{

    public LayoutInflater layoutInflater;

    public BaseRefreshManager(Context context) {
      layoutInflater =   LayoutInflater.from(context);
    }

    public abstract  View  getRefreshHeadView();

}
