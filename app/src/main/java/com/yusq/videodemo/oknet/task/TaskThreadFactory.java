package com.yusq.videodemo.oknet.task;

import java.util.concurrent.ThreadFactory;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.task
 * <p>
 * 类 名：TaskThreadFactory
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class TaskThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"task_thread_pool");
    }
}
