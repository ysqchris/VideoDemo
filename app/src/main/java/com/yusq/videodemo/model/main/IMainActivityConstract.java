package com.yusq.videodemo.model.main;

import androidx.fragment.app.Fragment;

import com.yusq.videodemo.imlp.ILifeCycle;
import com.yusq.videodemo.imlp.IMvpView;

public interface IMainActivityConstract {

    interface Iview extends IMvpView {

        void showFragment(Fragment fragment);

        void addFragment(Fragment fragment);

        void hideFragment(Fragment fragment);
    }

    interface Ipresenter extends ILifeCycle {
       void initHomeFragment();
       int getCurrentCheckIndex();
    }

}
