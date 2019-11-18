package com.yusq.videodemo.oknet.result;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.result
 * <p>
 * 类 名：IResult
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：IResponse 解析后的结果
 */
public interface IResult<T>{

      boolean isSuccess();

      T getData();
}
