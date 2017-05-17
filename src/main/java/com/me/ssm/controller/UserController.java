package com.me.ssm.controller;

import com.me.ssm.System.Authentication;
import com.me.ssm.model.User;
import com.me.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model){
        if(!Authentication.isLogin(request))
            return Authentication.backPath;
        log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "showUser";
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request,User user){
        if(!Authentication.isRole("admin",request))
            return Authentication.backPath;
        userService.add(user);
        return "redirect:signIn";
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request){
        if(!Authentication.isRole("admin",request))
            return Authentication.backPath;
        userService.delete(request.getParameter("id"));
        return "redirect:showUser";
    }
}
