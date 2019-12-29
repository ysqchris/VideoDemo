package com.yusq.videodemo.view.refresh;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 *
 */
public class BaseRefreshLayout extends LinearLayout {

    BaseRefreshManager refreshManager;
    private View mHeadView;
    private int mHeadViewHeiget;


    public BaseRefreshLayout(Context context) {
        super(context);
        initView();
    }

    public BaseRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public BaseRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
    }
    /**
     * 开启下拉刷新 默认下拉视图效果
     */
    public void setRefreshManager(){
        refreshManager = new DefaultRefreshManager(getContext());
        initHeadView();
    }

    /**
     * 开启下来刷新 使用用户自定义的下拉效果
     * @param baseRefreshManager
     */
    public void setRefreshManager(BaseRefreshManager baseRefreshManager){
        refreshManager = baseRefreshManager;
        initHeadView();
    }

    private void initHeadView() {
        setOrientation(VERTICAL);
        mHeadView = refreshManager.getRefreshHeadView();
        mHeadView.measure(0 , 0);
        mHeadViewHeiget = mHeadView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , mHeadViewHeiget);
        params.topMargin = -mHeadViewHeiget;
        addView(mHeadView,0,params);
    }

    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }
}
