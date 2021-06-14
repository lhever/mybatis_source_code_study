package com.lhever.demo.mybatis.study.test.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/6/1 17:43
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/6/1 17:43
 * @modify by reason:{方法名}:{原因}
 */
public class ProxyChain {

    private Class<?> targetClass;
    private List<Proxy> proxyList;
    private Object targetObject;
    private Method targetMethod;
    private Object[] methodParams;
    private MethodProxy methodProxy;

    private int index = 0;

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod,
                      Object[] methodParams, MethodProxy methodProxy, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.proxyList = proxyList;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodParams = methodParams;
        this.methodProxy = methodProxy;
    }

    public Object doProxyChain() throws Throwable {

        Object methodResult;

        if (proxyList == null || proxyList.size() == 0) {
            return methodProxy.invokeSuper(targetObject, methodParams);
        }

        if (index < proxyList.size()) {
            methodResult = proxyList.get(index++).doProxy(this);
        } else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }

        return methodResult;
    }


    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public List<Proxy> getProxyList() {
        return proxyList;
    }

    public void setProxyList(List<Proxy> proxyList) {
        this.proxyList = proxyList;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(Object[] methodParams) {
        this.methodParams = methodParams;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public void setMethodProxy(MethodProxy methodProxy) {
        this.methodProxy = methodProxy;
    }
}
