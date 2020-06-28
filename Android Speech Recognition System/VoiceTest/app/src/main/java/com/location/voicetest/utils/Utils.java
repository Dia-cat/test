package com.location.voicetest.utils;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils {

    public static final String DATANODE = "datanode";

    //保存笔记到本地
//    public static void saveNodeData(Context context, String name, String coutent) {
//        List<Map<String, String>> list = new ArrayList<>();
//        if (isNullOrEmpty(name)) {
//            Toast.makeText(context, "请输入保存笔记的名字", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (isNullOrEmpty(coutent)) {
//            Toast.makeText(context, "请输入保存笔记的内容", Toast.LENGTH_LONG).show();
//            return;
//        }
//        list = SharedPreferencesUtils.get(context, DATANODE, "") + "";
//        Map<String, String> map = new HashMap<>();
//        map.put("name", name);
//        map.put("time", System.currentTimeMillis() + "");
//        map.put("content", coutent);
//        list.add(map);
//
//    }

    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static void saveInfo(Context context, List<Map<String, String>> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, String> itemMap = datas.get(i);
            Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();
            JSONObject object = new JSONObject();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mJsonArray.put(object);
        }
        SharedPreferencesUtils.put(context, DATANODE, mJsonArray.toString());
    }

    public static List<Map<String, String>> getInfo(Context context) {
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        String result = SharedPreferencesUtils.get(context, DATANODE, "") + "";
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                Map<String, String> itemMap = new HashMap<String, String>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
                datas.add(itemMap);
            }
        } catch (JSONException e) {

        }
        return datas;
    }

    public static List<Map<String, String>> saveDataToList(Context context, String type, String score) {
        Map<String, String> map = new HashMap<>();
        List list = getInfo(context);
        if (list == null || list.size() <= 0) {
            list = new ArrayList();
        }
        map.put("type", type);
        map.put("score", score);
        map.put("time", System.currentTimeMillis() + "");
        list.add(map);
        return list;
    }
}
