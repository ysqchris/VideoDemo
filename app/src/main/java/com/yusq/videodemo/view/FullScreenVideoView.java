package com.yusq.videodemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/7/24/17:00
 * <p>
 * 包 名：com.yusq.videodemo.view
 * <p>
 * 类 名：
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class FullScreenVideoView extends VideoView {


    /**
     * 用于直接创建视图对象
     * @param context
     */
    public FullScreenVideoView(Context context) {
        super(context);
    }

    /**
     * 用户定义到xml文件中控件的初始化 ，支持自定义属性
     * @param context
     * @param attrs 样式
     */
    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 用户定义到xml文件中控件的初始化 ，支持自定义属性
     * @param context
     * @param attrs 属性
     * @param defStyleAttr 样式
     */
    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0 , widthMeasureSpec);
        int height  = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width , height);

    }
}
