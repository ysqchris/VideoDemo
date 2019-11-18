package com.yusq.videodemo.oknet.response;

import com.google.gson.Gson;
import com.yusq.videodemo.oknet.request.IRequest;
import com.yusq.videodemo.oknet.result.IResult;
import com.yusq.videodemo.oknet.result.ResultData;

import java.lang.reflect.Type;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.base
 * <p>
 * 类 名：DefaultResultParser
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class DefaultResultParser implements IParser{

    private static DefaultResultParser mInstance;

    private DefaultResultParser() {
    }

    public static DefaultResultParser getInstance(){
        if(mInstance == null){
            mInstance = new DefaultResultParser();
        }
        return mInstance;
    }

    @Override
    public IResult parserIResult(IRequest pRequest, IResponse pIResponse) {
        Type type =  pRequest.getType();
        String bodyStr = pIResponse.getBodyString();
        Object object = new Gson().fromJson(bodyStr,type);
        if(object == null){
            return ResultData.failed();
        }else {
          return ResultData.success(object);
        }
    }
}
