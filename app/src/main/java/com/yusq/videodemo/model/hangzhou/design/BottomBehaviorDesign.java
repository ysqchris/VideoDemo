package com.yusq.videodemo.model.hangzhou.design;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.yusq.videodemo.utils.BottomTextAnim;

public class BottomBehaviorDesign  extends CoordinatorLayout.Behavior<TextView> {

    public BottomBehaviorDesign() {
    }

    public BottomBehaviorDesign(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 即将发生嵌套滚动式，用于判断滑动方向
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull TextView child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target,
                                       int axes) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * 发生嵌套滚动的操作
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     * @param type
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        // 向下滑动
        if(dyConsumed + dyUnconsumed > 0){
            if(child.getVisibility() == View.VISIBLE)
            BottomTextAnim.hideAnim(child);
        }else {
            // 向上滑动
            if(child.getVisibility() != View.VISIBLE)
            BottomTextAnim.showAnim(child);
        }
    }
}
