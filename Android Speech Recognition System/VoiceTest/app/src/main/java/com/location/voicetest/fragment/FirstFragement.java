package com.location.voicetest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.location.voicetest.R;
import com.location.voicetest.activity.TextRecordActivity;
import com.location.voicetest.activity.dChineseTestActivity;
import com.location.voicetest.activity.dTestSimpleActivity;
import com.location.voicetest.base.BaseFragment;
import com.location.voicetest.utils.Utils;
import com.location.voicetest.view.TitleWidget;

import java.util.List;
import java.util.Map;


public class FirstFragement extends BaseFragment implements View.OnClickListener {
    private TitleWidget title;
    private Button button1, button2;
    private Button acbtn_sl;
    private TextView tv_score;
    private RelativeLayout rlyt_ceshi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_first, null, false);
        inintView(view);
        return view;
    }

    private void inintData() {
        int score = 0;
        List<Map<String, String>> list = Utils.getInfo(getActivity());
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                score = score + Integer.parseInt(list.get(i).get("score"));
            }
            tv_score.setText(score / list.size() + "分");
        }
    }

    private void inintView(View view) {
        title = view.findViewById(R.id.title);
        rlyt_ceshi = view.findViewById(R.id.rlyt_ceshi);
        tv_score = view.findViewById(R.id.tv_score);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        acbtn_sl = view.findViewById(R.id.acbtn_sl);
        title.setBackVisibility(View.GONE);
        acbtn_sl.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        rlyt_ceshi.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        inintData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent cintent = new Intent(mactivity, dChineseTestActivity.class);
                // Intent cintent = new Intent(mactivity, ChineseTestActivity.class);
                cintent.putExtra("type", "1");
                startActivity(cintent);
                break;
            case R.id.button2:
                Intent eintent = new Intent(mactivity, dChineseTestActivity.class);
                eintent.putExtra("type", "2");
                //  Intent eintent = new Intent(mactivity, EnglishTestActivity.class);
                startActivity(eintent);
                break;
            case R.id.acbtn_sl:
                //   Intent intent = new Intent(mactivity, TestSimpleActivity.class);
                Intent intent = new Intent(mactivity, dTestSimpleActivity.class);
                startActivity(intent);
                break;
            case R.id.rlyt_ceshi:
                Intent intentcc = new Intent(mactivity, TextRecordActivity.class);
                // Intent intentcc = new Intent(mactivity, HostSimpleActiivty.class);
                startActivity(intentcc);
                break;
        }

    }
}
