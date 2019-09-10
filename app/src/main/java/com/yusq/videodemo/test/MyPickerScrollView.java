package com.yusq.videodemo.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yusq.videodemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyPickerScrollView extends View {

    private Paint mTextPaint;
    private List<String> mListData;
    private onSelectListener mSelectListener;


    private int mViewHeight;
    private int mViewWidth;

    //text之间间距和minTextSize之比
    public static final float MARGIN_ALPHA = 2.8f;

    //默认的最大和最小字体 （中间的字体比较大 上下的字体比较小）
    private float mMaxTextSize = 16;
    private float mMinTextSize = 8;

    // 透明度
    private float mMaxTextAlpha = 255;
    private float mMinTextAlpha = 120;

    // 滑动距离
    private float mMoveLen = 0;

    // 是否已经初始化
    private boolean isInit = false;

    //选中的位置，这个位置是mDataList的中心位置，一直不变
    private int mCurrentSelected;

    private float mLastDownY;

    //自动回滚到中间的速度
    public static final float SPEED = 2;


    private Timer timer;
    private MyTimerTask mTask;



    Handler updateHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (Math.abs(mMoveLen) < SPEED) {
                mMoveLen = 0;
                if (mTask != null) {
                    mTask.cancel();
                    mTask = null;
                    performSelect();
                }
            } else
                // 这里mMoveLen / Math.abs(mMoveLen)是为了保有mMoveLen的正负号，以实现上滚或下滚
                mMoveLen = mMoveLen - mMoveLen / Math.abs(mMoveLen) * SPEED;
            invalidate();
        }

    };


    public MyPickerScrollView(Context context) {
        super(context);
        initView();
    }

    public MyPickerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyPickerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    /**
     * 初始化
     */
    private void  initView(){
       timer = new Timer();
       mListData = new ArrayList<>();
       mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       mTextPaint.setTextSize(R.dimen.dp_30);
       mTextPaint.setStyle(Paint.Style.FILL);
       mTextPaint.setTextAlign(Paint.Align.CENTER);
       mTextPaint.setColor(getResources().getColor(R.color.black));
   }


    /**
     * 添加数据
     * @param showData
     */
   public void setListDate(List<String> showData){
        if(mListData == null) {
            mListData = showData;
        }else {
            mListData.addAll(showData);
        }
       mCurrentSelected = mListData.size() / 2;
       invalidate();
   }

    public void setOnSelectListener(onSelectListener listener) {
        mSelectListener = listener;
    }


    private void performSelect() {
        if (mSelectListener != null)
            mSelectListener.onSelect(mListData.get(mCurrentSelected));
    }

    /**
     * 选择选中的item的index
     *
     * @param selected
     */
    public void setSelected(int selected) {
        mCurrentSelected = selected;
        int distance = mListData.size() / 2 - mCurrentSelected;
        if (distance < 0)
            for (int i = 0; i < -distance; i++) {
                moveHeadToTail();
                mCurrentSelected--;
            }
        else if (distance > 0)
            for (int i = 0; i < distance; i++) {
                moveTailToHead();
                mCurrentSelected++;
            }
        invalidate();
    }

    /**
     * 选择选中的内容
     *
     * @param mSelectItem
     */
    public void setSelected(String mSelectItem) {
        for (int i = 0; i < mListData.size(); i++)
            if (mListData.get(i).equals(mSelectItem)) {
                setSelected(i);
                break;
            }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight();
        mViewWidth = getMeasuredWidth();
        // 按照View的高度计算字体大小
        mMaxTextSize = mViewHeight / 8.0f;
        mMinTextSize = mMaxTextSize / 2f;
        isInit = true;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 根据index绘制view
        if (isInit)
            drawData(canvas);
    }


    private void drawData(Canvas canvas){
        drawCenterSelectedText(canvas);
        // 绘制上方data
        for (int i = 1; (mCurrentSelected - i) >= 0; i++) {
            drawOtherText(canvas, i, -1);
        }
        // 绘制下方data
        for (int i = 1; (mCurrentSelected + i) < mListData.size(); i++) {
            drawOtherText(canvas, i, 1);
        }


    }

    /**
     * 绘制选中的数据
     * @param canvas
     */
    private  void  drawCenterSelectedText(Canvas canvas){
        // 先绘制选中的text再往上往下绘制其余的text
        float scale = parabola(mViewHeight / 4.0f, mMoveLen);

        float size = (mMaxTextSize - mMinTextSize) * scale + mMinTextSize;
        mTextPaint.setTextSize(size);
        mTextPaint.setAlpha((int) ((mMaxTextAlpha - mMinTextAlpha) * scale + mMinTextAlpha));

        // text居中绘制，注意baseline的计算才能达到居中，y值是text中心坐标
        float x = (float) (mViewWidth / 2.0);
        float y = (float) (mViewHeight / 2.0 + mMoveLen);
        Paint.FontMetricsInt fmi = mTextPaint.getFontMetricsInt();
        float baseline = (float) (y - (fmi.bottom / 2.0 + fmi.top / 2.0));

        String textData = mListData.get(mCurrentSelected);
        canvas.drawText(textData, x, baseline, mTextPaint);
    }

    /**
     * 绘制其他的数据
     */

    /**
     * @param canvas
     * @param position 距离mCurrentSelected的差值
     * @param type 1表示向下绘制，-1表示向上绘制
     */
    private void drawOtherText(Canvas canvas, int position, int type) {
        float d = (float) (MARGIN_ALPHA * mMinTextSize * position + type * mMoveLen);
        float scale = parabola(mViewHeight / 4.0f, d);
        float size = (mMaxTextSize - mMinTextSize) * scale + mMinTextSize;
        mTextPaint.setTextSize(size);
        mTextPaint.setAlpha((int) ((mMaxTextAlpha - mMinTextAlpha) * scale + mMinTextAlpha));
        float y = (float) (mViewHeight / 2.0 + type * d);
        Paint.FontMetricsInt fmi = mTextPaint.getFontMetricsInt();
        float baseline = (float) (y - (fmi.bottom / 2.0 + fmi.top / 2.0));

        int indexs = mCurrentSelected + type * position;
        String textData = mListData.get(indexs);
        canvas.drawText(textData, (float) (mViewWidth / 2.0), baseline, mTextPaint);
    }

    /**
     * 抛物线
     * @param zero 零点坐标
     * @param x 偏移量
     * @return scale
     */
    private float parabola(float zero, float x) {
        float f = (float) (1 - Math.pow(x / zero, 2));
        return f < 0 ? 0 : f;
    }

    /**
     *
     * touch事件 监听
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                doDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                doMove(event);
                break;
            case MotionEvent.ACTION_UP:
                doUp(event);
                break;
        }
        return true;
    }


    private void doDown(MotionEvent event) {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        mLastDownY = event.getY();
    }




    private void doMove(MotionEvent event) {
        mMoveLen += (event.getY() - mLastDownY);
        if (mMoveLen > MARGIN_ALPHA * mMinTextSize / 2) {
            // 往下滑超过离开距离
            moveTailToHead();
            mMoveLen = mMoveLen - MARGIN_ALPHA * mMinTextSize;
        } else if (mMoveLen < -MARGIN_ALPHA * mMinTextSize / 2) {
            // 往上滑超过离开距离
            moveHeadToTail();
            mMoveLen = mMoveLen + MARGIN_ALPHA * mMinTextSize;
        }

        mLastDownY = event.getY();
        invalidate();
    }

    private void doUp(MotionEvent event) {
        // 抬起手后mCurrentSelected的位置由当前位置move到中间选中位置
        if (Math.abs(mMoveLen) < 0.0001) {
            mMoveLen = 0;
            return;
        }
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        mTask = new MyTimerTask(updateHandler);
        timer.schedule(mTask, 0, 10);
    }

    /**
     * 把移除去的数据放到尾部
     */
    private void moveHeadToTail() {
        if(mListData != null  && mListData.size() > 0) {
            String head = mListData.get(0);
            mListData.remove(0);
            mListData.add(head);
        }
    }

    /**
     * 把移除去的底部数据加到顶部
     */
    private void moveTailToHead() {
        if(mListData != null  && mListData.size() > 0) {
            String tail = mListData.get(mListData.size() - 1);
            mListData.remove(mListData.size() - 1);
            mListData.add(0, tail);
        }
    }


    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }

    }

    public interface onSelectListener {
        void onSelect(String pickers);
    }


}
