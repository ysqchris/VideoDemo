package com.yusq.videodemo.base;

import com.yusq.videodemo.inject.RequestMethod;
import com.yusq.videodemo.oknet.request.IRequest;
import com.yusq.videodemo.oknet.request.HostManager;
import com.yusq.videodemo.oknet.request.LfRequest;
import com.yusq.videodemo.oknet.response.DefaultResultParser;

import java.lang.reflect.Type;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.imlp
 * <p>
 * 类 名：IDataRequest
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class IDataRequest extends LfRequest {
    public static IRequest sendHttp(String path, @RequestMethod int requestMethod, Type type){
        IDataRequest request = new IDataRequest();
        request.mIHost = HostManager.dDataHost;
        request.mPath = path;
        request.mRequestMethod = requestMethod;
        request.type = type;
        request.resultParser = DefaultResultParser.getInstance();
        return request;
    }
}
