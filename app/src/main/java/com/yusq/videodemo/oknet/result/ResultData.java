package com.yusq.videodemo.oknet.result;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.oknet.result
 * <p>
 * 类 名：ResultData
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ResultData<T> implements IResult{

    private  static  int CODE_200 = 200;
    private  static  int CODE_404 = 404;

    protected T data;
    private  int code;
    private  String message;
    private boolean isSuccess;


    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    public static IResult failed() {
        ResultData resultData = new ResultData();
        resultData.code = CODE_404;
        resultData.isSuccess = false;
        return resultData;

    }

    public static IResult success(Object object) {
        ResultData resultData = new ResultData();
        resultData.data = object;
        resultData.code = CODE_200;
        resultData.isSuccess = true;
        return resultData;

    }

    @Override
    public T getData() {
        return data;
    }
}
