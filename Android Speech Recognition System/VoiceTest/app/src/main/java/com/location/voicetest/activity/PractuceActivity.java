package com.location.voicetest.activity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.location.voicetest.R;
import com.location.voicetest.utils.DataUtils;
import com.location.voicetest.utils.SoundRecordingUtils;
import com.location.voicetest.utils.ToastUtils;
import com.location.voicetest.utils.VoicePlayUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PractuceActivity extends Activity implements View.OnClickListener {

    private String type;//测试的类型

    private String[] practuceData;//数据值

    private TextView practucedata;//测试数据

    private int num = 1;//记录当前题号

    private ProgressBar bar;//进度条

    TextView progresstv; //当前题的num

    TextView toalnum;//题目总数

    private TextView scoreTv;//分数

    private TextView charTv;//拼音

    GradientDrawable background;

    SoundRecordingUtils soundRecordingutil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practuce_act);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if (type.equals("2")) {
            practuceData = DataUtils.chineseData;
        } else if (type.equals("1")) {
            practuceData = DataUtils.englishData;
        } else {
            practuceData = DataUtils.randomData;
        }
        initView();
    }

    private void initView() {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //练习的题目
        practucedata = (TextView) findViewById(R.id.practucedata);
        practucedata.setText(practuceData[0]);
        //下一题
        TextView next = (TextView) findViewById(R.id.next);
        next.setOnClickListener(this);
        //进度条
        bar = (ProgressBar) findViewById(R.id.bar);
        //当前题的num
        progresstv = (TextView) findViewById(R.id.progresstv);
        progresstv.setText("1");
        //题目总数
        toalnum = (TextView) findViewById(R.id.toalnum);
        toalnum.setText("/" + practuceData.length + "");
        //播放按钮
        ImageView play = (ImageView) findViewById(R.id.play);
        play.setOnClickListener(this);
        //开始录音
        ImageView soundRecording = (ImageView) findViewById(R.id.soundRecording);
        soundRecording.setOnClickListener(this);
        //音量
        ImageView volumeimg=(ImageView)findViewById(R.id.volumeimg);
        volumeimg.setOnClickListener(this);
        //分数
        scoreTv = (TextView) findViewById(R.id.score);
        //拼音
        charTv = (TextView) findViewById(R.id.charTv);
        background = (GradientDrawable) scoreTv.getBackground();
        if (type.equals("2")) {
            String[] cStrHY = PinyinHelper.toHanyuPinyinStringArray(practuceData[0].toCharArray()[0]);
            charTv.setText("[" + cStrHY[0] + "]");
        } else {
            charTv.setVisibility(View.GONE);
        }
        soundRecordingutil = new SoundRecordingUtils(this, charTv, scoreTv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next://下一题
                nextData();
                break;
            case R.id.play://播放
                VoicePlayUtils voicePlayUtils = new VoicePlayUtils(PractuceActivity.this);
                voicePlayUtils.startPlay(practucedata.getText() + "");
                break;
            case R.id.soundRecording://录音
                soundRecordingutil.soundRecording(practucedata.getText() + "");
                break;
            case R.id.volumeimg:
                    AudioManager am = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
                    am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                    am.adjustStreamVolume(AudioManager.STREAM_DTMF, AudioManager.ADJUST_RAISE, 0);
                    break;
            default:
                break;
        }
    }
    //下一题
    private void nextData() {
        scoreTv.setVisibility(View.GONE);
        if (num < practuceData.length) {
            practucedata.setText(practuceData[num]);
            soundRecordingutil.checkTextStr(practuceData[num]);
            num++;
            bar.setProgress((int) (num * 100 / practuceData.length));
            progresstv.setText(num + "");
        } else {
            bar.setProgress(100);
            ToastUtils.showToast(PractuceActivity.this, "已是最后一题");
        }

    }

}
