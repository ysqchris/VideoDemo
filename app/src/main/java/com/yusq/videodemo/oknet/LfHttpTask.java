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
 * 类 名：LfHttpTask
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class LfHttpTask {

    protected  Object execute(IRequest pRequest  , Map<String, Object> params){
        return HttpHelper.execute(pRequest , params);
    }

}
