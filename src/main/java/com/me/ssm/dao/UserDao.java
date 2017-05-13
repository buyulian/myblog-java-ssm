package com.me.ssm.dao;

import com.me.ssm.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Repository
public interface UserDao {

    User getUserById(@Param("id") String id);

    void add(User user);

    List<User> getAllUser();
}
