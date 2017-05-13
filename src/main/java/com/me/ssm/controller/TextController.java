package com.me.ssm.controller;

import com.me.ssm.model.Text;
import com.me.ssm.service.TextService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class TextController {

    @Resource
    private TextService textService;

    @RequestMapping("/")
    public String title(HttpServletRequest request, Model model){
        List<Text> title=textService.getAllText();
        model.addAttribute("titleList",title);
        return "title";
    }

    @RequestMapping("/text")
    public String text(HttpServletRequest request, Model model){
        int id=Integer.parseInt(request.getParameter("id"));
        Text text=textService.getTextById(id);
        model.addAttribute("text",text);
        return "text";
    }
    @RequestMapping("/save")
    public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String content=request.getParameter("content");
        String title=request.getParameter("title");
        int id=Integer.parseInt(request.getParameter("id"));
        Text text=new Text();
        text.setTitle(title);
        text.setContent(content);
        if(id<0){
            textService.add(text);
        }else{
            text.setId(id);
            textService.update(text);
        }
        PrintWriter out = response.getWriter();
        out.println("success");
        return ;
    }
    @RequestMapping("/addBlog")
    public String addBlog(HttpServletRequest request, Model model){
        Text text=new Text();
        text.setId(-1);
        model.addAttribute("text",text);
        return "text";
    }
    @RequestMapping("/seeBlog")
    public String seeBlog(HttpServletRequest request, Model model){
        Text text= textService.getTextById(Integer.parseInt(request.getParameter("id")));
        model.addAttribute("text",text);
        return "seeBlog";
    }
}
