package com.yusq.videodemo.oknet.response;

import com.yusq.videodemo.oknet.request.IRequest;
import com.yusq.videodemo.oknet.result.IResult;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.request
 * <p>
 * 类 名：IParser
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public interface IParser {

    IResult parserIResult(IRequest pRequest, IResponse pIResponse);
}
