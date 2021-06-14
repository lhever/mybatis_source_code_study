package com.lhever.demo.mybatis.study.test.proxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/6/1 21:09
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/6/1 21:09
 * @modify by reason:{方法名}:{原因}
 */
public class TimeStatisticProxy extends DefaultAspectProxy {
    ThreadLocal<Long> timeLocal = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        long beginTime = System.currentTimeMillis();
        timeLocal.set(beginTime);
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        long endTime = System.currentTimeMillis();
        System.out.println("方法：" + method.getName() + "的运行时间是:"  + (endTime - timeLocal.get()));
    }


    public static void main(String[] args) {

        Car carProxy = ProxyFactory.createProxy(Car.class, Arrays.asList(new TimeStatisticProxy(), new ParamPrintProxy()));

        carProxy.run("云E88688");
    }
}
