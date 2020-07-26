package com.kuang.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/*
 *@author:C1q
 */
public class fileDownload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        ServletConfig servletConfig = this.getServletConfig();

        InputStream in = servletConfig.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        Properties properties = new Properties();
        properties.load(br);
        String username = properties.getProperty("username");

        String servletName = servletConfig.getServletName();

        String url = servletConfig.getInitParameter("password");

        resp.getWriter().print(username);
        resp.getWriter().print(servletName);
        resp.getWriter().print(url);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
