package com.me.ssm.System;

import com.me.ssm.model.User;
import com.me.ssm.service.UserService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by 不语恋 on 2017/5/8.
 */
//这个类集中了授权管理相关的所有工具类，从微观角度进行权限管理
@Component
public class Authentication {
    public static String backPath="redirect:/";//越权访问的返回路径
    //登录工具类
    public static boolean login(String id,String pwd,HttpServletRequest request,UserService userService){
        User user=userService.getUserById(id);
        if(user==null)
            return false;
        //加密后的字符串
        String pwd2=md5(pwd+user.getSalt());
        if(pwd2.equals(user.getPassword())){
            // 如果不存在 session 会话，则创建一个 session 对象
            HttpSession session = request.getSession();
            if(!session.isNew()){
                session.invalidate();
                session=request.getSession();
            }
            session.setAttribute("id",user.getId());
            session.setAttribute("role",user.getRole());
            session.setAttribute("userName",user.getName());
            return true;
        }
        return false;
    }
    //是否登录
    public static boolean isLogin(HttpServletRequest request){
        // 如果不存在 session 会话，则创建一个 session 对象
        HttpSession session = request.getSession();
        if(session.isNew()){
           return false;
        }
        if(session.getAttribute("id")==null)
            return false;
        return true;
    }
    //是否是这个角色
    public static boolean isRole(String role,HttpServletRequest request){
        if(isLogin(request)){
            if(request.getSession().getAttribute("role").equals(role))
                return true;
        }
        return false;
    }
    //退出
    public static void loginOut(HttpServletRequest request){
        if(isLogin(request)){
            request.getSession().invalidate();
        }
    }
    //md5加密
    public static String md5(String str){

        MessageDigest md5= null;
        String newstr=null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            newstr=bytesToHexString(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newstr;
    }
    //bytes数组转16进制字符串
    public static String bytesToHexString(byte[] src){
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
    //随机生成盐
    public static String getSalt(){
        char[] chars="0123456789abcdefghijklmnopqrwtuvzxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] saltchars=new char[6];
        Random RANDOM = new SecureRandom();
        for(int i=0;i<6;i++){
            int n=RANDOM.nextInt(62);
            saltchars[i]=chars[n];
        }
        String salt=new String(saltchars);
        return salt;
    }
}
