package com.me.ssm.controller;

import com.me.ssm.system.Authentication;
import com.me.ssm.model.Text;
import com.me.ssm.service.TextService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static java.awt.SystemColor.text;

@Controller
public class TextController {

    @Resource
    private TextService textService;

    @RequestMapping("/")
    public String title(HttpServletRequest request, Model model) {
        if (Authentication.isLogin(request)) {
            model.addAttribute("role", request.getSession().getAttribute("role"));
        }
        List<Text> title = textService.getAllText();
        model.addAttribute("titleList", title);
        return "title";
    }

    @RequestMapping("/text")
    public String text(HttpServletRequest request, Model model) throws IOException {

        if (!Authentication.isLogin(request)) {
            return Authentication.backPath;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        if (text == null) {
            return Authentication.warnPath;
        }
        Text text = textService.getTextById(id);
        model.addAttribute("text", text);
        return "text";
    }

    @RequestMapping("/save")
    public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if (!Authentication.isLogin(request)) {
            return;
        }
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        int id = Integer.parseInt(request.getParameter("id"));
        Text text = new Text();
        text.setTitle(title);
        text.setContent(content);
        if (id < 0) {
            textService.add(text);
        } else {
            text.setId(id);
            textService.update(text);
        }
        PrintWriter out = response.getWriter();
        out.println("success");
        out.close();
        return;
    }

    @RequestMapping("/addBlog")
    public String addBlog(HttpServletRequest request, Model model) {
        if (!Authentication.isLogin(request)) {
            return Authentication.backPath;
        }
        Text text = new Text();
        text.setId(-1);
        model.addAttribute("text", text);
        return "text";
    }

    @RequestMapping("/seeBlog")
    public String seeBlog(HttpServletRequest request, Model model) throws IOException {
        if (Authentication.isLogin(request)) {
            model.addAttribute("role", request.getSession().getAttribute("role"));
        }
        int id = Integer.parseInt(request.getParameter("id"));
        List<Text> textList = textService.getAllText();
        Iterator<Text> iterator = textList.iterator();
        int priId = -1;
        int nextId = -1;
        while (iterator.hasNext()) {
            Text tmp = iterator.next();
            if (tmp.getId() == id) {
                if (iterator.hasNext()) {
                    nextId = iterator.next().getId();
                    break;
                }
            } else {
                priId = tmp.getId();
            }
        }
        Text text = textService.getTextById(id);
        if (text == null) return Authentication.warnPath;
        Text priText = textService.getTextById(priId);
        Text nextText = textService.getTextById(nextId);
        model.addAttribute("text", text);
        model.addAttribute("priText", priText);
        model.addAttribute("nextText", nextText);
        return "seeBlog";
    }

    @RequestMapping("/deleteBlog")
    public String deleteBlog(HttpServletRequest request, Model model) {
        if (!Authentication.isLogin(request)) {
            return Authentication.backPath;
        }
        textService.delete(Integer.parseInt(request.getParameter("id")));
        return "redirect:/";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRealPath("/upload");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 文件名称
        String fileName = "";

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles("wangEditorH5File");
            for (MultipartFile fi : fileList) {
                if (!fi.isEmpty()) {
                    String uuid = UUID.randomUUID().toString();
                    String fin = fi.getOriginalFilename();
                    int lio = fin.lastIndexOf(".");
                    int len = fin.length();
                    String substr = fin.substring(lio, len);
                    substr = substr.toLowerCase();
                    fileName = uuid + substr;
                    fi.transferTo(new File(path, fileName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取图片url地址
        String imgUrl = "http://localhost:8080/upload/" + fileName;
        response.setContentType("text/text;charset=utf-8");
        PrintWriter out = response.getWriter();
        //返回url地址
        out.print(imgUrl);
        out.flush();
        out.close();
    }

    @RequestMapping("/deleteUnusedImage")
    public String deleteUnusedImage(HttpServletRequest request) {
        if (!Authentication.isLogin(request)) {
            return Authentication.backPath;
        }
        String path = request.getRealPath("/upload");
        textService.deleteUnusedImage(path);
        return Authentication.backPath;
    }
}
