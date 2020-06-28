package com.video.text;

import com.video.text.bean.RegisterEntity;
import com.video.text.utils.JsonUtils;
import com.video.text.utils.RegiesterDao;
import com.video.text.utils.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Logintext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");
        String password = req.getParameter("passWord");
        String phoneNumber = req.getParameter("phoneNum");
        String result = "";
        RegiesterDao dao = new RegiesterDao();
        List<RegisterEntity> list = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(userName)) {
            list = dao.queryByuserName(userName);
        }
        if (!StringUtil.isNullOrEmpty(phoneNumber)) {
            list = dao.queryByphone(phoneNumber);
        } else {
            result = JsonUtils.errorMesg("请输入手机号或昵称");
        }
        if (StringUtil.isNullOrEmpty(password)) {
            result = JsonUtils.errorMesg("请输入登录密码");
        } else {
            for (int i = 0; i < list.size(); i++) {
                RegisterEntity entity = list.get(i);
                if (entity.getPhoneNum().equals(phoneNumber) && password.equals(entity.getPassword()) ||
                        entity.getUserName().equals(userName) && password.equals(entity.getPassword())) {
                    result = JsonUtils.correctMess("登录成功");
                    break;
                } else {
                    result = JsonUtils.errorMesg("账号密码不正确");
                }
            }
        }
        PrintWriter writer = resp.getWriter();
        JSONObject object = new JSONObject();
        object.put("data", result);
        writer.println(object.toString());
    }
}
