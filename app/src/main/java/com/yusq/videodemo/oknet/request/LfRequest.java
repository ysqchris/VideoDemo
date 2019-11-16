package com.yusq.videodemo.oknet.request;

import android.text.TextUtils;

import com.yusq.videodemo.inject.RequestMethod;

import java.nio.file.Path;
import java.util.Map;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.oknet.request
 * <p>
 * 类 名：LfRequest
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class LfRequest implements IRequest {

    // 请求地址
    protected IHost mIHost;

    // 请求方法
    @RequestMethod
    protected int mRequestMethod;

    //请求参数
    protected Map<String, Object> mParams;

    //请求地址路径
    protected String mPath;

    public void setParams(Map<String, Object> pParams) {
        mParams = pParams;
    }

    @Override
    public Map<String, Object> getParams() {
        return mParams;
    }

    @Override
    public int getRequestMethod() {
        return mRequestMethod;
    }

    @Override
    public IHost getHost() {
        return mIHost;
    }

    @Override
    public String getPath() {
        if(TextUtils.isEmpty(mPath)){
           if(mIHost != null){
             return  mIHost.getDefaultPath();
           }
        }
        return mPath;
    }

    @Override
    public void setPath(String path) {
       mPath = path;
    }
}
