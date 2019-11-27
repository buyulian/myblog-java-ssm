package com.me.ssm.user;

import com.me.ssm.dao.TextDao;
import com.me.ssm.model.Text;
import com.me.ssm.model.User;
import com.me.ssm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author liujiacun
 * @date 2019/2/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-main.xml"})
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Resource
    private DataSource dataSource;

    @Resource
    private TextDao textDao;

    @Test
    public void test(){
        Text text=new Text();
        text.setIds(Arrays.asList(1, 2, 3));
        List<Text> allTextForTest = textDao.getAllTextForTest(text);
        System.out.println(allTextForTest);
    }

    @Test
    public void testGetUserById(){
        setDataSourceProperty(dataSource,"jdbc:sqlite:src/test/com/me/ssm/user/myblog.db");
        User buyulian = userService.getUserById("buyulian");
        System.out.println(buyulian.getName());
    }

    private void setDataSourceProperty(DataSource dataSource,String url){
        try {
            Field urlField = dataSource.getClass().getDeclaredField("url");
            urlField.setAccessible(true);
            urlField.set(dataSource,url);

            Field driverClassNameField = dataSource.getClass().getDeclaredField("driverClassName");
            driverClassNameField.setAccessible(true);
            driverClassNameField.set(dataSource,"org.sqlite.JDBC");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
