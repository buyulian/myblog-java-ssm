package com.me.ssm.service;

import com.me.ssm.System.Authentication;
import com.me.ssm.dao.UserDao;
import com.me.ssm.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Resource
    private UserDao userDao;

    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public void add(User user) {
        user.setSalt(Authentication.getSalt());
        user.setPassword(Authentication.md5(user.getPassword()+user.getSalt()));
        userDao.add(user);
    }

}
