package com.me.ssm.service;

import com.me.ssm.model.Text;

import java.util.List;

/**
 * Created by 不语恋 on 2017/5/12.
 */
public interface TextService {

    Text getTextById(int id);

    void add(Text text);

    void update(Text text);

    int getId();

    List<Text> getAllText();
}
