package com.me.ssm.controller;

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
import java.util.List;
import java.util.UUID;

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
    public String text(HttpServletRequest request, Model model) throws IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Text text=textService.getTextById(id);
//        String str=Authentication.base64Decode(text.getContent());
//        str = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
//        text.setContent(URLDecoder.decode(str,"utf-8"));
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
        out.close();
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
    public String seeBlog(HttpServletRequest request, Model model) throws IOException {
        Text text= textService.getTextById(Integer.parseInt(request.getParameter("id")));
        model.addAttribute("text",text);
        return "seeBlog";
    }
    @RequestMapping("/deleteBlog")
    public String deleteBlog(HttpServletRequest request, Model model){
        textService.delete(Integer.parseInt(request.getParameter("id")));
        return "redirect:/";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String path = request.getRealPath("/upload");
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        String fileName = "";// 文件名称

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles("wangEditorH5File");
            for (MultipartFile fi : fileList) {
                if(!fi.isEmpty()){
                    String uuid=UUID.randomUUID().toString();
                    String fin=fi.getOriginalFilename();
                    int lio=fin.lastIndexOf(".");
                    int len=fin.length();
                    String substr=fin.substring(lio,len);
                    fileName = uuid+substr;
                   fi.transferTo(new File(path, fileName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**********************/

        //获取图片url地址
        String imgUrl = "http://localhost:8080/upload/" + fileName;
        response.setContentType("text/text;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(imgUrl);  //返回url地址
        out.flush();
        out.close();
    }
}
