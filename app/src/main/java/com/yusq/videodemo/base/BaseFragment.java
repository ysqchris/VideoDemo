package com.yusq.videodemo.base;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusq.videodemo.inject.SetViewInject;
import com.yusq.videodemo.presenter.LifeCircleMvpFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends LifeCircleMvpFragment {

    private View mRootView;
    protected Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SetViewInject annotation = this.getClass().getAnnotation(SetViewInject.class);
        if(annotation != null){
            int mainLayoutId = annotation.mainLayoutId();
            if(mainLayoutId > 0){
                mRootView = LayoutInflater.from(mContext).inflate(mainLayoutId , null);
                bindView(mRootView);
                afterBindView();
            }else{
                throw new RuntimeException("mainLayoutId is null");
            }
        }else{
            throw new RuntimeException("annotation is null");
        }
        return mRootView;
    }

    /**
     * view 的依赖注入绑定
     */
    private void bindView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    /**
     * view绑定之后的操作
     * 模板方法设计模式
     */
    public abstract void afterBindView();
}
