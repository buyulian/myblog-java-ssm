package com.me.ssm.service;

import com.me.ssm.me.AppleDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liujiacun
 * @date 2019/2/25
 */
@Component
public class AppleServiceImpl implements AppleService {

    @Resource
    private AppleDao appleDao;

    @Override
    public String getName() {
        return appleDao.getName();
    }
}
