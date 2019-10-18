package com.yusq.videodemo.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/7/25/15:03
 * <p>
 * 包 名：com.yusq.videodemo.inject
 * <p>
 * 类 名：
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface SetViewInject {

    int mainLayoutId() ;

}
