package com.yusq.videodemo.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yusq.videodemo.R;
import com.yusq.videodemo.test.wheelpicker.WheelPickerView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity  extends AppCompatActivity {
    private  MyPickerScrollView mMyPickerScrollView;
    private  TextView tv_yes;
    private  RelativeLayout picker_rel;
    private  Button show_btn;
    private List<String> mDataList;
    private WheelPickerView mWheelPickerView1 = null;
    private WheelPickerView mWheelPickerView2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mWheelPickerView1 = findViewById(R.id.wheel_picker_1);
        mWheelPickerView2 = findViewById(R.id.wheel_picker_2);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("item" + i);
        }
        //设置原始数据
        mWheelPickerView1.setItems(list);
        //设置初始位置
        mWheelPickerView1.setInitPosition(4);

        //设置原始数据
        mWheelPickerView2.setItems(list);
        //设置初始位置
        mWheelPickerView2.setInitPosition(4);

        mWheelPickerView1.setListener(new WheelPickerView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Toast.makeText(TestActivity.this, "item " + index, Toast.LENGTH_SHORT).show();
                mWheelPickerView2.setInitPosition(index);
                mWheelPickerView2.invalidate();
            }
        });

        /*initView();
        initLinstener();
        initData();*/
    }

    /**
     * 初始化
     */
    private void initView() {
       /* mMyPickerScrollView = (MyPickerScrollView) findViewById(R.id.pickerscrlllview);
        picker_rel = findViewById(R.id.picker_rl);
        show_btn = findViewById(R.id.show_btn);
        tv_yes = findViewById(R.id.select_text_confirm);*/
    }

    /**
     * 设置监听事件
     */
    private void initLinstener() {
        mMyPickerScrollView.setOnSelectListener(pickerListener);
        tv_yes.setOnClickListener(onClickListener);
        show_btn.setOnClickListener(onClickListener);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mDataList = new ArrayList<>();
        String[] name = new String[] { "中国香港     +111", "中国澳门     +222", "中国台湾     +333", "中国大陆     +86" };
        for (int i = 0; i < name.length; i++) {
            mDataList.add((name[i]));
        }
        // 设置数据，默认选择第一条
        mMyPickerScrollView.setListDate(mDataList);
        mMyPickerScrollView.setSelected(0);
    }

    // 滚动选择器选中事件
    MyPickerScrollView.onSelectListener pickerListener = new MyPickerScrollView.onSelectListener() {

        @Override
        public void onSelect(String pickers) {
            Toast.makeText(TestActivity.this  , pickers , Toast.LENGTH_SHORT).show();
        }
    };

    // 点击监听事件
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            /*int vId = v.getId();
            if (vId == R.id.show_btn) {
                picker_rel.setVisibility(View.VISIBLE);
            } else if (vId == R.id.select_text_confirm) {
                picker_rel.setVisibility(View.GONE);
            }*/
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
