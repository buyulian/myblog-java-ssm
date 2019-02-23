package com.me.ssm.user;

import com.me.ssm.model.User;
import com.me.ssm.service.UserService;
import com.me.ssm.system.data.DynamicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author liujiacun
 * @date 2019/2/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-main.xml"})
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Before
    public void setUp(){
        DynamicDataSource.setDataSourceKey("dataSourceSqlite");
        DynamicDataSource.setDataSourceUrl("jdbc:sqlite:src/test/com/me/ssm/user/myblog.db");
    }

    @Test
    public void testGetUserById(){
        User buyulian = userService.getUserById("buyulian");
        System.out.println(buyulian.getName());
        User buyulian2 = userService.getUserById("buyulian");
        System.out.println(buyulian2.getName());
    }
}
