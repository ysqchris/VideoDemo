package com.yusq.videodemo.model.hangzhou.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yusq.videodemo.model.hangzhou.behavior.BehaviorFragment;

import java.util.ArrayList;
import java.util.List;


public class HangzhouFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    List<String> fragmentsTitle = new ArrayList<>();

    public HangzhouFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentsTitle.add("仿知乎");
        fragmentsTitle.add("仿即刻");
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new BehaviorFragment();
            case 1:
                return new DesignViewFragment();
            default:
        }
        return new BehaviorFragment();

    }

    @Override
    public int getCount() {
        return fragmentsTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitle.get(position);
    }
}
