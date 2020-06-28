package com.location.voicetest.adapter;

import android.content.Context;

import com.location.voicetest.R;
import com.location.voicetest.base.CommonAdapter;
import com.location.voicetest.base.base.ViewHolder;
import com.location.voicetest.dbbean.TestInfo;

import java.util.List;

public class HostSimpleAdapter extends CommonAdapter<TestInfo> {
    public HostSimpleAdapter(Context context, int layoutId, List<TestInfo> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TestInfo testInfo, int position) {
        holder.setText(R.id.actv_1,testInfo.getBeizhu1());
        holder.setText(R.id.actv_2,testInfo.getCount()+"");
    }
}
