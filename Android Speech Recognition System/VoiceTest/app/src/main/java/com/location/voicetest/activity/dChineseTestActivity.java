package com.location.voicetest.activity;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.location.voicetest.R;
import com.location.voicetest.dialog.CommonDialog;
import com.location.voicetest.utils.Constent;
import com.location.voicetest.utils.DataUtils;
import com.location.voicetest.utils.OkhttpUntils;
import com.location.voicetest.utils.Okhtttpcallback;
import com.location.voicetest.utils.SharedPreferencesUtils;
import com.location.voicetest.utils.SoundRecordingUtils;
import com.location.voicetest.utils.StringUtil;
import com.location.voicetest.utils.ToastUtils;
import com.location.voicetest.utils.Urlutils;
import com.location.voicetest.utils.Utils;
import com.location.voicetest.utils.VoicePlayUtils;

import java.util.List;
import java.util.Map;

public class dChineseTestActivity extends Activity implements View.OnClickListener, CommonDialog.OnDialogClickListener, Okhtttpcallback {

    private EditText edit_content;//输入编辑框

    private int num = 0;//当前在线数据的角标

    private String type;//当前类型  1为中文  2为英文

    private TextView scoreTv;

    private VoicePlayUtils voicePlayUtils;

    private boolean isPlay = false;

    private ImageView play;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chinesetest_act);
        initView();
    }

    private void initView() {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        type = getIntent().getStringExtra("type");
        TextView online = (TextView) findViewById(R.id.online);
        online.setOnClickListener(this);
        edit_content = (EditText) findViewById(R.id.edit_content);
        ImageView volumeimg = (ImageView) findViewById(R.id.volumeimg);
        volumeimg.setOnClickListener(this);
        ImageView soundRecording = (ImageView) findViewById(R.id.soundRecording);
        soundRecording.setOnClickListener(this);
        play = (ImageView) findViewById(R.id.play);
        play.setOnClickListener(this);
        TextView resultuploading = (TextView) findViewById(R.id.resultuploading);
        resultuploading.setOnClickListener(this);
        scoreTv = (TextView) findViewById(R.id.score);
        voicePlayUtils = new VoicePlayUtils(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.online://在线生成测试内容
                onlineGeneration();
                break;
            case R.id.play://开始播放
                isPlay = !isPlay;
                if (isPlay)
                    startPlay();
                else
                    stopPlay();
                break;
            case R.id.soundRecording: //开始录音
                startSoundRecording();
                break;
            case R.id.volumeimg://音量
                AudioManager am = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
                am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                am.adjustStreamVolume(AudioManager.STREAM_DTMF, AudioManager.ADJUST_RAISE, 0);
                break;
            case R.id.resultuploading://上传得分
                showDilogText();
                break;
            default:
                break;
        }
    }

    private void stopPlay() {
        play.setImageResource(R.mipmap.bofangone);
        voicePlayUtils.stopPlay();
    }

    // 上传分数的dilog
    private void showDilogText() {
        String score = scoreTv.getText().toString();
        if (StringUtil.isNullOrEmpty(score) || scoreTv.getVisibility() == View.GONE) {
            ToastUtils.showToast(dChineseTestActivity.this, "您还没有进行录音测试");
        } else {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(dChineseTestActivity.this, "", "确定提交分数吗？");
            commonDialog.setOnDialogClickListener(this);
        }

    }

    //开始录音
    private void startSoundRecording() {
        String content = edit_content.getText().toString().trim();
        if (!StringUtil.isNullOrEmpty(content)) {
            SoundRecordingUtils recordingUtils = new SoundRecordingUtils(this, null, scoreTv);
            if (content.length() > 50) {
                content = content.substring(0, 50);
            }
            recordingUtils.soundRecording(content);
        } else {
            ToastUtils.showToast(this, "请填加要测试的内容");
        }

    }

    // 开始播放
    private void startPlay() {
        play.setImageResource(R.mipmap.stop);
        String content = edit_content.getText() + "";
        if (!StringUtil.isNullOrEmpty(content)) {
            voicePlayUtils.startPlay(edit_content.getText() + "");
        } else {
            ToastUtils.showToast(this, "请填加要测试的内容");
        }
    }

    //在线生成测试内容
    private void onlineGeneration() {
        play.setImageResource(R.mipmap.bofangone);
        isPlay = false;
        voicePlayUtils.stopPlay();
        scoreTv.setVisibility(View.GONE);
        if (num < DataUtils.chineseOnelineData.length) {
            if (type.equals("1"))
                edit_content.setText(DataUtils.chineseOnelineData[num]);
            else
                edit_content.setText(DataUtils.englishOnelineData[num]);
            num++;
        } else {
            ToastUtils.showToast(this, "已是在线的最后一条数据");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        voicePlayUtils.stopPlay();
    }

    @Override
    public void onSureClick() {//确认提交
        List<Map<String, String>> list = Utils.saveDataToList(this, type.equals("1") ? "中文测试" : "英文测试", scoreTv.getText().toString());
        Utils.saveInfo(this, list);
        OkhttpUntils.postHttpClent(this, Urlutils.updatarecordurl, 0, new String[]{"recordType", "recordTime", "recordScore", "phoneNum"},
                type.equals("1") ? "中文测试" : "英文测试", System.currentTimeMillis() + "", scoreTv.getText().toString(), SharedPreferencesUtils.get(this, Constent.PHONE, "") + "");
    }

    @Override
    public void onCancleClick() {

    }

    @Override
    public void fail(int id, String str) {

    }

    @Override
    public void success(int id, String result) {
        System.out.println("===================result======" + result);
        if (!result.contains("Error")&&result.contains("成功")){
            ToastUtils.showToast(dChineseTestActivity.this,"分数提交成功");
        }
    }
}
