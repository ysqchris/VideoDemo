package com.yusq.videodemo.model.shanghai.presenter;

import android.util.Log;

import com.yusq.videodemo.base.BaseTask;
import com.yusq.videodemo.bean.ShangHaiDetailBean;
import com.yusq.videodemo.imlp.IMvpView;
import com.yusq.videodemo.model.shanghai.ShanghaiDetailHttpTask;
import com.yusq.videodemo.oknet.result.IResult;
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
        /**
         * 1. 合理利用继承关系
         * 2. 合理利用抽象编程
         * 3. 合理利用泛型传递数据
         *
         */
        submitTask(new BaseTask<ShangHaiDetailBean>(){

                @Override
                public IResult<ShangHaiDetailBean> doingBackground() {
                    return new ShanghaiDetailHttpTask().getDataList("desc" , "1" , "20");
                }

                @Override
                public void onSuccess(IResult<ShangHaiDetailBean> t) {
                    ShangHaiDetailBean data = t.getData();
                    getView().showData(data);
                }
            });
        }
}
