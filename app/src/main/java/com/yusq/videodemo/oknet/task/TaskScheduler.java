package com.yusq.videodemo.oknet.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.yusq.videodemo.oknet.tools.ThreadUtil;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.task
 * <p>
 * 类 名：TaskScheduler
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class TaskScheduler {

    private static  TaskScheduler  instance;
    private final Handler handler;
    private PriorityThreadPoolExecutor mExecutor;

    private static int CORE_PROCESS = ThreadUtil.getCoreCpuNum() + 1;
    private static int MAX_CORE_PROCESS = CORE_PROCESS * 3;
    private static int KEEP_ALIVE_TIME = 1;



    interface ITaskSchedulerType{
        int SUBMIT_TASK = 0;
    }

    private TaskScheduler(){
        //用于消息调度的线程
        HandlerThread  handlerThread = new HandlerThread("task_scheduler");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case ITaskSchedulerType.SUBMIT_TASK:
                        doSubmitTask(msg.obj);
                        break;
                }
                return false;
            }
        });
        BlockingQueue<Runnable> poolQueue = new LinkedBlockingDeque<>();
        //创建一个线程池
        mExecutor = new PriorityThreadPoolExecutor(
                CORE_PROCESS,
                MAX_CORE_PROCESS,
                KEEP_ALIVE_TIME,
                TimeUnit.MINUTES,
                poolQueue,
                new TaskThreadFactory());

    }

    private void doSubmitTask(Object pObj) {
        if(pObj instanceof AsyTaskInstance){
            mExecutor.submit((AsyTaskInstance) pObj);
        }
    }

    public static TaskScheduler  getInstance(){
        if (instance == null){
            synchronized (TaskScheduler.class){
                instance = new TaskScheduler();
            }
        }
        return  instance;
    }

    public void submit(AsyTaskInstance pTaskInstance) {
        handler.sendMessage(handler.obtainMessage(ITaskSchedulerType.SUBMIT_TASK , pTaskInstance));
    }
}

