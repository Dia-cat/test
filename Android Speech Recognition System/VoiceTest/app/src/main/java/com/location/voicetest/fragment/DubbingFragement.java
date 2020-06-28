package com.location.voicetest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.location.voicetest.R;
import com.location.voicetest.activity.DubbingDeailActivity;
import com.location.voicetest.base.BaseFragment;


public class DubbingFragement extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_dubbing, null, false);
        inintView(view);
        return view;
    }

    //view初始化
    private void inintView(View view) {
        //中文配音练习
        LinearLayout chinesedub = (LinearLayout) view.findViewById(R.id.chinesedub);
        chinesedub.setOnClickListener(this);
        //英文配音练习
        LinearLayout englishdub = (LinearLayout) view.findViewById(R.id.englishdub);
        englishdub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), DubbingDeailActivity.class);
        switch (v.getId()) {
            case R.id.chinesedub://中文配音
                intent.putExtra("type", "1");
                break;
            case R.id.englishdub://英文配音
                intent.putExtra("type", "2");
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
