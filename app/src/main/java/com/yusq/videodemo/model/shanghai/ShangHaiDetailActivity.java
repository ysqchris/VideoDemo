package com.yusq.videodemo.model.shanghai;

import android.util.Log;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseActivity;
import com.yusq.videodemo.bean.ShangHaiDetailBean;
import com.yusq.videodemo.inject.SetViewInject;
import com.yusq.videodemo.model.shanghai.presenter.IShangHaiDetailActivityConstract;
import com.yusq.videodemo.model.shanghai.presenter.ShangHaiDetailPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/15
 * <p>
 * 包 名：com.yusq.videodemo.model
 * <p>
 * 类 名：ShangHaiDetailActivity
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
@SetViewInject(mainLayoutId = R.layout.shanghai_detail_layout)
public class ShangHaiDetailActivity extends BaseActivity implements IShangHaiDetailActivityConstract.Iview {

    IShangHaiDetailActivityConstract.Ipresenter mIpresenter = new ShangHaiDetailPresenter(this);


    @Override
    public void afterBindView() {
        initGetNetData();
    }

    /**
     * 网络请求
     */
    private void initGetNetData() {
          mIpresenter.getNetData();
    }

    @Override
    public void showData(ShangHaiDetailBean data) {

    }
}
