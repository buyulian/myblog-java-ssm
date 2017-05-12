package com.me.ssm.controller;

import com.me.ssm.System.Authentication;
import com.me.ssm.model.User;
import com.me.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/signUp")
    public String signUp(){
        return "signUp";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, User user){
        if(Authentication.login(user.getId(),user.getPassword(),request,userService))
            return "redirect:showUser";
        model.addAttribute("message","密码错误");
        return "signIn";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request,User user){
        Authentication.loginOut(request);
        return Authentication.backPath;
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request,User user){
        if(Authentication.isRole("admin",request))
            return Authentication.backPath;
        userService.add(user);
        return "redirect:signIn";
    }
}
