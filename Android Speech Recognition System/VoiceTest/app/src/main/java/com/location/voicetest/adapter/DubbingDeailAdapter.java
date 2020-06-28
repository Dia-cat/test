package com.location.voicetest.adapter;

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.location.voicetest.R;
import com.location.voicetest.utils.SoundRecordingUtils;
import com.location.voicetest.utils.VoicePlayUtils;

import java.util.List;
import java.util.Map;

public class DubbingDeailAdapter extends BaseAdapter {

    List<Map<String, String>> list;

    Context context;

    LayoutInflater mInflater;

    String type;

    VoicePlayUtils voicePlayUtils;

    VideoView videoView;

    public DubbingDeailAdapter(List<Map<String, String>> list, Context context, String type, VoicePlayUtils voicePlayUtils, VideoView videoView) {
        this.context = context;
        this.list = list;
        this.type = type;
        this.voicePlayUtils = voicePlayUtils;
        this.videoView = videoView;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.dubbingdeailitme, null);
        final TextView state = (TextView) convertView.findViewById(R.id.state);
        TextView tilenum = (TextView) convertView.findViewById(R.id.tilenum);
        tilenum.setText("" + (position + 1) + "/ " + list.size() + "");
        final TextView dubbingScore = (TextView) convertView.findViewById(R.id.dubbingScore);
        final TextView englishtext = (TextView) convertView.findViewById(R.id.englishtext);
        TextView chinesetext = (TextView) convertView.findViewById(R.id.chinesetext);
        final ImageView playImg = (ImageView) convertView.findViewById(R.id.play);
        ImageView soundRecording = (ImageView) convertView.findViewById(R.id.soundRecording);
        ImageView volumeImg = (ImageView) convertView.findViewById(R.id.volume);
        if (type.equals("1")) {
            englishtext.setText("  " + list.get(position).get("testdata"));
            chinesetext.setText("  " + list.get(position).get("fanyidata"));
        } else {
            englishtext.setText("  " + list.get(position).get("fanyidata"));
            chinesetext.setText("  " + list.get(position).get("testdata"));
        }
        playImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state.getText().equals("1")) {
                    state.setText("0");
                    voicePlayUtils.startPlay(englishtext.getText() + "");
                    playImg.setImageResource(R.mipmap.stop);
                    videoView.pause();
                } else {
                    state.setText("1");
                    voicePlayUtils.stopPlay();
                    playImg.setImageResource(R.mipmap.bofangone);
                }
            }
        });
        soundRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundRecordingUtils soundRecordingUtils = new SoundRecordingUtils(context, null, dubbingScore);
                soundRecordingUtils.soundRecording(englishtext.getText().toString().substring(4, 5) + "");
                videoView.pause();
                dubbingScore.setVisibility(View.VISIBLE);
            }
        });
        volumeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager am = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
                am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                am.adjustStreamVolume(AudioManager.STREAM_DTMF, AudioManager.ADJUST_RAISE, 0);
            }
        });
        return convertView;
    }

}
