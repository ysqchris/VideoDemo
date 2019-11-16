package com.yusq.videodemo.oknet;

import com.yusq.videodemo.oknet.request.IRequest;

import java.util.Map;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.oknet
 * <p>
 * 类 名：HttpHelper
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class HttpHelper {

    private  volatile  static  Httpscheduler httpscheduler;


    public static Object execute(IRequest pRequest, Map<String, Object> pParams) {
         pRequest.setParams(pParams);
         ICall  call = getHttpScheduler().newCall(pRequest);
         return  call;
    }

    private static Httpscheduler getHttpScheduler() {
        if(httpscheduler == null){
            synchronized (HttpHelper.class){
                if(httpscheduler == null){
                    httpscheduler = new OKHttpScheduler();
                }
            }
        }
        return httpscheduler;
    }
}
