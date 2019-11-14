package com.yusq.videodemo.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseFragment;
import com.yusq.videodemo.inject.SetViewInject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SetViewInject(mainLayoutId = R.layout.shanghai_fragment_layout)
public class ShangHaiFragment extends BaseFragment {

    @BindView(R.id.shanghai_bar_img)
    ImageView shanghaiBarImg;
    @BindView(R.id.shanghai_coolapseing_tool_barlayout)
    CollapsingToolbarLayout shanghaiCoolapseingToolBarlayout;
    @BindView(R.id.shanghai_app_bar_layout)
    AppBarLayout shanghaiAppBarLayout;
    @BindView(R.id.shanghai_app_nested_scrollview)
    NestedScrollView shanghaiAppNestedScrollview;
    Unbinder unbinder;

    @Override
    public void afterBindView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.shanghai_bar_img, R.id.shanghai_app_nested_scrollview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shanghai_bar_img:
                break;
            case R.id.shanghai_app_nested_scrollview:
                Log.e("TAG", "onViewClicked: 滑动控件");
                break;
        }
    }
}
