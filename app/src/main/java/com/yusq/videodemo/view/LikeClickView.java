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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(iBitmap != null && paint != null) {
            if(isLike) {
                canvas.drawBitmap(iBitmap, 100, 200, paint);
            }else {
                canvas.drawBitmap(backBitmap, 100, 200, paint);
            }
        }
    }


    public void ChangeIsLike() {
        isLike = !isLike;
        invalidate();
    }
}
