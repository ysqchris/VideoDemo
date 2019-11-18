package com.yusq.videodemo.oknet.task;

import com.yusq.videodemo.oknet.tools.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.task
 * <p>
 * 类 名：AsyTaskInstance
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class AsyTaskInstance<Result> extends FutureTask<Result> {

    private final ITaskBackground mTaskBackground;
    private final ITaskCallback mITaskCallback;

    public AsyTaskInstance(ITaskBackground<Result> pTaskBackground, ITaskCallback<Result> pITaskCallback ) {
        super(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                return pTaskBackground.doingBackground();
            }
        });
        mTaskBackground = pTaskBackground;
        mITaskCallback = pITaskCallback;
    }

    @Override
    protected void done() {
       if(mITaskCallback != null){
           try {
               Result o = get();
               if(o != null){
                   ThreadUtil.postMainThread(new Runnable() {
                       @Override
                       public void run() {
                           mITaskCallback.onComplete(o);
                       }
                   });
               }
           } catch (Exception pE) {
               pE.printStackTrace();
           }
       }
    }

    @Override
    protected void setException(Throwable t) {
        super.setException(t);
        if(mITaskCallback != null){
            ThreadUtil.postMainThread(new Runnable() {
                @Override
                public void run() {
                    mITaskCallback.onException(t);
                }
            });
        }
    }

    public static AsyTaskInstance getInstance(ITaskBackground pTaskBackground, ITaskCallback pITaskCallback){
        return new AsyTaskInstance(pTaskBackground , pITaskCallback);
    }
}
