package com.me.ssm.dao;

import com.me.ssm.model.Text;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 不语恋 on 2017/5/12.
 */
@Repository
public interface TextDao {
    Text getTextById(@Param("id") int id);

    void add(Text text);

    void update(Text text);

    int getId();

    List<Text> getAllText();
}
