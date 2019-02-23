package com.me.ssm.service;

import com.me.ssm.system.Authentication;
import com.me.ssm.dao.TextDao;
import com.me.ssm.model.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 不语恋 on 2017/5/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TextServiceImpl implements TextService {

    private Logger logger= LoggerFactory.getLogger(TextServiceImpl.class);


    @Resource
    private TextDao textDao;

    @Override
    public Text getTextById(int id) {
        if (id < 0) {
            return null;
        }
        return textDao.getTextById(id);
    }

    @Override
    public void add(Text text) {
        if (text.getTitle().length() > 240) {
            return;
        }
        text.setDate(new Date());
        textDao.add(text);
    }

    @Override
    public void update(Text text) {
        if (text.getTitle().length() > 240) {
            return;
        }
        textDao.update(text);
    }

    @Override
    public void delete(int id) {
        textDao.delete(id);
    }

    @Override
    public int getId() {
        return textDao.getId();
    }

    @Override
    public List<Text> getAllText() {
        return textDao.getAllText();
    }

    @Override
    public List<Text> getAllTextContent() {
        return textDao.getAllTextContent();
    }

    @Override
    public void deleteUnusedImage(String path) {
        path += "\\";
        List<Text> textList = textDao.getAllTextContent();
        String flag = "visited";
        for (Text text : textList) {
            // 正则表达式规则
            String regEx = "(?<=http://localhost:8080/upload/).*?.jpg";
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regEx);
            // 忽略大小写的写法
            // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
            String str = "";
            try {
                str = Authentication.base64Decode(text.getContent());
                str = URLDecoder.decode(str, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Matcher matcher = pattern.matcher(str);
            // 查找字符串中是否有匹配正则表达式的字符/字符串
            while (matcher.find()) {
                String fileName = matcher.group();
                File file = new File(path + fileName);
                if (!file.renameTo(new File(path + flag + fileName))) {
                    logger.error("重命名失败");
                }
            }
        }
        File file = new File(path);
        File[] fileList = file.listFiles();
        if (fileList == null) {
            return;
        }
        for (File f : fileList) {
            if (!f.getName().contains(flag)) {
                if (!f.delete()) {
                    logger.error("删除文件失败");
                }
            }
        }
        file = new File(path);
        fileList = file.listFiles();
        if (fileList == null) {
            return;
        }
        for (File f : fileList) {
            String fname = f.getName();
            if (fname.contains(flag)) {
                String substr = fname.substring(flag.length());
                if (!f.renameTo(new File(path + substr))) {
                    logger.error("重命名文件失败");
                }
            }
        }
    }

    private void decodeTitle(Text text) {
        try {
            String str = Authentication.base64Decode(text.getTitle());
            str = URLDecoder.decode(str, "utf-8");
            text.setTitle(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
