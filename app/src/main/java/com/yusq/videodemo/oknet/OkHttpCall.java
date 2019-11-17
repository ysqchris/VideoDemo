package com.yusq.videodemo.oknet;

import com.yusq.videodemo.oknet.request.IRequest;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
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

    public OkHttpCall(IRequest pRequest, Call pCall) {
        mCall = pCall;
    }

    @Override
    public Object execute() {
        Response response = null;
        if(mCall != null){
            try  {
             response = mCall.execute();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return response;
    }


}
