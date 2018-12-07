package com.use.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/captcha.jpg")
public class RandomCode extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 105;
    private static final int HEIGHT = 50;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setContentType("image/jpeg");
        ServletOutputStream sos = resp.getOutputStream();
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        char[] rands = generateCheckCode();
        drawBackground(g);
        drawRands(g, rands);
        g.dispose();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", bos);
        byte[] buf = bos.toByteArray();
        resp.setContentLength(buf.length);
        sos.write(buf);
        bos.close();
        sos.close();
        session.setAttribute("rand", new String(rands));

    }
    private void drawBackground(Graphics g) {
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
    }

    private void drawRands(Graphics g, char[] rands) {
        // g.setColor(Color.BLUE);
        Random random = new Random();
        int red = random.nextInt(110);
        int green = random.nextInt(50);
        int blue = random.nextInt(50);
        g.setColor(new Color(red, green, blue));
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 30));
        g.drawString("" + rands[0], 5, 35);
        g.drawString("" + rands[1], 25, 34);
        g.drawString("" + rands[2], 45, 36);
        g.drawString("" + rands[3], 65, 33);
    }

    private char[] generateCheckCode() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 36);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
