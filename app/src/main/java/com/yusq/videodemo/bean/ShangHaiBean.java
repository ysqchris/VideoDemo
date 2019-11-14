package com.yusq.videodemo.bean;

import java.util.ArrayList;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/14
 * <p>
 * 包 名：com.yusq.videodemo.bean
 * <p>
 * 类 名：ShangHaiBean
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ShangHaiBean {

    private int mItemType = IShangHaiItemType.VERTICAL;
    private String mDec;
    private boolean isShowImg;
    private ArrayList<ShangHaiBean> hListData;

    public int getItemType() {
        return mItemType;
    }

    public ShangHaiBean setItemType(int pItemType) {
        mItemType = pItemType;
        return this;
    }

    public String getDec() {
        return mDec;
    }

    public ShangHaiBean setDec(String pDec) {
        mDec = pDec;
        return this;
    }

    public boolean isShowImg() {
        return isShowImg;
    }

    public ShangHaiBean setShowImg(boolean pShowImg) {
        isShowImg = pShowImg;
        return this;
    }

    public ArrayList<ShangHaiBean> gethListData() {
        return hListData;
    }

    public ShangHaiBean sethListData(ArrayList<ShangHaiBean> pHListData) {
        hListData = pHListData;
        return this;
    }

    public interface IShangHaiItemType{
        int VERTICAL = 0;
        int HORIZANTAL = 1;
    }
}
