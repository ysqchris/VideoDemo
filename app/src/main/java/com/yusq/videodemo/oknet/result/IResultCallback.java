package com.yusq.videodemo.oknet.result;

public interface IResultCallback<T> {

    void onSuccess(IResult<T> t);

    void onFailed(IResult t);

}
