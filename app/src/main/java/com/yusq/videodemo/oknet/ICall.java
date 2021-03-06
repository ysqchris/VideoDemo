package com.yusq.videodemo.oknet;

import com.yusq.videodemo.oknet.request.IRequest;
import com.yusq.videodemo.oknet.response.IResponse;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.oknet
 * <p>
 * 类 名：ICall
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public interface ICall {

    IResponse execute();

    IRequest getRequest();
}
