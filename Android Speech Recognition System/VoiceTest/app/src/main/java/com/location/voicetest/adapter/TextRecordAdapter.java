package com.location.voicetest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.location.voicetest.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TextRecordAdapter extends BaseAdapter {

    List<Map<String, String>> list;

    Context context;

    LayoutInflater mInflator;

    public TextRecordAdapter(List<Map<String, String>> list, Context context) {
        this.context = context;
        this.list = list;
        mInflator = LayoutInflater.from(context);
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
        convertView = mInflator.inflate(R.layout.textrecorditme, null);
        TextView textscore = (TextView) convertView.findViewById(R.id.textscore);//分数
        TextView texttime = (TextView) convertView.findViewById(R.id.texttime);//时间
        TextView texttype = (TextView) convertView.findViewById(R.id.texttype);//类型
        Long time = Long.parseLong(list.get(position).get("time"));
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm");//24小时制
        int score = Integer.parseInt(list.get(position).get("score"));
        if (score < 60) {
            textscore.setTextColor(Color.RED);
        } else if (score < 85 && score > 60) {
            textscore.setTextColor(Color.BLUE);
        } else {
            textscore.setTextColor(Color.GREEN);
        }
        textscore.setText(list.get(position).get("score"));
        texttime.setText(simpleDateFormat.format(date) + "");
        texttype.setText(list.get(position).get("type"));
        return convertView;
    }
}
