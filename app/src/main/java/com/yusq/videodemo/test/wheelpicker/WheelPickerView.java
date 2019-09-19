package com.yusq.videodemo.test.wheelpicker;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.yusq.videodemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class WheelPickerView  extends View {
    // 默认文字大小
    private static final int DEFAULT_TEXT_SIZE = (int) (Resources.getSystem().getDisplayMetrics().density * 15);
    //行间隙
    private static final float DEFAULT_LINE_SPACE = 1f;
    //默认显示行数
    private static final int DEFAULT_VISIBIE_ITEMS = 9;

    private Context mContext;
    // X轴拉伸参数
    private float scaleX = 1.05F;
    // 画笔 选中文字画笔
    private Paint  mSelectTxtPaint;
    // 未选中画笔
    private Paint mDefaultTxtPaint ;
    // 分割线画笔
    private  Paint mIndicatorLinePaint;

    // 选中文字护画笔的颜色
    private int mSelectTxtColor;
    // 未选中文字的画笔颜色
    private int  mDefaultTxtColor;
    // 分割线的颜色
    private int mIndicatorLineColor;
    //画笔类型
    private Typeface typeface = Typeface.MONOSPACE;
    //显示文字大小
    private int textSize;

    // 测量的视图宽高
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    // 内部左右边距
    private int mPaddingLeft;
    private int mPaddingRight;

    //文本的高度
    private int textHeight;

    private Rect tempRect = new Rect();

    // 仿IOS的滚筒效果 圆半径
    private int halfCircumference;
    private int radius;

    // 每一行的间距
    float lineSpacingMultiplier;
    // 每一行高度
    int itemTxtHeight;
    // 显示的行数
    private int itemsVisibleCount;

    // 选中的Item 上边分割线高度Y
    private int firstLineY;
    // 选中的Item 下边分割线高度Y
    private int secondLineY;

    // 初始化显示位置
    int initPosition;
    // 当前选中的位置
    private int preCurrentIndex;
    // 选中的位置
    private int selectedItem;
    // 是否循环滑动
    boolean isLoopScollr;

    // 需要展示的数据源
    List<IndexString> items;
    //绑定每个位置需要显示的数据
    private HashMap<Integer,IndexString> drawingStrings;

    // Y轴滑动距离
    public float totalScrollY;
    // 改变的位置
    //private int changeY;

    //滑动的手势监听
    private GestureDetector flingGestureDetector;
    OnItemSelectedListener onItemSelectedListener;
    Handler handler;

    // 透明度的尺度单位
    private int  alphaScale = 50;

    public WheelPickerView(Context context) {
        super(context);
        initWheelPickerView(context , null);
    }

    public WheelPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWheelPickerView(context ,attrs);
    }

    public WheelPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWheelPickerView(context , attrs);
    }

    /**
     * 初始化选择框
     */
    private void initWheelPickerView(Context pContext , AttributeSet pAttributeSet){
        mContext = pContext;
        handler = new MessageHandler(this);
        flingGestureDetector = new GestureDetector(mContext, new LoopViewGestureListener(this));
        flingGestureDetector.setIsLongpressEnabled(false);
        TypedArray typedArray = mContext.obtainStyledAttributes(pAttributeSet, R.styleable.WheelPickerView);
        if (typedArray != null) {
            textSize = typedArray.getInteger(R.styleable.WheelPickerView_awv_textsize, DEFAULT_TEXT_SIZE);
            textSize = (int) (Resources.getSystem().getDisplayMetrics().density * textSize);
            lineSpacingMultiplier = typedArray.getFloat(R.styleable.WheelPickerView_awv_lineSpace, DEFAULT_LINE_SPACE);
            mSelectTxtColor = typedArray.getInteger(R.styleable.WheelPickerView_awv_centerTextColor, 0xff313131);
            mDefaultTxtColor = typedArray.getInteger(R.styleable.WheelPickerView_awv_outerTextColor, 0xffafafaf);
            mIndicatorLineColor = typedArray.getInteger(R.styleable.WheelPickerView_awv_dividerTextColor, 0xffc5c5c5);
            itemsVisibleCount = typedArray.getInteger(R.styleable.WheelPickerView_awv_itemsVisibleCount, DEFAULT_VISIBIE_ITEMS);
            if (itemsVisibleCount % 2 == 0) {
                itemsVisibleCount = DEFAULT_VISIBIE_ITEMS;
            }
            isLoopScollr = typedArray.getBoolean(R.styleable.WheelPickerView_awv_isLoop, true);
            typedArray.recycle();
        }

        drawingStrings=new HashMap<>();
        totalScrollY = 0;
        initPosition = -1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initPaintIfPossible();
        initMeasure();
    }

    /**
     * 初始化画笔
     */
    private void initPaintIfPossible(){
        if(mSelectTxtPaint == null){
            mSelectTxtPaint = new Paint();
            mSelectTxtPaint.setColor(mSelectTxtColor);
            mSelectTxtPaint.setAntiAlias(true);
            mSelectTxtPaint.setTypeface(typeface);
            mSelectTxtPaint.setTextSize(textSize);
            mSelectTxtPaint.setTextScaleX(scaleX);
        }

        if(mDefaultTxtPaint == null){
            mDefaultTxtPaint = new Paint();
            mDefaultTxtPaint.setColor(mDefaultTxtColor);
            mDefaultTxtPaint.setAntiAlias(true);
            mDefaultTxtPaint.setTypeface(typeface);
            mDefaultTxtPaint.setTextSize(textSize);
        }

        if(mIndicatorLinePaint == null){
            mIndicatorLinePaint = new Paint();
            mIndicatorLinePaint.setColor(mIndicatorLineColor);
            mIndicatorLinePaint.setAntiAlias(true);
        }
    }

    /**
     * 测量各高度
     */
   private void  initMeasure(){
        // 获取测量的宽高
        mMeasuredWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();
        if(mMeasuredHeight == 0 || mMeasuredWidth == 0 ){
            return;
        }
        // 内部左右边距
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mMeasuredWidth = mMeasuredWidth  - mPaddingRight ;

        mSelectTxtPaint.getTextBounds("\u661F\u671F", 0, 2, tempRect);
        textHeight = tempRect.height();
        // 视图的高/2 * 圆周率
        halfCircumference = (int) (mMeasuredHeight * Math.PI / 2);
        // 每一行高度计算
        itemTxtHeight = (int) (halfCircumference / (lineSpacingMultiplier * (itemsVisibleCount -1)));
        // 滚筒半径
        radius = mMeasuredHeight / 2;
        //选中item 上边分割线的Y坐标
        firstLineY = (int) ((mMeasuredHeight - lineSpacingMultiplier * itemTxtHeight) / 2.0F);
        //选中item 下边分割线的Y坐标
        secondLineY = (int) ((mMeasuredHeight + lineSpacingMultiplier * itemTxtHeight) / 2.0F);
        // 初始化选中Item 位置
       if (initPosition == -1) {
           if (isLoopScollr) {
               initPosition = (items.size() + 1) / 2;
           } else {
               initPosition = 0;
           }
       }
       // 当前位置
       preCurrentIndex = initPosition;
   }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(items == null && items.isEmpty()){
            return;
        }
        int ScolleCount = (int) (totalScrollY / (lineSpacingMultiplier * itemTxtHeight));
        // 重新计算选中位置
        preCurrentIndex = initPosition + ScolleCount % items.size();

        if (!isLoopScollr) {
            if (preCurrentIndex < 0) {
                preCurrentIndex = 0;
            }
            if (preCurrentIndex > items.size() - 1) {
                preCurrentIndex = items.size() - 1;
            }
        } else {
            if (preCurrentIndex < 0) {
                preCurrentIndex = items.size() + preCurrentIndex;
            }
            if (preCurrentIndex > items.size() - 1) {
                preCurrentIndex = preCurrentIndex - items.size();
            }
        }

        int count = 0;
        // 设置需要显示的item的数据
        while (count < itemsVisibleCount) {
            int itemIndex = preCurrentIndex - (itemsVisibleCount / 2 - count);
            if (isLoopScollr) {
                while (itemIndex < 0) {
                    itemIndex = itemIndex + items.size();
                }
                while (itemIndex > items.size() - 1) {
                    itemIndex = itemIndex - items.size();
                }
                drawingStrings.put(count, items.get(itemIndex));
            } else if (itemIndex < 0) {
                drawingStrings.put(count,new IndexString());
            } else if (itemIndex > items.size() - 1) {
                drawingStrings.put(count,new IndexString());
            } else {
                drawingStrings.put(count, items.get(itemIndex));
            }
            count++;
        }
        // 绘制框中选中项的上下分割线
        canvas.drawLine(mPaddingLeft, firstLineY, mMeasuredWidth, firstLineY, mIndicatorLinePaint);
        canvas.drawLine(mPaddingLeft, secondLineY, mMeasuredWidth, secondLineY, mIndicatorLinePaint);

        //
        int scrollCount2 = (int) (totalScrollY % (lineSpacingMultiplier * itemTxtHeight));
        int i = 0;
        while (i < itemsVisibleCount) {
            canvas.save();
            float itemHeight = itemTxtHeight * lineSpacingMultiplier;
            double radian = ((itemHeight * i - scrollCount2) * Math.PI) / halfCircumference;
            if (radian >= Math.PI || radian <= 0) {
                canvas.restore();
            } else {
                int translateY = (int) (radius - Math.cos(radian) * radius - (Math.sin(radian) * itemTxtHeight) / 2D);
                canvas.translate(0, translateY);
                canvas.scale(1.0F, (float) Math.sin(radian));
                if (translateY <= firstLineY && itemTxtHeight + translateY >= firstLineY) {
                    // first divider
                    canvas.save();
                    canvas.clipRect(0, 0, mMeasuredWidth, firstLineY - translateY);
                    drawOuterText(canvas, i);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0, firstLineY - translateY, mMeasuredWidth, (int) (itemHeight));
                    drawCenterText(canvas, i);
                    canvas.restore();
                } else if (translateY <= secondLineY && itemTxtHeight + translateY >= secondLineY) {
                    // second divider
                    canvas.save();
                    canvas.clipRect(0, 0, mMeasuredWidth, secondLineY - translateY);
                    drawCenterText(canvas, i);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0, secondLineY - translateY, mMeasuredWidth, (int) (itemHeight));
                    drawOuterText(canvas, i);
                    canvas.restore();
                } else if (translateY >= firstLineY && itemTxtHeight + translateY <= secondLineY) {
                    // center item
                    canvas.clipRect(0, 0, mMeasuredWidth, (int) (itemHeight));
                    drawCenterText(canvas, i);
                    selectedItem = items.indexOf(drawingStrings.get(i));
                } else {
                    // other item
                    canvas.clipRect(0, 0, mMeasuredWidth, (int) (itemHeight));
                    drawOuterText(canvas, i);
                }
                canvas.restore();
            }
            i++;
        }
    }

    /**
     * 绘制选中的 中间 text item
     * @param pCanvas
     * @param position
     */
    private void drawCenterText(Canvas pCanvas, int position) {
        pCanvas.drawText(
                drawingStrings.get(position).string,
                getTextX(drawingStrings.get(position).string, mDefaultTxtPaint, tempRect),
                getDrawingY(),
                mSelectTxtPaint);
    }

    /**
     * 绘制未选中的Text item
     * @param pCanvas
     * @param position
     */
    private void drawOuterText(Canvas pCanvas, int position) {
        int alphaPos = 0;
        if(position <= itemsVisibleCount/2){
             alphaPos = itemsVisibleCount/2 - position;
        }else{
            alphaPos = position - itemsVisibleCount/2;
        }
        int alpha = 255 - alphaScale * alphaPos;
        if(alpha < 0){
            mDefaultTxtPaint.setAlpha(0);
        }else {
            mDefaultTxtPaint.setAlpha(alpha);
        }
        pCanvas.drawText(
                drawingStrings.get(position).string,
                getTextX(drawingStrings.get(position).string, mDefaultTxtPaint, tempRect),
                getDrawingY(),
                mDefaultTxtPaint);
        mDefaultTxtPaint.setAlpha(255);
    }

    /**
     * 绘制的Y轴高度
     */
    private int getDrawingY() {
        if (itemTxtHeight > textHeight) {
            return itemTxtHeight - ((itemTxtHeight - textHeight) / 2);
        } else {
            return itemTxtHeight;
        }
    }

    /**
     * text 开始的X位置
     * @param a
     * @param paint
     * @param rect
     * @return
     */
    private int getTextX(String a, Paint paint, Rect rect) {
        paint.getTextBounds(a, 0, a.length(), rect);
        int textWidth = rect.width();
        textWidth *= scaleX;
        return (mMeasuredWidth - mPaddingLeft - textWidth) / 2 + mPaddingLeft;
    }

    // 开始时间
    private long startTime = 0;
    private ScheduledFuture<?> mFuture;
    // 操作事件类型
    public enum ACTION { CLICK, FLING, DRAG }
    private float previousY;
    // 便宜量
    private int mOffset;
    // Timer mTimer;
    private ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean eventConsumed = flingGestureDetector.onTouchEvent(event);
        float itemHeight = lineSpacingMultiplier * itemTxtHeight;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis();
                cancelFuture();
                previousY = event.getRawY();
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                float dy = previousY - event.getRawY();
                previousY = event.getRawY();

                totalScrollY = (int) (totalScrollY + dy);

                if (!isLoopScollr) {
                    float top = -initPosition * itemHeight;
                    float bottom = (items.size() - 1 - initPosition) * itemHeight;

                    if (totalScrollY < top) {
                        totalScrollY = (int) top;
                    } else if (totalScrollY > bottom) {
                        totalScrollY = (int) bottom;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            default:
                if (!eventConsumed) {
                    float y = event.getY();
                    double l = Math.acos((radius - y) / radius) * radius;
                    int circlePosition = (int) ((l + itemHeight / 2) / itemHeight);

                    float extraOffset = (totalScrollY % itemHeight + itemHeight) % itemHeight;
                    mOffset = (int) ((circlePosition - itemsVisibleCount / 2) * itemHeight - extraOffset);

                    if ((System.currentTimeMillis() - startTime) > 120) {
                        smoothScroll(ACTION.DRAG);
                    } else {
                        smoothScroll(ACTION.CLICK);
                    }
                }
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }

        invalidate();
        return true;
    }

    /**
     * 平滑判断
     * @param action
     */
     void smoothScroll(ACTION action) {
        cancelFuture();
        if (action == ACTION.FLING || action == ACTION.DRAG) {
            float itemHeight = lineSpacingMultiplier * itemTxtHeight;
            mOffset = (int) ((totalScrollY % itemHeight + itemHeight) % itemHeight);
            if ((float) mOffset > itemHeight / 2.0F) {
                mOffset = (int) (itemHeight - (float) mOffset);
            } else {
                mOffset = -mOffset;
            }
        }
        mFuture =
                mExecutor.scheduleWithFixedDelay(new SmoothScrollTimerTask(this, mOffset), 0, 10, TimeUnit.MILLISECONDS);
    }


    public void cancelFuture() {
        if (mFuture != null && !mFuture.isCancelled()) {
            mFuture.cancel(true);
            mFuture = null;
        }
    }

    /**
     * 滑动速度设置
     * @param velocityY
     */
    protected final void scrollBy(float velocityY) {
        cancelFuture();
        int velocityFling = 10;
        mFuture = mExecutor.scheduleWithFixedDelay(new InertiaTimerTask(this, velocityY), 0, velocityFling,
                TimeUnit.MILLISECONDS);
    }


    protected final void onItemSelected() {
        if (onItemSelectedListener != null) {
            postDelayed(new OnItemSelectedRunnable(this), 200L);
        }
    }

    public final int getSelectedItem() {
        return preCurrentIndex;
    }

    public final void setItems(List<String> items) {
        this.items = convertData(items);
        initMeasure();
        invalidate();
    }

    public List<IndexString> convertData(List<String> items){
        List<IndexString> data= new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            data.add(new IndexString(i,items.get(i)));
        }
        return data;
    }


    public final void setInitPosition(int initPosition) {
        if (initPosition < 0) {
            this.initPosition = 0;
        } else {
            if (items != null && items.size() > initPosition) {
                this.initPosition = initPosition;
            }
        }
    }

    public final void setListener(OnItemSelectedListener OnItemSelectedListener) {
        onItemSelectedListener = OnItemSelectedListener;
    }


    /**
     * 封装展示的数据源
     */
    class  IndexString {
        private String  string;
        private int index;
        public  IndexString(){
            this.string="";
        }

        public IndexString(int index,String str){
            this.index=index;
            this.string=str;
        }

    }


    public interface OnItemSelectedListener {
        void onItemSelected(int index);
    }


}
