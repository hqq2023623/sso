package sso.servlet;

import sso.util.VerifyCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by lzj on 2016/11/24.
 */
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);
        req.getSession().setAttribute("verifyCode", code.toLowerCase());
        int w = 80, h = 40;

        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);
        Paint linearPaint = new LinearGradientPaint(0, 0, w, h, fractions, colors);
        Paint linearPaint2 = new LinearGradientPaint(0, 0, w, h, new float[]{0.3f, .6f, .8f, .9f}, new Color[]{Color.BLUE, Color.BLACK, Color.GREEN, Color.BLUE});
        // 设置图片背景为白色
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        // 设置图片渐变背景
        g2.setPaint(linearPaint);
        g2.fillRoundRect(0, 0, w, h, 5, 5);

        g2.setPaint(linearPaint2);
        int fontSize = (int) (Math.min(w / verifySize, h));
        Font font = new Font("微软雅黑", Font.BOLD, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize / 2, h / 2);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, (w / verifySize) * i, h / 2 + fontSize / 2);
        }
        g2.dispose();
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        try {
            ServletOutputStream sos = resp.getOutputStream();
            ImageIO.write(image, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
