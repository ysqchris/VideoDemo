package com.yusq.videodemo.oknet.okhttp;

import com.yusq.videodemo.oknet.ICall;
import com.yusq.videodemo.oknet.request.IRequest;
import com.yusq.videodemo.oknet.response.IResponse;
import com.yusq.videodemo.oknet.okhttp.OkHttpResponse;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.oknet
 * <p>
 * 类 名：OkHttpCall
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class OkHttpCall implements ICall {

    private final Call mCall;
    private final IRequest mIRequest;

    public OkHttpCall(IRequest pRequest, Call pCall) {
        mCall = pCall;
        mIRequest = pRequest;
    }

    @Override
    public IResponse execute() {
        Response response = null;
        if(mCall != null){
            try  {
             response = mCall.execute();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //静态代理
        return new OkHttpResponse(response);
    }

    @Override
    public IRequest getRequest() {
        return mIRequest;
    }
}
