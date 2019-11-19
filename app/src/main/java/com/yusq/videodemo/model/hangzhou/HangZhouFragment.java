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
        hangzhouViewPager.setAdapter(new FragmentPagerAdapter(childFragmentManager) {
            @Override
            public Fragment getItem(int i) {
                return new BehaviorFragment();
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "标题"+position;
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
