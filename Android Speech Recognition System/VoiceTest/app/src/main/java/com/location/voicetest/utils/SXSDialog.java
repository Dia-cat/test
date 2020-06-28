package com.location.voicetest.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.location.voicetest.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义dialog
 *
 * @Title: SXSDialog.java
 * @author: xusonghui
 * @data: 2016年6月5日 上午11:36:15
 * @ModifiedPerson:
 * @ModifiedPersonData：2016年6月5日上午11:36:15 @ModifyRemarks：
 * @version: V1.0
 * @Copyright 沙小僧
 */
public class SXSDialog extends Dialog implements View.OnClickListener {
    protected View view;
    protected Map<Integer, View.OnClickListener> map = new HashMap<Integer, View.OnClickListener>();
    protected int width;
    protected int height;

    public SXSDialog(Context context, int layout) {
        super(context, R.style.customDialog);
        view = LayoutInflater.from(context).inflate(layout, null);
    }


    public SXSDialog(Context context, View view) {
        super(context, R.style.customDialog);
        this.view = view;
    }

    public SXSDialog(Context context, int layout, int style) {
        super(context, style);
        view = LayoutInflater.from(context).inflate(layout, null);
    }

    @Override
    public View findViewById(int id) {
        return view.findViewById(id);
    }

    public void setWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setOnClick(int viewId, View.OnClickListener onClickListener) {
        map.put(viewId, onClickListener);
        View view = findViewById(viewId);
        view.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        if (width <= 0) {
        } else {
            view.getLayoutParams().width = width;
        }
        if (height <= 0) {
        } else {
            view.getLayoutParams().height = height;
        }
        view.setLayoutParams(view.getLayoutParams());
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (map.containsKey(v.getId()) && map.get(v.getId()) != null) {
            map.get(v.getId()).onClick(v);
        }
    }
}
