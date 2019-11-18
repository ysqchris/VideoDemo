package com.yusq.videodemo.model.shanghai.presenter;

import android.util.Log;

import com.yusq.videodemo.imlp.IMvpView;
import com.yusq.videodemo.model.shanghai.ShanghaiDetailHttpTask;
import com.yusq.videodemo.oknet.result.ResultData;
import com.yusq.videodemo.oknet.task.LfTask;
import com.yusq.videodemo.presenter.BaseAbstractLifePresenter;

import java.io.IOException;

import okhttp3.Response;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.model.shanghai.presenter
 * <p>
 * 类 名：ShangHaiDetailPresenter
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ShangHaiDetailPresenter  extends BaseAbstractLifePresenter<IShangHaiDetailActivityConstract.Iview>
        implements  IShangHaiDetailActivityConstract.Ipresenter  {


    public ShangHaiDetailPresenter(IMvpView pIMvpView) {
        super(pIMvpView);
    }

    @Override
    protected IShangHaiDetailActivityConstract.Iview getEmptyView() {
        return null;
    }

    @Override
    public void getNetData() {
            submitTask(new LfTask() {
                //在子线程中调用
                @Override
                public Object doingBackground() {
                    return new ShanghaiDetailHttpTask().getDataList("desc" , "1" , "2");
                }

                // 回调到主线程
                @Override
                public void onSuccess(Object pO) {
                  
                }
                // 回调到主线程
                @Override
                public void onException(Throwable pThrowable) {
                    // 异常回调;
                    pThrowable.getMessage().toString();
                }
            });
        }
}
