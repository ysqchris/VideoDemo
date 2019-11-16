package com.yusq.videodemo.oknet;

import com.yusq.videodemo.inject.RequestMethod;
import com.yusq.videodemo.oknet.request.IRequest;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.oknet
 * <p>
 * 类 名：OKHttpScheduler
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
class OKHttpScheduler extends Httpscheduler {

    private OkHttpClient client;
    Request.Builder  requestBuild = new Request.Builder();

    @Override
    public ICall newCall(IRequest pRequest) {
        Map<String , Object> params = pRequest.getParams();
        int requestMethod  = pRequest.getRequestMethod();
        switch (requestMethod){
            case RequestMethod.Get:
                // 拼接Get请求 utl(host+path)
                StringBuilder urlStringBuild = new StringBuilder(pRequest.getHost().getHost());
                urlStringBuild.append(pRequest.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(urlStringBuild.toString()).newBuilder();
                if(params != null && params.size() > 0){
                    Iterator<Map.Entry<String , Object>>  iterator = params.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String , Object> next = iterator.next();
                        // TODO: 2019/11/16 object 转成 String
                        urlBuilder.addQueryParameter(next.getKey(), String.valueOf(next.getValue()));
                    }
                }
                requestBuild.get().url(urlBuilder.build());
                break;
            case RequestMethod.Post:

                break;
        }
        Request  httpRequest = requestBuild.build();
        Call call =  getClient().newCall(httpRequest);
        OkHttpCall okHttpCall = new OkHttpCall(pRequest ,call);
        return okHttpCall;
    }

    private OkHttpClient getClient() {
        if(client == null){
            client = new OkHttpClient();
        }
        return client;
    }
}
