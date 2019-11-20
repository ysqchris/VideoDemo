package com.yusq.videodemo.model.hangzhou.behavior;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseFragment;
import com.yusq.videodemo.bean.ShangHaiDetailBean;
import com.yusq.videodemo.inject.SetViewInject;
import com.yusq.videodemo.model.hangzhou.adapter.BeHaviorAdapter;
import com.yusq.videodemo.model.shanghai.presenter.IShangHaiDetailActivityConstract;
import com.yusq.videodemo.model.shanghai.presenter.ShangHaiDetailPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


@SetViewInject(mainLayoutId = R.layout.behaver_fragment)
public class BehaviorFragment extends BaseFragment implements IShangHaiDetailActivityConstract.Iview {

    IShangHaiDetailActivityConstract.Ipresenter mIpresenter = new ShangHaiDetailPresenter(this);

    @BindView(R.id.behavior_toolsbar)
    Toolbar behaviorToolsbar;
    @BindView(R.id.behavior_app_bar_layout)
    AppBarLayout behaviorAppBarLayout;
    @BindView(R.id.behavior_recycler_view)
    RecyclerView behaviorRecyclerView;


    Unbinder unbinder;

    List<ShangHaiDetailBean.ResultBean> resultBeanList;
    @BindView(R.id.behavior_bottom_text)
    TextView behaviorBottomText;
    Unbinder unbinder1;

    @Override
    public void afterBindView() {
        behaviorRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mIpresenter.getNetData();

    }

    @Override
    public void showData(ShangHaiDetailBean data) {
        resultBeanList = data.result.data;
        if (behaviorRecyclerView.getAdapter() == null) {
            behaviorRecyclerView.setAdapter(new BeHaviorAdapter(getContext(), resultBeanList));
        } else {
            behaviorRecyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.behavior_bottom_text)
    public void onViewClicked() {
        Toast.makeText(getContext(), "点击有效", Toast.LENGTH_LONG).show();
    }
}
