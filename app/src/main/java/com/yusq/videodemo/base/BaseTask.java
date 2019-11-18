package com.yusq.videodemo.base;

import com.yusq.videodemo.oknet.result.IResultCallback;
import com.yusq.videodemo.oknet.result.IResult;
import com.yusq.videodemo.oknet.result.ResultData;
import com.yusq.videodemo.oknet.task.LfTask;

/**
 *
 * @param <T>
 */
public abstract class BaseTask<T> extends LfTask<IResult<T>> implements IResultCallback<T> {

    @Override
    public void onComplete(IResult<T> tiResult) {
          if(tiResult != null){
              if(tiResult.isSuccess()) {
                  onSuccess(tiResult);
              }else {
                  onFailed(ResultData.failed());
              }
          }else {
              onFailed(ResultData.failed());
          }
    }

    @Override
    public void onFailed(IResult t) {

    }

    @Override
    public void onException(Throwable pThrowable) {
        onFailed(ResultData.failed());
    }
}
