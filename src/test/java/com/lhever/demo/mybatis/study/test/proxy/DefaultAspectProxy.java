package com.lhever.demo.mybatis.study.test.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * AOP切面默认代理类
 * @author lihong
 * @since 1.0.0
 */
public abstract class DefaultAspectProxy extends AbstractAspectProxy {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAspectProxy.class);

    @Override
    public void begin() {

    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {

    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {

    }

    @Override
    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {

    }

    @Override
    public void end() {

    }
}