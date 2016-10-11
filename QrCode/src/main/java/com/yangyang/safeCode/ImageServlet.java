package com.yangyang.safeCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "ImageServlet",value = "/safeImage")
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage bi = new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color color = new Color(200,150,255);
        g.setColor(color);
        g.fillRect(0,0,68,22);

        char[] nums = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random random = new Random();

        int len = nums.length,index;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            index = random.nextInt(len);
            g.setColor(new Color(random.nextInt(88), random.nextInt(150),random.nextInt(255)));
            g.drawString(nums[index]+"",(i*15)+3,18);
            sb.append(Character.toUpperCase(nums[index]));
        }
        request.getSession().setAttribute("safeCode",sb.toString());
        ImageIO.write(bi,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
