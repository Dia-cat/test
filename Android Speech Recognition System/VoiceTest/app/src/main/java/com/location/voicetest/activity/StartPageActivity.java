package com.location.voicetest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.location.voicetest.MainActivity;
import com.location.voicetest.R;
import com.location.voicetest.utils.Constent;
import com.location.voicetest.utils.SharedPreferencesUtils;

public class StartPageActivity extends Activity {

    private Intent intent;

    private String isLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage_act);
        isLogin = SharedPreferencesUtils.get(StartPageActivity.this, Constent.ISLOGIN, "") + "";
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin.equals("1")) {//判断是否登录
                    intent = new Intent(StartPageActivity.this, MainActivity.class);
                } else {//未登录
                    intent = new Intent(StartPageActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
