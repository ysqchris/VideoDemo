package com.yusq.videodemo.model.hangzhou.adapter;

import android.icu.text.RelativeDateTimeFormatter;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.yusq.videodemo.model.hangzhou.behavior.BehaviorFragment;
import com.yusq.videodemo.model.hangzhou.refresh.RefreshListFragment;

import java.util.ArrayList;
import java.util.List;


public class HangzhouFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    List<String> fragmentsTitle = new ArrayList<>();

    public HangzhouFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentsTitle.add("仿知乎");
        fragmentsTitle.add("仿即刻");
        fragmentsTitle.add("下拉刷新");
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new BehaviorFragment();
            case 1:
                return new DesignViewFragment();
            case 2:
                return new RefreshListFragment();
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
