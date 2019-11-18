package com.yusq.videodemo.model.shanghai;

import com.yusq.videodemo.oknet.LfHttpServer;
import com.yusq.videodemo.oknet.result.IResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.model.shanghai
 * <p>
 * 类 名：ShanghaiDetailHttpTask
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ShanghaiDetailHttpTask extends LfHttpServer {

    public IResult getDataList(String sort , String page, String pageSize){
        Map<String , Object> params = new HashMap<>();
        params.put("sort" , sort);
        params.put("page" , page);
        params.put("pagesize", pageSize);
        params.put("time" , System.currentTimeMillis()/1000);
        params.put("key" , "106532700bad94dc971e41ff6d0e3ae4");
        return  super.execute(ShangHaiDetailRequest.xiaohuaRequest, params);
    }

}
