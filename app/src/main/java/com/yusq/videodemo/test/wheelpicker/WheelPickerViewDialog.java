package com.yusq.videodemo.test.wheelpicker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.yusq.videodemo.R;

import java.util.List;

/**
 * 项目名：uhome_huarun
 * <p>
 * 时 间：2019/9/17
 * <p>
 * 包 名：com.uhome.base.view.pickerview
 * <p>
 * 类 名：WheelPickerViewDialog
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class WheelPickerViewDialog extends Dialog implements View.OnClickListener {

    private WheelPickerView mPickerViewLeft , mPickerViewCenter , mPickerViewRight;
    private WheelPickerSelectItemListener mPickerSelectItemListener;
    private TextView mDialogTitle;

    private int leftCheckIndex , centerCheckIndex , rightCheckIndex;

    public WheelPickerViewDialog(Context context) {
        super(context);
    }

    public WheelPickerViewDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public WheelPickerViewDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 目前暂时支持 年月格式YYYYMM   和 年格式 YYYY 2种格式的日期
     * @param context
     * @param theme
     */
    public WheelPickerViewDialog(Context context,
                                 int theme ,
                                 WheelPickerSelectItemListener pSelectItemListener) {
        super(context, theme);
        this.mPickerSelectItemListener = pSelectItemListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_picker_dialog_layout);
        initView();
        initDate();
        initEvents();
    }


    private void initView(){
        findViewById(R.id.tv_wheel_left).setOnClickListener(this);
        findViewById(R.id.tv_wheel_right).setOnClickListener(this);
        mDialogTitle = findViewById(R.id.tv_wheel_title);
        mPickerViewLeft = findViewById(R.id.wheel_picker_left);
        mPickerViewCenter = findViewById(R.id.wheel_picker_center);
        mPickerViewRight = findViewById(R.id.wheel_picker_right);
    }

    private void initDate(){

    }

    /**
     * 初始化每个滑动的监听事件
     */
    private void initEvents(){
        mPickerViewLeft.setListener(new WheelPickerView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                leftCheckIndex = index;
            }
        });

        mPickerViewCenter.setListener(new WheelPickerView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                centerCheckIndex = index;
            }
        });

        mPickerViewRight.setListener(new WheelPickerView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                rightCheckIndex = index;
            }
        });
    }



    /**
     * 设置显示一列 （填充数据到左边列）
     * @param centerData
     */
    public void setColumnVisibleData(List<String> centerData){
        mPickerViewRight.setVisibility(View.GONE);
        mPickerViewCenter.setVisibility(View.GONE);
        mPickerViewLeft.setVisibility(View.VISIBLE);
        mPickerViewLeft.setItems(centerData);
    }

    /**
     * 设置显示两列 （填充数据到中间和左边列）
     * @param centerData
     * @param leftData
     */
    public void setColumnVisibleData(List<String> leftData ,List<String> centerData){
        mPickerViewRight.setVisibility(View.GONE);
        mPickerViewLeft.setVisibility(View.VISIBLE);
        mPickerViewCenter.setVisibility(View.VISIBLE);
        mPickerViewCenter.setItems(centerData);
        mPickerViewLeft.setItems(leftData);
    }

    /**
     * 设置显示三列
     * @param centerData
     * @param leftData
     * @param
     */
    public void setColumnVisibleData(List<String> leftData , List<String> centerData ,  List<String> rightData){
        mPickerViewRight.setVisibility(View.VISIBLE);
        mPickerViewLeft.setVisibility(View.VISIBLE);
        mPickerViewCenter.setVisibility(View.VISIBLE);
        mPickerViewLeft.setItems(leftData);
        mPickerViewCenter.setItems(centerData);
        mPickerViewRight.setItems(rightData);
    }


    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if(vId == R.id.tv_wheel_right){ // 确定
            if(mPickerSelectItemListener != null){
                mPickerSelectItemListener.selectItemIndexes(leftCheckIndex , centerCheckIndex , rightCheckIndex);
            }
            dismiss();
        }else if(vId == R.id.tv_wheel_left){// 取消
            dismiss();
        }
    }

    public void showDialog(boolean isFillScreenAndBottom) {
        super.show();
        if(isFillScreenAndBottom) { // 设置显示在底部并铺满全屏
            setDialogFillScreenAndGravityBottom();
        }
    }

    /**
     * 设置标题
     * @param resId
     */
    public void setDialogTitle(int resId){
        if(resId != 0) {
            mDialogTitle.setText(resId);
        }
    }

    /**
     * 设置标题
     * @param res
     */
    public void setDialogTitle(String res){
        if(!TextUtils.isEmpty(res)) {
            mDialogTitle.setText(res);
        }
    }

    /**
     * 选中Item的监听
     */
    public interface WheelPickerSelectItemListener{
         void selectItemIndexes(int leftIndex, int centerIndex, int rightIndex);
    }

    /**
     * 设置dialog全屏显示
     */
    public void setDialogFillScreenAndGravityBottom(){
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = getWindow();
        //设置dialog弹出的动画，从屏幕底部弹出
        //window.setWindowAnimations(R.style.);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int) (d.getWidth());
        wlp.gravity = Gravity.BOTTOM;
        if (wlp.gravity == Gravity.BOTTOM) wlp.y = 0;
        //如果是底部显示，则距离底部的距离是0
        window.setAttributes(wlp);
    }


}
