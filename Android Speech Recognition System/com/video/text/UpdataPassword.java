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

public class UpdataPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String type = req.getParameter("type");
        String phoneNum = req.getParameter("phoneNum");
        String newPass = req.getParameter("newPass");
        String userName = req.getParameter("userName");
        String result = "";
        RegiesterDao dao = new RegiesterDao();
        List<RegisterEntity> list = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(phoneNum) || StringUtil.isNullOrEmpty(type)) {
            result = JsonUtils.errorMesg("传入参数不正确");
        } else {
            RegisterEntity entity = null;
            list = dao.queryByphone(phoneNum);
            for (int i = 0; i < list.size(); i++) {
                entity = list.get(i);
            }
            if (entity != null && list.size() > 0) {
                if (type.equals("2")) {//修改登录密码
                    dao.update(entity.getId(), null, newPass);
                } else {//修改昵称
                    dao.update(entity.getId(), userName, null);
                }
                result = JsonUtils.correctMess("修改成功");
            }
        }
        PrintWriter writer = resp.getWriter();
        JSONObject object = new JSONObject();
        object.put("data", result);
        writer.println(object.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  super.doGet(req, resp);
        doPost(req, resp);
    }
}
