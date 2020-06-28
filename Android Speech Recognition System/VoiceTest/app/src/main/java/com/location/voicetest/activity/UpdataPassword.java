package com.location.voicetest.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.location.voicetest.R;
import com.location.voicetest.utils.Constent;
import com.location.voicetest.utils.OkhttpUntils;
import com.location.voicetest.utils.Okhtttpcallback;
import com.location.voicetest.utils.SharedPreferencesUtils;
import com.location.voicetest.utils.StringUtil;
import com.location.voicetest.utils.ToastUtils;
import com.location.voicetest.utils.Urlutils;

/**
 * 修改登录密码
 */
public class UpdataPassword extends Activity implements View.OnClickListener, Okhtttpcallback {

    EditText phonenum;//手机号
    EditText beforepaw;//原密码
    EditText newpass;//新密码
    EditText surepass;//确认密码
    String newPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatapassword_act);
        initView();
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        phonenum = (EditText) findViewById(R.id.phonenum);
        beforepaw = (EditText) findViewById(R.id.beforepaw);
        newpass = (EditText) findViewById(R.id.newpass);
        surepass = (EditText) findViewById(R.id.surepass);
        Button surebutton = (Button) findViewById(R.id.surebutton);
        surebutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.surebutton:
                updatePassword();
                break;
            default:
                break;
        }
    }

    //更新密码
    private void updatePassword() {
        String phoneNum = phonenum.getText() + "";
        String beforepass = beforepaw.getText() + "";
        newPass = newpass.getText() + "";
        String surePass = surepass.getText() + "";
        if (StringUtil.isNullOrEmpty(phoneNum) || StringUtil.isNullOrEmpty(beforepass) ||
                StringUtil.isNullOrEmpty(newPass) || StringUtil.isNullOrEmpty(surePass)) {
            ToastUtils.showToast(this, "请填写完整的信息");
        } else {
            if (!phoneNum.equals(SharedPreferencesUtils.get(this, Constent.PHONE, ""))) {
                ToastUtils.showToast(this, "手机号错误");
            } else if (!beforepass.equals(SharedPreferencesUtils.get(this, Constent.PASSWORD, ""))) {
                ToastUtils.showToast(this, "旧密码错误");
            } else if (!newPass.equals(surePass)) {
                ToastUtils.showToast(this, "两次输入的新密码不相同");
            } else {
                OkhttpUntils.postHttpClent(this, Urlutils.UpdataPasswordurl, 0, new String[]{"type", "phoneNum", "newPass"},
                        "2", SharedPreferencesUtils.get(this, Constent.PHONE, "") + "", newPass);

            }
        }
    }

    @Override
    public void fail(int id, String str) {

    }

    @Override
    public void success(int id, String result) {
        System.out.println("=============result===" + result);
        if (!result.contains("Error") && result.contains("成功")) {
            SharedPreferencesUtils.put(this, Constent.PASSWORD, newPass);
            ToastUtils.showToast(this, "密码修改成功");
        }
    }
}
