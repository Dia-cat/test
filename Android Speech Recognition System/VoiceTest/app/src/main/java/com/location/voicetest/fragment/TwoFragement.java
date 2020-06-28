package com.location.voicetest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.location.voicetest.R;
import com.location.voicetest.activity.LoginActivity;
import com.location.voicetest.base.BaseFragment;
import com.location.voicetest.view.TitleWidget;

public class TwoFragement extends BaseFragment {
    private TitleWidget title;
    private Button button1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_two, null, false);
        inintView(view);

        return view;
    }

    private void inintView(View view) {
        title = view.findViewById(R.id.title);
        title.setBackVisibility(View.GONE);
        button1 = view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mactivity, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
