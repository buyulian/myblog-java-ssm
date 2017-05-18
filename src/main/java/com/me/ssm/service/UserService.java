package com.me.ssm.service;

import com.me.ssm.model.User;

import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */
public interface UserService {

    List<User> getAllUser();

    User getUserById(String id);

    void add(User user);

    void delete(String id);

    void update(User user);
}
