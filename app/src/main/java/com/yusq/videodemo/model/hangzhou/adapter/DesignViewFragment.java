package com.yusq.videodemo.model.hangzhou.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseFragment;
import com.yusq.videodemo.inject.SetViewInject;
import com.yusq.videodemo.view.LikeClickView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SetViewInject(mainLayoutId = R.layout.design_view_fragment_layout)
public class DesignViewFragment extends BaseFragment {

    @BindView(R.id.design_view_is_like)
    LikeClickView designViewIsLike;
    Unbinder unbinder;

    @Override
    public void afterBindView() {
          designViewIsLike.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  designViewIsLike.ChangeIsLike();
              }
          });
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
}
