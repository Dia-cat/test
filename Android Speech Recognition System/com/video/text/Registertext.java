package com.video.text;

import com.video.text.utils.JsonUtils;
import com.video.text.utils.RegiesterDao;
import com.video.text.utils.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Registertext extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // super.doPost(req, resp);
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("utf-8");
        //用户的昵称
        String userName = req.getParameter("userName");
        //用户的手机号
        String phoneNumber = req.getParameter("phoneNum");
        String password = req.getParameter("password");
        String result = "";
        if (StringUtil.isNullOrEmpty(userName) || StringUtil.isNullOrEmpty(phoneNumber) ||
                StringUtil.isNullOrEmpty(password)) {
            result = JsonUtils.errorMesg("请输入完整的信息");
        } else {
            result = JsonUtils.correctMess("注册成功");
            RegiesterDao dao = new RegiesterDao();
            dao.insert(userName, password, phoneNumber);
        }
        JSONObject object = new JSONObject();
        object.put("data", result);
        PrintWriter out = resp.getWriter();
        out.println(object.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //  super.doGet(req, resp);
        doPost(req, resp);
    }
}
