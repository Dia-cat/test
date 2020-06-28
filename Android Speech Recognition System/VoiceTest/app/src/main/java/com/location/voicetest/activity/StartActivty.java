package com.location.voicetest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.location.voicetest.MainActivity;
import com.location.voicetest.utils.Constent;
import com.location.voicetest.utils.SharedPreferencesUtils;

public class StartActivty extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String isLogin = SharedPreferencesUtils.get(this, Constent.ISLOGIN, "") + "";
        Intent intent;
        if (isLogin.equals("1")) {//登录
            intent = new Intent(this, MainActivity.class);
        } else {//未登录
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
