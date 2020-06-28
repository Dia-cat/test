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

public class UpdataPickName extends Activity implements View.OnClickListener, Okhtttpcallback {

    EditText phonenum;

    EditText beforename;

    EditText newpickName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatapickname_act);
        InitView();
    }

    @SuppressLint("WrongViewCast")
    private void InitView() {
        phonenum = (EditText) findViewById(R.id.phonenum);
        beforename = (EditText) findViewById(R.id.beforename);
        newpickName = (EditText) findViewById(R.id.newpickName);
        Button surebutton = (Button) findViewById(R.id.surebutton);
        surebutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.surebutton:
                chagePickName();
                break;

        }
    }

    private void chagePickName() {
        String phoneNum = phonenum.getText() + "";
        String beforenameNum = beforename.getText() + "";
        String newpickNameNum = newpickName.getText() + "";
        if (StringUtil.isNullOrEmpty(phoneNum) || StringUtil.isNullOrEmpty(beforenameNum) ||
                StringUtil.isNullOrEmpty(newpickNameNum)) {
            ToastUtils.showToast(this, "请填写完整的信息");
        } else {
            if (!phoneNum.equals(SharedPreferencesUtils.get(this, Constent.PHONE, ""))) {
                ToastUtils.showToast(this, "手机号输入错误");
            } else if (!beforenameNum.equals(SharedPreferencesUtils.get(this, Constent.USERNAME, ""))) {
                ToastUtils.showToast(this, "原昵称输入错误");
            } else {
                OkhttpUntils.postHttpClent(this, Urlutils.UpdataPasswordurl, 0, new String[]{"type", "phoneNum", "userName"},
                        "1", SharedPreferencesUtils.get(this, Constent.PHONE, "") + "", newpickNameNum);
            }
        }
    }

    @Override
    public void fail(int id, String str) {

    }

    @Override
    public void success(int id, String result) {
        if (!result.contains("Error") && result.contains("成功")) {
            SharedPreferencesUtils.put(this, Constent.USERNAME, newpickName.getText() + "");
            ToastUtils.showToast(this, "修改成功");
            finish();
        }
    }
}
