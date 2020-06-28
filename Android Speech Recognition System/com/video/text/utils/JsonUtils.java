package com.video.text.utils;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    public static String errorMesg(String errormsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Error", "104");
        map.put("Message", errormsg);
        map.put("key", "error");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    public static String correctMess(String Correctmessage) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("right", "105");
        map.put("Message", Correctmessage);
        map.put("key", "success");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }
}
