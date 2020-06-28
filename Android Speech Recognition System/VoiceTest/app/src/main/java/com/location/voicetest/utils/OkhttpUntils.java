package com.location.voicetest.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zxy on 2017/9/11.
 */

public class OkhttpUntils {

    static Okhtttpcallback okhttpcallbacak;

    public static final int OkHTTPCONNETTIME = 3000;

    public static void getHttpClent(Okhtttpcallback okhttpcallback, String url, String[] keystr, String... date) {
        okhttpcallbacak = okhttpcallback;
        GetBuilder builder = OkHttpUtils.get().url(url);
        for (int i = 0; i < keystr.length; i++) {
            builder.addParams(keystr[i], date[i]);
        }
        builder.build().connTimeOut(OkHTTPCONNETTIME).execute(new CallBack());
    }

    public static void postHttpClent(Okhtttpcallback okhttpcallback, String url, int id, String[] keystr, String... date) {
        okhttpcallbacak = okhttpcallback;
        PostFormBuilder builder = OkHttpUtils.post().url(url).id(id);
        for (int i = 0; i < keystr.length; i++) {
            builder.addParams(keystr[i], date[i]);
        }
        builder.build().execute(new CallBack());
    }

    public static void postFileClient(Okhtttpcallback okhttpcallback, File file, String filename, String url, Map<String, String> params, int id) {
        okhttpcallbacak = okhttpcallback;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "mutipart/form-data");
        OkHttpUtils.post().headers(headers).addFile("file", filename, file)
                .url(url).id(id).params(params).build()
                .execute(new CallBack());
    }

    public static class CallBack extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            okhttpcallbacak.fail(id, e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            okhttpcallbacak.success(id, response);
        }
    }
}
