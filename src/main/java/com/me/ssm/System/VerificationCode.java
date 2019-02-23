package com.me.ssm.System;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成类
 * @author 不语恋
 */
public class VerificationCode {

    /** 验证码宽度 */
    private static int width = 140;

    /** 验证码高度 */
    private static int height = 60;

    /** 验证码个数 */
    private static int codeCount = 4;

    /** 混淆线个数 */
    private static int lineCount = 4;

    /** 验证码包含的字符 */
    static String codeSequence = "0123456789qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";

    /** 得到验证码 */
    public static void getCode(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        //定义随机数类
        Random r = new Random();
        //定义存储验证码的类
        StringBuilder builderCode = new StringBuilder();
        //定义画布
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //得到画笔
        Graphics g = buffImg.getGraphics();
        //1.设置颜色,画边框
        g.setColor(Color.black);
        g.drawRect(0, 0, width, height);
        //2.设置颜色,填充内部
        g.setColor(Color.white);
        g.fillRect(1, 1, width - 2, height - 2);
        //3.设置干扰线
        g.setColor(Color.blue);
        //g是Graphics对象
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3.0f));
        g2.setColor(Color.blue);
        for (int i = 0; i < lineCount; i++) {
            //g.drawLine(r.nextInt(width),r.nextInt(height),r.nextInt(width),r.nextInt(height));
            g2.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }
        //4.设置验证码
        g.setColor(Color.blue);
        //4.1设置验证码字体
        g.setFont(new Font("lucida handwriting", Font.ITALIC, 36));
        for (int i = 0; i < codeCount; i++) {
            char c = codeSequence.charAt(r.nextInt(codeSequence.length()));
            //String c=createChineseChar();
            builderCode.append(c);
        }
        g.drawString(builderCode + "", 10, 40);
        //5.输出到屏幕
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "png", sos);
        //6.保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("codeValidate", builderCode.toString().toLowerCase());
        //7.禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //8.关闭sos
        sos.close();
    }

    /** 让验证码失效 */
    public static boolean validateCode(HttpServletRequest request, String code) {
        HttpSession session = request.getSession();
        String codeValidate = (String) session.getAttribute("codeValidate");
        boolean flag = false;
        if (codeValidate != null && codeValidate.equals(code.toLowerCase())) {
            flag = true;
        }
        session.removeAttribute("codeValidate");
        return flag;
    }

    /** 生成中文验证码 */
    public static String createChineseChar() throws Exception {
        String str = null;
        // 定义高低位
        int hightPos, lowPos;
        Random random = new Random();
        //获取高位值
        hightPos = (176 + Math.abs(random.nextInt(39)));
        //获取低位值
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (new Integer(hightPos).byteValue());
        b[1] = (new Integer(lowPos).byteValue());
        //转成中文
        str = new String(b, "GBk");
        return str;
    }
}
