package com.location.voicetest.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.location.voicetest.R;
import com.location.voicetest.base.BaseActivity;
import com.location.voicetest.utils.DataUtils;
import com.location.voicetest.utils.ToastUtils;
import com.location.voicetest.utils.VoicePlayUtils;

public class dTestSimpleActivity extends BaseActivity implements View.OnClickListener {

    private int num = 0;

    private TextView count;

    private String type;

    private Button switchmodle;

    MediaPlayer mMediaPlayer;

    private boolean isPlay = false;

    private ImageView aauditions;

    private int[] english = {R.raw.englishone, R.raw.englishtwo,
            R.raw.englishthree, R.raw.englishfore, R.raw.englishfive};

    private int[] chinese = {R.raw.chineseone, R.raw.chinesetwo, R.raw.chinesethree,
            R.raw.chinesefore, R.raw.chinesefive};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_act);
        initView();
    }

    //初始化控件
    private void initView() {
        type = "chinese";
        count = (TextView) findViewById(R.id.count);
        ImageView updata = (ImageView) findViewById(R.id.updata);
        updata.setOnClickListener(this);
        aauditions = (ImageView) findViewById(R.id.aauditions);
        aauditions.setOnClickListener(this);
        ImageView nextdata = (ImageView) findViewById(R.id.nextdata);
        nextdata.setOnClickListener(this);
        switchmodle = (Button) findViewById(R.id.switchmodle);
        switchmodle.setOnClickListener(this);
        count.setText(DataUtils.chineseOnelineData[0]);
        mMediaPlayer = MediaPlayer.create(this, chinese[0]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextdata://下一曲
                nextData();
                break;
            case R.id.updata://上一曲
                upData();
                break;
            case R.id.aauditions://试听
                isPlay = !isPlay;
                if (num >= chinese.length) {
                    num = chinese.length - 1;
                }
                if (isPlay) {
                    aauditions.setImageResource(R.mipmap.stop);
                    mMediaPlayer.start();
                } else {
                    mMediaPlayer.pause();
                    aauditions.setImageResource(R.mipmap.bofangone);
                }
                break;
            case R.id.switchmodle://切换模式
                switchModle();
                break;
            default:
                break;
        }
    }

    //模式切换
    private void switchModle() {
        mMediaPlayer.stop();
        isPlay = false;
        aauditions.setImageResource(R.mipmap.bofangone);
        num = 0;
        switchmodle.setText("chinese".equals(type) ? "切换中文模式" : "切换英文模式");
        type = "chinese".equals(type) ? "english" : "chinese";
        if (type.equals("chinese")) {
            count.setText(DataUtils.chineseOnelineData[0]);
            mMediaPlayer = MediaPlayer.create(this, chinese[0]);
        } else {
            count.setText(DataUtils.englishOnelineData[0]);
            mMediaPlayer = MediaPlayer.create(this, english[0]);
        }
    }

    //上一曲
    private void upData() {
        if (type.equals("chinese")) {
            num--;
            if (num > 0) {
                mMediaPlayer.stop();
                count.setText(DataUtils.chineseOnelineData[num]);
                mMediaPlayer = MediaPlayer.create(this, chinese[num]);
                aauditions.setImageResource(R.mipmap.bofangone);
                isPlay = false;
            } else {
                num = 0;
                ToastUtils.showToast(this, "已是第一首");
            }
        } else {
            num--;
            if (num > 0) {
                mMediaPlayer.stop();
                count.setText(DataUtils.englishOnelineData[num]);
                mMediaPlayer = MediaPlayer.create(this, english[num]);
                aauditions.setImageResource(R.mipmap.bofangone);
                isPlay = false;
            } else {
                num = 0;
                ToastUtils.showToast(this, "已是第一首");
            }
        }
    }

    //下一曲
    private void nextData() {
        if (type.equals("chinese")) {
            num++;
            if (num < chinese.length) {
                mMediaPlayer.stop();
                count.setText(DataUtils.chineseOnelineData[num]);
                mMediaPlayer = MediaPlayer.create(this, chinese[num]);
                aauditions.setImageResource(R.mipmap.bofangone);
                isPlay = false;
            } else {
                num = chinese.length;
                ToastUtils.showToast(this, "已是最后一首");
            }
        } else {
            num++;
            if (num < english.length) {
                mMediaPlayer.stop();
                count.setText(DataUtils.englishOnelineData[num]);
                mMediaPlayer = MediaPlayer.create(this, english[num]);
                aauditions.setImageResource(R.mipmap.bofangone);
                isPlay = false;
            } else {
                num = english.length;
                ToastUtils.showToast(this, "已是最后一首");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        aauditions.setImageResource(R.mipmap.bofangone);
        isPlay = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayer.stop();
        aauditions.setImageResource(R.mipmap.bofangone);
        isPlay = false;
    }
}
