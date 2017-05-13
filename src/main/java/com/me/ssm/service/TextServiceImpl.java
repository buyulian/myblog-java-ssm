package com.me.ssm.service;

import com.me.ssm.dao.TextDao;
import com.me.ssm.model.Text;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 不语恋 on 2017/5/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TextServiceImpl implements TextService {
    @Resource
    private TextDao textDao;

    public Text getTextById(int id){
        return textDao.getTextById(id);
    }

    public void add(Text text){
        textDao.add(text);
    }

    public void update(Text text) {
        textDao.update(text);
    }

    public int getId(){
        return textDao.getId();
    }

    public List<Text> getAllText(){
        return textDao.getAllText();
    }
}
