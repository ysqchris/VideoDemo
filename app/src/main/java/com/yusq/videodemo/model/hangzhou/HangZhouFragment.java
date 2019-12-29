package com.yusq.videodemo.model.hangzhou;


import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseFragment;
import com.yusq.videodemo.inject.SetViewInject;
import com.yusq.videodemo.model.hangzhou.adapter.HangzhouFragmentStatePagerAdapter;

import butterknife.BindView;
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
