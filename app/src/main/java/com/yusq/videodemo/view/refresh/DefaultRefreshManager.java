package com.yusq.videodemo.view.refresh;

import android.content.Context;
import android.view.View;

import com.yusq.videodemo.R;

public class DefaultRefreshManager extends BaseRefreshManager {

    public DefaultRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getRefreshHeadView() {
        return  layoutInflater.inflate(R.layout.default_refresh_head, null, false);
    }
}
