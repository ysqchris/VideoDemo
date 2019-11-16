package com.yusq.videodemo.model.shanghai;

import com.yusq.videodemo.base.IDataRequest;
import com.yusq.videodemo.inject.RequestMethod;
import com.yusq.videodemo.oknet.request.IRequest;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.model.shanghai
 * <p>
 * 类 名：ShangHaiDetailRequest
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public interface ShangHaiDetailRequest {

    IRequest  xiaohuaRequest = IDataRequest.sendHttp("path" , RequestMethod.Get);

}
