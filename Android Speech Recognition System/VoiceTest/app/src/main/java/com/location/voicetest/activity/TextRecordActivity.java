package com.location.voicetest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.location.voicetest.R;
import com.location.voicetest.adapter.TextRecordAdapter;
import com.location.voicetest.utils.Constent;
import com.location.voicetest.utils.OkhttpUntils;
import com.location.voicetest.utils.Okhtttpcallback;
import com.location.voicetest.utils.SharedPreferencesUtils;
import com.location.voicetest.utils.Urlutils;
import com.location.voicetest.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextRecordActivity extends Activity implements Okhtttpcallback {
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textrecordactivity);
        listView = (ListView) findViewById(R.id.listv);
        OkhttpUntils.postHttpClent(this, Urlutils.recordurl, 0, new String[]{"phoneNum"},
                SharedPreferencesUtils.get(this, Constent.PHONE, "") + "");
//        List<Map<String, String>> list = Utils.getInfo(this);
//        if (list.size() <= 0) {
//            listView.setVisibility(View.GONE);
//        } else {
//            listView.setVisibility(View.VISIBLE);
//            TextRecordAdapter adapter = new TextRecordAdapter(list, this);
//            listView.setAdapter(adapter);
//        }
    }

    @Override
    public void fail(int id, String str) {

    }

    @Override
    public void success(int id, String result) {
        System.out.println("==============111111111111======" + result);
        try {
            JSONObject object = new JSONObject(result);
            JSONArray array = object.getJSONArray("data");
            if (array.length() < 0) {
                listView.setVisibility(View.GONE);
                findViewById(R.id.norecord).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.norecord).setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                List<Map<String, String>> list = new ArrayList<>();
                JSONObject object1;
                for (int i = 0; i < array.length(); i++) {
                    object1 = array.getJSONObject(i);
                    Map<String, String> map = new HashMap<>();
                    map.put("time", object1.optString("recordTime"));
                    map.put("score", object1.optString("recordScore"));
                    map.put("type", object1.optString("recordType"));
                    list.add(map);
                }
                listView.setVisibility(View.VISIBLE);
                TextRecordAdapter adapter = new TextRecordAdapter(list, this);
                listView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
