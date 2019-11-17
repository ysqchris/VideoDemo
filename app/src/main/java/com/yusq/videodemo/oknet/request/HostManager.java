package com.yusq.videodemo.oknet.request;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/16
 * <p>
 * 包 名：com.yusq.videodemo.oknet.request
 * <p>
 * 类 名：HostManager
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public interface HostManager {

    IHost dDataHost = new IHost() {

        @Override
        public String getHost() {
            return "http://v.juhe.cn";
        }

        @Override
        public String getDefaultPath() {
            return "/joke/content/list.php";
        }
    };

}
