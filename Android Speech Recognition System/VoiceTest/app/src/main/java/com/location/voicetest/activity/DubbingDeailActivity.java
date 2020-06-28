package com.location.voicetest.activity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.location.voicetest.R;
import com.location.voicetest.adapter.DubbingDeailAdapter;
import com.location.voicetest.utils.DataUtils;
import com.location.voicetest.utils.VoicePlayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配音练习的详情页面
 */
public class DubbingDeailActivity extends Activity {
    VoicePlayUtils voicePlayUtils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dubbingdeail_act);
        initView();

    }

    //初始化view
    private void initView() {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        voicePlayUtils = new VoicePlayUtils(this);
        List<Map<String, String>> list = new ArrayList<>();
        ListView listv = (ListView) findViewById(R.id.listv);
        VideoView videoView = (VideoView) findViewById(R.id.video);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String type = getIntent().getStringExtra("type");
        Uri uri = null;
        if (type.equals("1")) {
            uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.chinese);
        } else {
            uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.englishvido);
        }
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
        //设置视频路径
        videoView.setVideoURI(uri);
        //开始播放视频
        videoView.start();
        Map<String, String> map;
        for (int i = 0; i < DataUtils.chinesetextData.length; i++) {
            map = new HashMap<>();
            map.put("testdata", DataUtils.englishtextData[i]);
            map.put("fanyidata", DataUtils.chinesetextData[i]);
            list.add(map);
        }
        DubbingDeailAdapter adapter = new DubbingDeailAdapter(list, this, type,voicePlayUtils,videoView);
        listv.setAdapter(adapter);
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(DubbingDeailActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        voicePlayUtils.stopPlay();
    }
}
