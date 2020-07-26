package com.kuang.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/*
 *@author:C1q
 */
public class imgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("refresh", "3");

        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, 150, 60);

        g.setColor(Color.BLUE);
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(makeNum(), 5, 20);

        resp.setContentType("image/jpg");
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");

        ImageIO.write(image, "jpg", resp.getOutputStream());


    }

    private String makeNum() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //获取长度为6的字符串
        for (int i = 0; i < 6; i++) {
            //获取范围在3之内的索引值
            int number = random.nextInt(3);
            int result = 0;
            switch (number) {
                case 0:
                    //Math.random()*25+65成成65-90的int型的整型,强转小数只取整数部分
                    result = (int) (Math.random() * 25 + 65);  //对应A-Z 参考ASCII编码表
                    //将整型强转为char类型
                    sb.append((char) result);
                    break;
                case 1:
                    result = (int) (Math.random() * 25 + 97);   //对应a-z
                    sb.append((char) result);
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }

        }
        for (int j = 0; j < 6-sb.length() ; j++) {
            sb.append("0");
        }

        return sb.toString();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
