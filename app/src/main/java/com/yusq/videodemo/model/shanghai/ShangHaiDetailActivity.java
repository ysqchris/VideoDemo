package com.yusq.videodemo.model.shanghai;

import android.util.Log;

import com.yusq.videodemo.R;
import com.yusq.videodemo.base.BaseActivity;
import com.yusq.videodemo.inject.SetViewInject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/15
 * <p>
 * 包 名：com.yusq.videodemo.model
 * <p>
 * 类 名：ShangHaiDetailActivity
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
@SetViewInject(mainLayoutId = R.layout.shanghai_detail_layout)
public class ShangHaiDetailActivity extends BaseActivity {

    @Override
    public void afterBindView() {
        initGetNetData();
    }

    /**
     * 网络请求
     */
    private void initGetNetData() {

        new ShanghaiDetailHttpTask().getDataList("desc" , "1" , "2");


//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url("http://www.baidu.com").get().build();
//        Call call =  client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call pCall, @NotNull IOException pE) {
//                Log.e("CHRIS", "onResponse: "+ pE.fillInStackTrace());
//            }
//
//            @Override
//            public void onResponse(@NotNull Call pCall, @NotNull Response pResponse) throws IOException {
//                Log.e("CHRIS", "onResponse: "+ pResponse.body().string());
//            }
//        });
    }
}
