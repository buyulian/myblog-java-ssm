package com.me.ssm.user;

import com.me.ssm.model.User;
import com.me.ssm.service.AppleService;
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
public class AppleServiceTest {

    @Resource
    private AppleService appleService;

    @Test
    public void testGetUserById(){
        String name = appleService.getName();
        System.out.println(name);
    }
}
