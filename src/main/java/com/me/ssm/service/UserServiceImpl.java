package com.me.ssm.service;

import com.me.ssm.system.Authentication;
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

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public void add(User user) {
        user.setSalt(Authentication.getSalt());
        user.setPassword(Authentication.md5(user.getPassword() + user.getSalt()));
        userDao.add(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        user.setSalt(Authentication.getSalt());
        user.setPassword(Authentication.md5(user.getPassword() + user.getSalt()));
        userDao.update(user);
    }

}
