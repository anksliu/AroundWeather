package com.example.lks.aroundweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lks on 2017/3/11.
 */

public class HttpUtil {
    public static void sendOkhttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
