package com.location.voicetest.utils;

/**
 * Created by zxy on 2017/9/11.
 */

public interface Okhtttpcallback {

    void fail(int id, String str);

    void success(int id, String result);
}
