package com.yusq.videodemo.bean;

import java.util.ArrayList;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/18
 * <p>
 * 包 名：com.yusq.videodemo.bean
 * <p>
 * 类 名：ShangHaiDetailBean
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ShangHaiDetailBean {

    public int error_code;
    public String reason;
    public ResultListBean result;

    public static class ResultListBean {
      public ArrayList<ResultBean> data;
    }

    public static class ResultBean {
        public String content;
        public String hashId;
        public String unixtime;
        public String updatetime;
    }
}
