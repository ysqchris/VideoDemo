package com.yusq.videodemo.model.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseActivity;
import com.yusq.videodemo.constant.LocalConstants;
import com.yusq.videodemo.inject.SetViewInject;

import butterknife.BindView;
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
                hindRgmainFragment();
        }
    }

    private void hindRgmainFragment() {
        if (presenter.getCurrentCheckIndex() < 2) {
            presenter.replaceFragment(LocalConstants.BEI_JIN);
            rbMainBeijing.setChecked(true);
        } else {
            presenter.replaceFragment(LocalConstants.SHANG_HAI);
            rbMainShangnhai.setChecked(true);
        }
    }


    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();

    }

    @Override
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, fragment).commit();
    }

    @Override
    public void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    @OnClick({R.id.rb_main_shangnhai, R.id.rb_main_hangzhou, R.id.rb_main_beijing, R.id.rb_main_shenzheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_main_shangnhai:
                presenter.replaceFragment(0);
                break;
            case R.id.rb_main_hangzhou:
                presenter.replaceFragment(1);
                break;
            case R.id.rb_main_beijing:
                presenter.replaceFragment(2);
                break;
            case R.id.rb_main_shenzheng:
                presenter.replaceFragment(3);
                break;
        }
    }
}
