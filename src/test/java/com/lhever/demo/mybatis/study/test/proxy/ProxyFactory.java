package com.lhever.demo.mybatis.study.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/6/1 17:44
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/6/1 17:44
 * @modify by reason:{方法名}:{原因}
 */
public class ProxyFactory {
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {

        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return new ProxyChain(targetClass, obj, method, args, proxy, proxyList).doProxyChain();
            }
        });
    }

}


