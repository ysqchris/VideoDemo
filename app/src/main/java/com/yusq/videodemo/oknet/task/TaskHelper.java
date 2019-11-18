package com.yusq.videodemo.oknet.task;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.task
 * <p>
 * 类 名：TaskHelper
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class TaskHelper {

    public static void submitTask(ITaskBackground pTaskBackground , ITaskCallback pITaskCallback){
       AsyTaskInstance taskInstance =  AsyTaskInstance.getInstance(pTaskBackground , pITaskCallback);
       // 构建线程池管理器
       TaskScheduler.getInstance().submit(taskInstance);
    }

}
