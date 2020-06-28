package com.video.text;

import com.video.text.utils.JsonUtils;
import com.video.text.utils.RecordDao;
import com.video.text.utils.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Recordtext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //测试的类型
        String recordType = req.getParameter("recordType");
        // 测试的时间
        String recordTime = req.getParameter("recordTime");
        // 测试的分数
        String recordScore = req.getParameter("recordScore");
        // 测试的手机号
        String phoneNum = req.getParameter("phoneNum");
        String result = "";
        if (!StringUtil.isNullOrEmpty(recordType) && !StringUtil.isNullOrEmpty(recordTime)
                && !StringUtil.isNullOrEmpty(recordScore) && !StringUtil.isNullOrEmpty(phoneNum)) {
            RecordDao dao = new RecordDao();
            dao.insert(recordType, recordTime, recordScore, phoneNum);
            result = JsonUtils.correctMess("数据插入成功");
        } else {
            result = JsonUtils.errorMesg("参数传入不能为空");
        }
        PrintWriter writer = resp.getWriter();
        JSONObject object = new JSONObject();
        object.put("data", result);
        writer.println(object.toString());
    }
}
