package com.me.ssm.controller;

import com.me.ssm.System.Authentication;
import com.me.ssm.model.User;
import com.me.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model){
        if(!Authentication.isRole("admin",request)){
            return Authentication.backPath;
        }
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "showUser";
    }


    @RequestMapping("/editUser")
    public String signUp(HttpServletRequest request, Model model){
        if(!Authentication.isRole("admin",request)){
            return Authentication.backPath;
        }
        String id=request.getParameter("id");
        if(id!=null)
            model.addAttribute("id",id);
        else model.addAttribute("id",-1);
        return "editUser";
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request,User user){
        if(!Authentication.isRole("admin",request))
            return Authentication.backPath;
        userService.add(user);
        return "redirect:showUser";
    }

    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request,User user){
        if(!Authentication.isRole("admin",request))
            return Authentication.backPath;
        userService.update(user);
        return "redirect:showUser";
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request){
        if(!Authentication.isRole("admin",request))
            return Authentication.backPath;
        userService.delete(request.getParameter("id"));
        return "redirect:showUser";
    }
}
