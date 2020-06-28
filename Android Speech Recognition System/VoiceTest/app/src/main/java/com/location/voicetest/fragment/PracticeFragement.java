package com.location.voicetest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.location.voicetest.R;
import com.location.voicetest.activity.PractuceActivity;
import com.location.voicetest.base.BaseFragment;

/**
 * 练习页面
 */

public class PracticeFragement extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prafragement, null, false);
        inintView(view);
        return view;
    }

    private void inintView(View view) {
        //英语练习
        TextView englishprac = (TextView) view.findViewById(R.id.englishprac);
        englishprac.setOnClickListener(this);
        //中文练习
        TextView chineseprac = (TextView) view.findViewById(R.id.chineseprac);
        chineseprac.setOnClickListener(this);
        //随机练习
        TextView randomprac = (TextView) view.findViewById(R.id.randomprac);
        randomprac.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), PractuceActivity.class);
        switch (v.getId()) {
            case R.id.englishprac:
                intent.putExtra("type", "1");
                break;
            case R.id.chineseprac:
                intent.putExtra("type", "2");
                break;
            case R.id.randomprac:
                intent.putExtra("type", "3");
                break;
            default:
                break;
        }
        getActivity().startActivity(intent);
    }
}
