package com.location.voicetest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.iflytek.cloud.EvaluatorListener;
import com.iflytek.cloud.EvaluatorResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvaluator;
import com.location.voicetest.result.Result;
import com.location.voicetest.result.xml.XmlResultParser;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  录音
 */
public class SoundRecordingUtils {

    SpeechEvaluator mIse;

    Context context;

    private String category;// 评测题型

    private final static String PREFER_NAME = "ise_settings";

    private String result_level;// 结果等级

    private String language;   // 评测语种

    private TextView charTv;

    private String mLastResult;//录音结果

    private float score;//分数

    private TextView scoreTv;//分数

    GradientDrawable background;

    public SoundRecordingUtils(Context context, TextView charTv, TextView scoreTv) {
        this.context = context;
        this.charTv = charTv;
        this.scoreTv = scoreTv;
        mIse = SpeechEvaluator.createEvaluator(context, null);
        background = (GradientDrawable) scoreTv.getBackground();
    }

    //录音
    public void soundRecording(String textStr) {
        if (mIse == null) {
            return;
        }
        setParams(textStr);
        int ret = mIse.startEvaluating(textStr, null, mEvaluatorListener);
    }

    private void setParams(String type) {
        checkTextStr(type);
        SharedPreferences pref = context.getSharedPreferences(PREFER_NAME, context.MODE_PRIVATE);
        // 设置评测语言
//        language = pref.getString(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置需要评测的类型
        category = pref.getString(SpeechConstant.ISE_CATEGORY, "read_sentence");
        // 设置结果等级（中文仅支持complete）
        result_level = pref.getString(SpeechConstant.RESULT_LEVEL, "complete");
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        String vad_bos = pref.getString(SpeechConstant.VAD_BOS, "5000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        String vad_eos = pref.getString(SpeechConstant.VAD_EOS, "1800");
        // 语音输入超时时间，即用户最多可以连续说多长时间；
        String speech_timeout = pref.getString(SpeechConstant.KEY_SPEECH_TIMEOUT, "-1");

        mIse.setParameter(SpeechConstant.LANGUAGE, language);
        mIse.setParameter(SpeechConstant.ISE_CATEGORY, category);
        mIse.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
        mIse.setParameter(SpeechConstant.VAD_BOS, vad_bos);
        mIse.setParameter(SpeechConstant.VAD_EOS, vad_eos);
        mIse.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, speech_timeout);
        mIse.setParameter(SpeechConstant.RESULT_LEVEL, result_level);

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIse.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIse.setParameter(SpeechConstant.ISE_AUDIO_PATH, Environment.getExternalStorageDirectory().getAbsolutePath() + "/msc/ise.wav");
        //通过writeaudio方式直接写入音频时才需要此设置
        //mIse.setParameter(SpeechConstant.AUDIO_SOURCE,"-1");
    }

    //检查语言的类型
    public void checkTextStr(String type) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(type.toCharArray()[0] + "");
        // 设置评测语言
        if (m.matches()) {
            language = "zh_cn";
            String[] cStrHY = PinyinHelper.toHanyuPinyinStringArray(type.toCharArray()[0]);
            if (charTv != null) {
                charTv.setText("[" + cStrHY[0] + "]");
                charTv.setVisibility(View.VISIBLE);
            }
        } else {
            language = "en_us";
            if (charTv != null)
                charTv.setVisibility(View.GONE);
        }
    }

    // 评测监听接口
    private EvaluatorListener mEvaluatorListener = new EvaluatorListener() {

        @Override
        public void onResult(EvaluatorResult result, boolean isLast) {
            if (isLast) {
                StringBuilder builder = new StringBuilder();
                builder.append(result.getResultString());
                mLastResult = builder.toString();
                // 解析最终结果
                if (!TextUtils.isEmpty(mLastResult)) {
                    XmlResultParser resultParser = new XmlResultParser();
                    Result result11 = resultParser.parse(mLastResult);
                    if (null != result) {
                        score = result11.total_score * 20;
                        scoreTv.setVisibility(View.VISIBLE);
                        if (score > 80) {
                            background.setColor(Color.parseColor("#00E403"));
                        } else if (score < 85 && score > 60) {
                            background.setColor(Color.parseColor("#00C8E3"));
                        } else {
                            background.setColor(Color.parseColor("#ff0000"));
                        }
                        scoreTv.setText((int) score + "");
                    } else {
                        showTip("解析结果为空");
                    }
                }
                showTip("评测结束");
            }
        }

        @Override
        public void onError(SpeechError error) {
            if (error != null) {
                showTip("error:" + error.getErrorCode() + "," + error.getErrorDescription());
            }
        }

        @Override
        public void onBeginOfSpeech() {
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            showTip("当前音量：" + volume);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

        }

    };

    private void showTip(String str) {
        ToastUtils.showToast(context, str);
    }
}
