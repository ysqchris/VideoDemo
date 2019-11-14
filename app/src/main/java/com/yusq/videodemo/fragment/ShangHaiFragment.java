package com.yusq.videodemo.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusq.videodemo.R;
import com.yusq.videodemo.adapter.ShangHaiAdapter;
import com.yusq.videodemo.base.BaseFragment;
import com.yusq.videodemo.bean.ShangHaiBean;
import com.yusq.videodemo.inject.SetViewInject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SetViewInject(mainLayoutId = R.layout.shanghai_fragment_layout)
public class ShangHaiFragment extends BaseFragment {

    @BindView(R.id.shanghai_bar_img)
    ImageView shanghaiBarImg;

    @BindView(R.id.shanghai_toolsbar_tx)
    TextView shanghaiToolsbarTx;

    @BindView(R.id.shanghai_toolsbar)
    Toolbar shanghaiToolsbar;

    @BindView(R.id.shanghai_coolapseing_tool_barlayout)
    CollapsingToolbarLayout shanghaiCoolapseingToolBarlayout;

    @BindView(R.id.shanghai_app_bar_layout)
    AppBarLayout shanghaiAppBarLayout;

    @BindView(R.id.shanghai_recycler_view)
    RecyclerView mRecyclerView;

    Unbinder unbinder;

    @Override
    public void afterBindView() {
        initRecyclerView();
        initListenter();
    }



    private void initRecyclerView() {
       mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
       List<ShangHaiBean> mdatas = new ArrayList<>();
       for (int i = 0 ; i < 20; i++) {
           if(i%4 == 0){
               ShangHaiBean data = new ShangHaiBean();
               ArrayList<ShangHaiBean> hItemListDatas = new ArrayList<>();
               for (int j=0 ; j < 6 ; j++){
                   ShangHaiBean dataH = new ShangHaiBean();
                   dataH.setShowImg(false).setDec(j + "H");
                   hItemListDatas.add(dataH);
               }
               data.setShowImg(false).setItemType(ShangHaiBean.IShangHaiItemType.HORIZANTAL).sethListData(hItemListDatas);
               mdatas.add(data);
           }else {
               ShangHaiBean data = new ShangHaiBean();
               data.setShowImg(false).setDec(i + "");
               mdatas.add(data);
           }
       }
       mRecyclerView.setAdapter(new ShangHaiAdapter(mContext , mdatas));
    }

    private void initListenter() {
        shanghaiAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout pAppBarLayout, int pI) {
                int viewHeight = pAppBarLayout.getMeasuredHeight() + pI;
                Log.e("ShangHaiFragment", "viewHeight == " + viewHeight);
                if (viewHeight < 500) {
                    shanghaiToolsbarTx.setVisibility(View.VISIBLE);
                } else {
                    shanghaiToolsbarTx.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.shanghai_bar_img, R.id.shanghai_toolsbar_tx, R.id.shanghai_toolsbar, R.id.shanghai_coolapseing_tool_barlayout, R.id.shanghai_app_bar_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shanghai_bar_img:
                break;
            case R.id.shanghai_toolsbar_tx:
                break;
            case R.id.shanghai_toolsbar:
                break;
            case R.id.shanghai_coolapseing_tool_barlayout:
                break;
            case R.id.shanghai_app_bar_layout:
                break;
        }
    }
}
