package com.rt.web.servlet.base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("/base")
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //1、获取方法名称
            String mName = req.getParameter("method");
            if (mName == null || mName.trim().length() == 0) {
                mName="index";
            }
            //2、获取方法对象
            Method method = this.getClass().getMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
            //3、让方法执行，返回值
            String path = (String) method.invoke(this, req, resp);
            //4、判断返回值，是否为空，若不为空，统一处理请求转发
            if (path != null) {
                req.getRequestDispatcher(path).forward(req, resp);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("不要乱搞");
        return null;
    }
}
