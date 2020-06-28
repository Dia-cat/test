package com.location.voicetest.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;

import com.location.voicetest.R;
import com.location.voicetest.base.BaseActivity;
import com.location.voicetest.base.TheApplication;
import com.location.voicetest.db.green.UserInfoDao;
import com.location.voicetest.dbbean.UserInfo;
import com.location.voicetest.utils.Constent;
import com.location.voicetest.utils.OkhttpUntils;
import com.location.voicetest.utils.Okhtttpcallback;
import com.location.voicetest.utils.SharedPreferencesUtils;
import com.location.voicetest.utils.ToastUtils;
import com.location.voicetest.utils.Urlutils;

public class RegestActivity extends BaseActivity implements Okhtttpcallback, View.OnClickListener {

    private AppCompatEditText acet_1, acet_2, acet_3;
    private AppCompatButton acbtn_click;
    private UserInfoDao userInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_regist);
        inintView();
        userInfoDao = TheApplication.instance.getDaoSession().getUserInfoDao();
    }

    private void inintView() {
        acet_1 = findViewById(R.id.acet_1);
        acet_2 = findViewById(R.id.acet_2);
        acet_3 = findViewById(R.id.acet_3);
        acbtn_click = findViewById(R.id.acbtn_click);
        acbtn_click.setOnClickListener(this);
    }

    @Override
    public void fail(int id, String str) {
        System.out.println("=========str====="+str);
    }

    @Override
    public void success(int id, String result) {
        System.out.println("=========result====="+result);
        if (!result.contains("Error") && result.contains("成功")) {
            ToastUtils.showToast(RegestActivity.this, "注册成功");
            RegestActivity.this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acbtn_click:
                String name = acet_1.getText().toString();
                String pwd = acet_2.getText().toString();
                String phone = acet_3.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(phone)) {
                    ToastUtils.showToast(RegestActivity.this, "请填写完整信息");
                } else {
                    String userName = SharedPreferencesUtils.get(RegestActivity.this, Constent.USERNAME, "") + "";
                    String phoneNum = SharedPreferencesUtils.get(RegestActivity.this, Constent.PHONE, "") + "";
//                    if (phone.equals(phoneNum)) {
//                        ToastUtils.showToast(RegestActivity.this, "该用户已存在");
//                    } else {
                        OkhttpUntils.postHttpClent(this, Urlutils.Registerurl, 0, new String[]{"userName", "phoneNum", "password"}, name, phone, pwd);
                        SharedPreferencesUtils.put(RegestActivity.this, Constent.USERNAME, name);
                        SharedPreferencesUtils.put(RegestActivity.this, Constent.PASSWORD, pwd);
                        SharedPreferencesUtils.put(RegestActivity.this, Constent.PHONE, phone);
      //              }
                }
        }


    }
}
