package com.yusq.videodemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yusq.videodemo.R;
import com.yusq.videodemo.inject.SetViewInject;

import butterknife.BindView;
import butterknife.OnClick;


@SetViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {


    @BindView(R.id.rb_main_1)
    RadioButton rbMain1;
    @BindView(R.id.rb_main_2)
    RadioButton rbMain2;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;
    @BindView(R.id.rg_bottom_tab_main)
    FrameLayout rgBottomTabMain;
    @BindView(R.id.fac_main_home)
    FloatingActionButton facMainHome;
    @BindView(R.id.rb_main_3)
    RadioButton rbMain3;
    @BindView(R.id.rb_main_4)
    RadioButton rbMain4;
    @BindView(R.id.rg_main_bottom_1)
    RadioGroup rgMainBottom1;

    private boolean isChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.fac_main_home:
               isChange = !isChange;
               if(isChange){
                   rgMainBottom.setVisibility(View.GONE);
                   rgMainBottom1.setVisibility(View.VISIBLE);
               }else{
                   rgMainBottom.setVisibility(View.VISIBLE);
                   rgMainBottom1.setVisibility(View.GONE);
               }


       }

    }
}
