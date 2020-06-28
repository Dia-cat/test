package com.location.voicetest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;

import com.location.voicetest.MainActivity;
import com.location.voicetest.R;
import com.location.voicetest.base.BaseActivity;
import com.location.voicetest.base.TheApplication;
import com.location.voicetest.bean.User;
import com.location.voicetest.db.green.UserInfoDao;
import com.location.voicetest.dbbean.UserInfo;
import com.location.voicetest.utils.Constent;
import com.location.voicetest.utils.OkhttpUntils;
import com.location.voicetest.utils.Okhtttpcallback;
import com.location.voicetest.utils.SharedPreferencesUtils;
import com.location.voicetest.utils.StringUtil;
import com.location.voicetest.utils.ToastUtils;
import com.location.voicetest.utils.Urlutils;
import com.location.voicetest.view.TitleWidget;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener, Okhtttpcallback {
    private AppCompatButton login;
    private AppCompatEditText userName, passWord;
    private TitleWidget title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inintView();
    }

    private void inintView() {
        login = findViewById(R.id.login);
        userName = findViewById(R.id.userName);
        passWord = findViewById(R.id.passWord);
        login.setOnClickListener(this);
        title = findViewById(R.id.title);
        title.setSubmitListener(new TitleWidget.onSubmitListener() {
            @Override
            public void onSubmit(View paramView) {
                Intent intent = new Intent(LoginActivity.this, RegestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login://登录
                String name = userName.getText().toString();
                String pwd = passWord.getText().toString();
                if (StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(pwd)) {
                    ToastUtils.showToast(LoginActivity.this, "请输入用户名和密码");
                } else {
                    OkhttpUntils.postHttpClent(this, Urlutils.LoginUrl,
                            0, new String[]{"passWord", "phoneNum"}, pwd, name);
                    SharedPreferencesUtils.put(this, Constent.PHONE, name);
                    SharedPreferencesUtils.put(this, Constent.PASSWORD, pwd);
//                    String userName = SharedPreferencesUtils.get(LoginActivity.this, Constent.USERNAME, "") + "";
//                    String phoneNum = SharedPreferencesUtils.get(LoginActivity.this, Constent.PHONE, "") + "";
//                    String passWord = SharedPreferencesUtils.get(LoginActivity.this, Constent.PASSWORD, "") + "";
//                    if (name.equals(userName) || name.equals(phoneNum) && pwd.equals(passWord)) {
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        LoginActivity.this.finish();
//                        SharedPreferencesUtils.put(LoginActivity.this, Constent.ISLOGIN, "1");
//                    } else {
//                        ToastUtils.showToast(LoginActivity.this, "账号密码错误");
//                    }
                }
                break;
        }
    }

    @Override
    public void fail(int id, String str) {
        System.out.println("===========str===" + str);
    }

    @Override
    public void success(int id, String result) {
        System.out.println("==========result====" + result);
        if (!result.contains("Error") && result.contains("成功")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
            ToastUtils.showToast(LoginActivity.this, "登录成功");
            SharedPreferencesUtils.put(LoginActivity.this, Constent.ISLOGIN, "1");
        } else {
            ToastUtils.showToast(LoginActivity.this, "账号密码错误");
        }
    }
}
