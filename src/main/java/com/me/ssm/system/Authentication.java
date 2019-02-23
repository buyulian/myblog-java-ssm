package com.me.ssm.system;

import com.me.ssm.model.User;
import com.me.ssm.service.UserService;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 这个类集中了授权管理相关的所有工具类，从微观角度进行权限管理
 * @author 不语恋
 */
@Component
public class Authentication {
    /** 越权访问的返回路径 */
    public static String backPath = "redirect:/";
    /** 恶意攻击的返回路径 */
    public static String warnPath = "redirect:/warn.html";

    /** 登录工具类
     *
     * @param id
     *          用户的id
     *
     * @param pwd
     *          用户的密码
     *
     * @param request
     *          Http 请求
     *
     * @param userService
     *          用户 service 层接口
     *
     * @return 是否登录成功
     * */
    public static boolean login(String id, String pwd, HttpServletRequest request, UserService userService) {
        User user = userService.getUserById(id);
        if (user == null)
            return false;
        //加密后的字符串
        String pwd2 = md5(pwd + user.getSalt());
        if (pwd2.equals(user.getPassword())) {
            // 如果不存在 session 会话，则创建一个 session 对象
            HttpSession session = request.getSession();
            if (!session.isNew()) {
                session.invalidate();
                session = request.getSession();
            }
            session.setAttribute("id", user.getId());
            session.setAttribute("role", user.getRole());
            session.setAttribute("userName", user.getName());
            return true;
        }
        return false;
    }

    /** 是否登录
     * @param request
     *          请求
     * @return 是否登录成功
     */
    public static boolean isLogin(HttpServletRequest request) {
        // 如果不存在 session 会话，则创建一个 session 对象
        HttpSession session = request.getSession();
        if (session.isNew()) {
            return false;
        }
        if (session.getAttribute("id") == null)
            return false;
        return true;
    }

    /** 是否是这个角色 */
    public static boolean isRole(String role, HttpServletRequest request) {
        if (isLogin(request)) {
            if (request.getSession().getAttribute("role").equals(role))
                return true;
        }
        return false;
    }

    /** 退出 */
    public static void loginOut(HttpServletRequest request) {
        if (isLogin(request)) {
            request.getSession().invalidate();
        }
    }

    /** md5加密 */
    public static String md5(String str) {

        MessageDigest md5 = null;
        String newstr = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            newstr = bytesToHexString(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newstr;
    }

    /** bytes数组转16进制字符串 */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /** 随机生成盐 */
    public static String getSalt() {
        char[] chars = "0123456789abcdefghijklmnopqrwtuvzxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] saltchars = new char[6];
        Random RANDOM = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            int n = RANDOM.nextInt(62);
            saltchars[i] = chars[n];
        }
        String salt = new String(saltchars);
        return salt;
    }

    /** sql安全的base64加密 */
    public static String safeUrlBase64Encode(byte[] data) {
        String encodeBase64 = new BASE64Encoder().encode(data);
        String safeBase64Str = encodeBase64.replace('+', '-');
        safeBase64Str = safeBase64Str.replace('/', '_');
        safeBase64Str = safeBase64Str.replaceAll("=", "");
        return safeBase64Str;
    }

    /** sql安全的base64解密 */
    public static byte[] safeUrlBase64Decode(String safeBase64Str) throws IOException {
        String base64Str = safeBase64Str.replace('-', '+');
        base64Str = base64Str.replace('_', '/');
        int mod4 = base64Str.length() % 4;
        if (mod4 > 0) {
            base64Str = base64Str + "====".substring(mod4);
        }
        return new BASE64Decoder().decodeBuffer(base64Str);
    }

    /** base64加密 */
    public static String base64Encode(String str) {
        byte[] data = str.getBytes();
        return safeUrlBase64Encode(data);
    }

    /** base64解密 */
    public static String base64Decode(String str) throws IOException {
        byte[] data = safeUrlBase64Decode(str);
        return new String(data);
    }
}
