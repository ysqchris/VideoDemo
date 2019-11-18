package com.yusq.videodemo.oknet;

import com.yusq.videodemo.oknet.request.IRequest;
import com.yusq.videodemo.oknet.response.IParser;
import com.yusq.videodemo.oknet.response.IResponse;
import com.yusq.videodemo.oknet.result.IResult;

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
public abstract  class  Httpscheduler{
    public abstract ICall newCall(IRequest pRequest);

    public IResult execute(ICall call){
        // IResponse 转换成 result
        IResponse iResponse = call.execute();
        IRequest iRequest = call.getRequest();
        IParser parser = iRequest.getIParser();
        return parser.parserIResult(iRequest,iResponse);
    }
}
