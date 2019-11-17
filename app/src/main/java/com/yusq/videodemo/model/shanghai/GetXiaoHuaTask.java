package com.yusq.videodemo.model.shanghai;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.Response;

public class GetXiaoHuaTask extends AsyncTask<Object , Object , Object> {

    //运行在子线程
    @Override
    protected Object doInBackground(Object... objects) {
        Object response =  new  ShanghaiDetailHttpTask().getDataList((String) objects[0] , (String) objects[1] , (String) objects[2]);
        return response;
    }

    /**
     * 回到主线程
     * @param o
     */
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Response response = (Response)o;
        try {
            Log.e("CHRIS", "onPostExecute: " + response.body().string());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
