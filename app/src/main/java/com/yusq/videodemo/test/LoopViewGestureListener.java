// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.yusq.videodemo.test;

import android.view.MotionEvent;

final class LoopViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final WheelPickerView mWheelPickerView;

    LoopViewGestureListener(WheelPickerView loopview) {
        mWheelPickerView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mWheelPickerView.scrollBy(velocityY);
        return true;
    }
}
