package com.location.voicetest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.location.voicetest.R;
import com.location.voicetest.utils.StringUtil;
import com.location.voicetest.utils.ToastUtils;

/**
 * 意见反馈
 */
public class IdeaFankuiActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ideafankui_act);
        final EditText edit_content=(EditText)findViewById(R.id.edit_content);
        Button Submission=(Button)findViewById(R.id.Submission);
        Submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isNullOrEmpty(edit_content.getText()+"")){
                    ToastUtils.showToast(IdeaFankuiActivity.this,"请输入要反馈的内容");
                }else {
                    ToastUtils.showToast(IdeaFankuiActivity.this,"反馈成功，谢谢您的建议");
                }
            }
        });
    }
}
