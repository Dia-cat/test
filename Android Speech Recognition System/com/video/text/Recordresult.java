package com.video.text;

import com.video.text.bean.RecordEntity;
import com.video.text.utils.JsonUtils;
import com.video.text.utils.RecordDao;
import com.video.text.utils.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recordresult extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  super.doPost(req, resp);
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String phoneNum = req.getParameter("phoneNum");
        List<RecordEntity> recordlist = new ArrayList();
        String result = "";
        JSONObject object = new JSONObject();
        Map<String, String> map = null;
        List<Map<String, String>> list = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(phoneNum)) {
            RecordDao dao = new RecordDao();
            recordlist = dao.queryByphone(phoneNum);
            for (int i = 0; i < recordlist.size(); i++) {
                RecordEntity entity = recordlist.get(i);
                map = new HashMap<>();
                map.put("recordType", entity.getRecordtype());
                map.put("recordTime", entity.getRecordtime());
                map.put("recordScore", entity.getRecordscore());
                list.add(map);
            }
            result = JSONArray.fromObject(list).toString();
        } else {
            result = JsonUtils.errorMesg("参数不正确");
        }
        PrintWriter writer = resp.getWriter();
        object.put("data", result);
        writer.println(object.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
