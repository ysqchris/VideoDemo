// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.yusq.videodemo.test.wheelpicker;


final class InertiaTimerTask implements Runnable {

    float a;
    final float velocityY;
    final WheelPickerView mWheelPickerView;

    InertiaTimerTask(WheelPickerView pWheelPickerView, float velocityY) {
        super();
        mWheelPickerView = pWheelPickerView;
        this.velocityY = velocityY;
        a = Integer.MAX_VALUE;
    }

    @Override
    public final void run() {
        if (a == Integer.MAX_VALUE) {
            if (Math.abs(velocityY) > 2000F) {
                if (velocityY > 0.0F) {
                    a = 2000F;
                } else {
                    a = -2000F;
                }
            } else {
                a = velocityY;
            }
        }
        if (Math.abs(a) >= 0.0F && Math.abs(a) <= 20F) {
            mWheelPickerView.cancelFuture();
            mWheelPickerView.handler.sendEmptyMessage(MessageHandler.WHAT_SMOOTH_SCROLL);
            return;
        }
        int i = (int) ((a * 10F) / 1000F);
        WheelPickerView pickerView = mWheelPickerView;
        pickerView.totalScrollY = pickerView.totalScrollY - i;
        if (!pickerView.isLoopScollr) {
            float itemHeight = pickerView.lineSpacingMultiplier * pickerView.itemTxtHeight;
            if (pickerView.totalScrollY <= (int) ((float) (-pickerView.initPosition) * itemHeight)) {
                a = 40F;
                pickerView.totalScrollY = (int) ((float) (-pickerView.initPosition) * itemHeight);
            } else if (pickerView.totalScrollY >= (int) ((float) (pickerView.items.size() - 1 - pickerView.initPosition) * itemHeight)) {
                pickerView.totalScrollY = (int) ((float) (pickerView.items.size() - 1 - pickerView.initPosition) * itemHeight);
                a = -40F;
            }
        }
        if (a < 0.0F) {
            a = a + 20F;
        } else {
            a = a - 20F;
        }
        pickerView.handler.sendEmptyMessage(MessageHandler.WHAT_INVALIDATE_LOOP_VIEW);
    }
}
