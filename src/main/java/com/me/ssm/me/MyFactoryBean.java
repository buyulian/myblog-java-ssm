package com.me.ssm.me;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author liujiacun
 * @date 2019/2/25
 */
public class MyFactoryBean<T> implements FactoryBean<T> {
    private Class<T> mapperInterface;

    @Override
    public T getObject() throws Exception {
        T t = (T)Proxy.newProxyInstance(AppleDao.class.getClassLoader(), new Class<?>[]{AppleDao.class}, new MyInvocationHandler());
        return t;
    }

    @Override
    public Class<T> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
}
