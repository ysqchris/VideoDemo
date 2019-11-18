package com.yusq.videodemo.oknet.task;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.task
 * <p>
 * 类 名：ITaskCallback
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public interface ITaskCallback<Result> {

    void onComplete(Result result);

    void onException(Throwable pThrowable);

}
