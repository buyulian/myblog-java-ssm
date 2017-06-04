package com.me.ssm.controller;

import com.me.ssm.System.Authentication;
import com.me.ssm.System.VerificationCode;
import com.me.ssm.model.User;
import com.me.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 不语恋 on 2017/5/10.
 */
@Controller
public class SignController {
    @Resource
    private UserService userService;

    @RequestMapping("/signIn")
    public String signIn(){
        return "signIn";
    }


    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, User user){
        String code=request.getParameter("code");
        if(!VerificationCode.validateCode(request,code)){
            model.addAttribute("message","密码或验证码错误");
            return "signIn";
        }
        if(Authentication.login(user.getId(),user.getPassword(),request,userService))
            return Authentication.backPath;
        model.addAttribute("message","密码或验证码错误");
        return "signIn";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request,User user){
        if(!Authentication.isLogin(request)){
            return Authentication.backPath;
        }
        Authentication.loginOut(request);
        return Authentication.backPath;
    }
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        VerificationCode.getCode(request,response);
    }

}
