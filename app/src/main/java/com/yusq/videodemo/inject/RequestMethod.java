package com.yusq.videodemo.inject;

import android.support.annotation.IntDef;

import static com.yusq.videodemo.inject.RequestMethod.Get;
import static com.yusq.videodemo.inject.RequestMethod.Post;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.inject
 * <p>
 * 类 名：RequestMethod
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */

@IntDef({Get, Post})
public @interface RequestMethod {

    int Get = 1;
    int Post = 2;

}
