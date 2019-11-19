package com.yusq.videodemo.model.main;

import android.support.v4.app.Fragment;

import com.yusq.videodemo.R;
import com.yusq.videodemo.fragment.BeiJingFragment;
import com.yusq.videodemo.model.hangzhou.HangZhouFragment;
import com.yusq.videodemo.model.shanghai.ShangHaiFragment;
import com.yusq.videodemo.fragment.ShengzhengFragment;
import com.yusq.videodemo.imlp.IMvpView;
import com.yusq.videodemo.presenter.BaseAbstractLifePresenter;

public class MainActivityPresenter extends BaseAbstractLifePresenter<IMainActivityConstract.Iview>  implements  IMainActivityConstract.Ipresenter {

    private int mCurrentFragmentIndex ;
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentFragmentCheckId;

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
    public void replaceFragment(int mCurrentFragmentIndex) {
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
            setCurrentShowIndex(mCurrentFragmentIndex);
        }
    }

    @Override
    public int getCurrentCheckIndex() {
        return mCurrentFragmentIndex;
    }

    /**
     * 记录当前显示的fragment角标
     * @param mCurrentFragmentIndex
     */
    private void setCurrentShowIndex(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex){
            case 0:
                mCurrentFragmentCheckId =  R.id.rb_main_shangnhai;
                break;
            case 1:
                mCurrentFragmentCheckId = R.id.rb_main_hangzhou;
                break;
            case 2:
                mCurrentFragmentCheckId = R.id.rb_main_beijing;
                break;
            case 3:
                mCurrentFragmentCheckId = R.id.rb_main_shenzheng;
                break;
        }

    }

    /**
     * 创建当前的fragment
     * @param mCurrentFragmentIndex
     */
    private void newCurrentFragment(int mCurrentFragmentIndex) {
       Fragment fragment =  null;
        switch (mCurrentFragmentIndex){
            case 0:
                fragment = new ShangHaiFragment();
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
        if(fragment != null && fragment.isVisible()) {
            getView().hideFragment(fragment);
        }
    }


}
