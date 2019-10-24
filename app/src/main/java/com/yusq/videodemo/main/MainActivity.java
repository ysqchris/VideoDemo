package com.yusq.videodemo.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseActivity;
import com.yusq.videodemo.inject.SetViewInject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@SetViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityConstract.Iview {

    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;
    @BindView(R.id.rg_bottom_tab_main)
    FrameLayout rgBottomTabMain;
    @BindView(R.id.fac_main_home)
    FloatingActionButton facMainHome;
    @BindView(R.id.rg_main_bottom_1)
    RadioGroup rgMainBottom1;
    @BindView(R.id.fragment_layout)
    FrameLayout fragmentLayout;
    @BindView(R.id.rb_main_shangnhai)
    RadioButton rbMainShangnhai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.rb_main_beijing)
    RadioButton rbMainBeijing;
    @BindView(R.id.rb_main_shenzheng)
    RadioButton rbMainShenzheng;


    private boolean isChange;
    private MainActivityPresenter presenter = new MainActivityPresenter(this);


    @Override
    public void afterBindView() {
        presenter.initHomeFragment();
    }


    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChange = !isChange;
                if (isChange) {
                    rgMainBottom.setVisibility(View.GONE);
                    rgMainBottom1.setVisibility(View.VISIBLE);
                } else {
                    rgMainBottom.setVisibility(View.VISIBLE);
                    rgMainBottom1.setVisibility(View.GONE);
                }
        }
    }


    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment);

    }

    @Override
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, fragment);
    }

    @Override
    public void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment);
    }

}
