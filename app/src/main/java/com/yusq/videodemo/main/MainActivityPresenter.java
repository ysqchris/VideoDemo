package com.yusq.videodemo.main;

import android.support.v4.app.Fragment;

import com.yusq.videodemo.fragment.BeiJingFragment;
import com.yusq.videodemo.fragment.HangZhouFragment;
import com.yusq.videodemo.fragment.ShanHaiFragment;
import com.yusq.videodemo.fragment.ShengzhengFragment;
import com.yusq.videodemo.imlp.IMvpView;
import com.yusq.videodemo.presenter.BaseAbstractLifePresenter;

public class MainActivityPresenter extends BaseAbstractLifePresenter<IMainActivityConstract.Iview>  implements  IMainActivityConstract.Ipresenter {

    private int mCurrentFragmentIndex ;
    private Fragment[] mFragments = new Fragment[4];

    public MainActivityPresenter(IMvpView pIMvpView) {
        super(pIMvpView);
    }



    @Override
    protected IMainActivityConstract.Iview getEmptyView() {
        return null;
    }


    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }


    /**
     * 替换当前的Frgment
     * @param mCurrentFragmentIndex
     */
    private void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i < mFragments.length ; i++) {
            if(mCurrentFragmentIndex != i){
                if(mFragments[i] != null){
                    hideFragment(mFragments[i]);
                }
            }
        }
        Fragment  fragment = mFragments[mCurrentFragmentIndex];
        if(fragment != null){
            addAndShowFragment(fragment);
            setCurrentShowIndex(mCurrentFragmentIndex);
        }else{
            newCurrentFragment(mCurrentFragmentIndex);
        }
    }

    /**
     * 记录当前显示的fragment角标
     * @param mCurrentFragmentIndex
     */
    private void setCurrentShowIndex(int mCurrentFragmentIndex) {

    }

    /**
     * 创建当前的fragment
     * @param mCurrentFragmentIndex
     */
    private void newCurrentFragment(int mCurrentFragmentIndex) {
       Fragment fragment =  null;
        switch (mCurrentFragmentIndex){
            case 0:
                fragment = new ShanHaiFragment();
                break;
            case 1:
                fragment = new HangZhouFragment();
                break;
            case 2:
                fragment = new BeiJingFragment();
                break;
            case 3:
                fragment = new ShengzhengFragment();
                break;
            default:
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    /**
     * 添加并显示当前的额fragment
     * @param fragment
     */
    private void addAndShowFragment(Fragment fragment) {
        if(fragment.isAdded()){
            getView().showFragment(fragment);
        }else {
            getView().addFragment(fragment);
        }
    }

    /**
     * 隐藏当前的fragment
     * @param fragment
     */
    private void hideFragment(Fragment fragment) {
        getView().hideFragment(fragment);
    }
}
