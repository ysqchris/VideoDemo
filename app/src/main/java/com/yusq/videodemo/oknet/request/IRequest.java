package com.yusq.videodemo.oknet.request;

import java.util.Map;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.oknet
 * <p>
 * 类 名：IRequest
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public interface IRequest {

    void setParams(Map<String  , Object> pParams);

    Map<String , Object> getParams();

    int getRequestMethod();

    IHost getHost();

    String getPath();

    void setPath(String pPath);


}
