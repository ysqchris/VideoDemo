package com.yusq.videodemo.model.hangzhou;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseFragment;
import com.yusq.videodemo.fragment.ShengzhengFragment;
import com.yusq.videodemo.inject.SetViewInject;
import com.yusq.videodemo.model.hangzhou.adapter.HangzhouFragmentStatePagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SetViewInject(mainLayoutId = R.layout.hangzhou_fragment_layout)
public class HangZhouFragment extends BaseFragment {

    @BindView(R.id.hangzhou_tab_layout)
    TabLayout hangzhouTabLayout;
    @BindView(R.id.hangzhou_view_pager)
    ViewPager hangzhouViewPager;

    Unbinder unbinder;

    FragmentManager childFragmentManager;

    @Override
    public void afterBindView() {
        hangzhouTabLayout.setupWithViewPager(hangzhouViewPager);
        childFragmentManager = getChildFragmentManager();
        hangzhouViewPager.setAdapter(new HangzhouFragmentStatePagerAdapter(childFragmentManager));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
