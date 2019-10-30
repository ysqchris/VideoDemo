package com.yusq.videodemo.constant;


import android.support.annotation.IntDef;

import static com.yusq.videodemo.constant.LocalConstants.BEI_JIN;
import static com.yusq.videodemo.constant.LocalConstants.HANG_ZHOU;
import static com.yusq.videodemo.constant.LocalConstants.SHANG_HAI;
import static com.yusq.videodemo.constant.LocalConstants.SHENG_ZHENG;

@IntDef({SHANG_HAI , HANG_ZHOU, BEI_JIN,SHENG_ZHENG})
public @interface LocalConstants {

    int SHANG_HAI = 0;
    int HANG_ZHOU = 1;
    int BEI_JIN = 2;
    int SHENG_ZHENG = 3;



}
