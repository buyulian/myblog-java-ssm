package com.me.ssm.me;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liujiacun
 * @date 2019/2/26
 */
public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行了 sql 语句");
        return "返回的 sql 执行结果";
    }
}
