package com.yusq.videodemo.oknet;

import com.yusq.videodemo.oknet.request.IRequest;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.oknet
 * <p>
 * 类 名：Httpscheduler
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public abstract  class Httpscheduler {
    public abstract ICall newCall(IRequest pRequest);

    public  Object execute(ICall call){
        return call.execute();
    };
}
