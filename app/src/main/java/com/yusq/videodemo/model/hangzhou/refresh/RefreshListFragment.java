package com.yusq.videodemo.model.hangzhou.refresh;


import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseFragment;
import com.yusq.videodemo.inject.SetViewInject;
import com.yusq.videodemo.view.refresh.RefreshLayout;

import butterknife.BindView;

@SetViewInject(mainLayoutId = R.layout.refresh_list_fragment_layout)
public class RefreshListFragment extends BaseFragment {

    @BindView(R.id.hangzhou_refresh)
    RefreshLayout hangzhouRefresh;

    @Override
    public void afterBindView() {
        hangzhouRefresh.setRefreshManager();
    }
}


