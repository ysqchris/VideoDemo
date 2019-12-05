package com.yusq.videodemo.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yusq.videodemo.R;

public class LikeClickView extends View {

    private  boolean isLike;
    private Bitmap iBitmap;
    private Bitmap backBitmap;
    private Paint paint;

    public LikeClickView(Context context) {
        super(context);
        init();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LikeClickView);
        isLike = typedArray.getBoolean(R.styleable.LikeClickView_is_like , false);
        typedArray.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        Resources resources = getResources();
        iBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);
        backBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_midea_yjkm_baner);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = 0;
        int measureHeight = 0;
        // 显示的最大的宽高
        int maxHeight = backBitmap.getHeight() + getContext().getResources().getDimensionPixelSize(R.dimen.x20);
        int maxWidth = backBitmap.getWidth() + getContext().getResources().getDimensionPixelSize(R.dimen.x30);
        // 当前View的测量模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        // 当前View的设置的宽高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 费精确宽高的模式下
        if(mode != MeasureSpec.EXACTLY){
           // 测量模式未指定的模式下， 如果背景有多大，空间就设置多大
            int miniWidth = getSuggestedMinimumWidth();
            int miniHeight = getSuggestedMinimumHeight();
            if(miniWidth == 0){
                measureWidth = maxWidth;
            }else {
                measureWidth = Math.min(miniWidth,maxHeight);
            }
            if(miniHeight == 0){
                measureHeight = maxHeight;
            }else {
                measureHeight = Math.min(miniHeight,maxHeight);
            }
        }else { // 精确宽高的模式下
            measureWidth = Math.min(widthSize,maxWidth);
            measureHeight = Math.min(heightSize,maxHeight);
        }
        setMeasuredDimension(measureWidth,measureHeight);
       // super.onMeasure(widthMeasureSpec ,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int bitMapWidth = backBitmap.getWidth();
        int bitMapHeight = backBitmap.getHeight();
        int left = (width-bitMapWidth)/2;
        int top = (height-bitMapHeight)/2;
        if(iBitmap != null && paint != null) {
            if(isLike) {
                canvas.drawBitmap(iBitmap, left, top, paint);
            }else {
                canvas.drawBitmap(backBitmap, left, top, paint);
            }
        }
    }


    public void ChangeIsLike() {
        isLike = !isLike;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    /**
     * 当view 销毁的时候需要 回收清理一下 对象
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        backBitmap.recycle();
        iBitmap.recycle();
    }
}
