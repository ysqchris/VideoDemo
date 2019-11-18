package com.yusq.videodemo.oknet.okhttp;

import com.yusq.videodemo.oknet.response.IResponse;

import java.io.IOException;

import okhttp3.Response;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet
 * <p>
 * 类 名：OkHttpResponse
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class OkHttpResponse implements IResponse {

    private Response  mResponse;

    public OkHttpResponse(Response pResponse) {
       mResponse = pResponse;
    }

    @Override
    public String getBodyString() {
        try {
            if (mResponse != null) {
                return mResponse.body().string();
            }
        }catch (IOException e){
            e.fillInStackTrace();
        }
        return null;
    }
}
