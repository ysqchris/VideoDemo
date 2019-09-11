package com.yusq.videodemo.test;

public class SmoothScrollTimerTask  implements Runnable{

    int realTotalOffset;
    int realOffset;
    int offset;
    final WheelPickerView pickerView;

    SmoothScrollTimerTask(WheelPickerView pPickerView, int offset) {
        this.pickerView = pPickerView;
        this.offset = offset;
        realTotalOffset = Integer.MAX_VALUE;
        realOffset = 0;
    }

    @Override
    public final void run() {
        if (realTotalOffset == Integer.MAX_VALUE) {
            realTotalOffset = offset;
        }
        realOffset = (int) ((float) realTotalOffset * 0.1F);

        if (realOffset == 0) {
            if (realTotalOffset < 0) {
                realOffset = -1;
            } else {
                realOffset = 1;
            }
        }
        if (Math.abs(realTotalOffset) <= 0) {
            pickerView.cancelFuture();
            pickerView.handler.sendEmptyMessage(MessageHandler.WHAT_ITEM_SELECTED);
        } else {
            pickerView.totalScrollY = pickerView.totalScrollY + realOffset;
            pickerView.handler.sendEmptyMessage(MessageHandler.WHAT_INVALIDATE_LOOP_VIEW);
            realTotalOffset = realTotalOffset - realOffset;
        }
    }

}
