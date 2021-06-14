package com.lhever.demo.mybatis.study.test.proxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/6/1 21:07
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/6/1 21:07
 * @modify by reason:{方法名}:{原因}
 */
public class ParamPrintProxy extends DefaultAspectProxy {

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println("调用方法前，传入的参数是：" + Arrays.toString(params));
        super.before(cls, method, params);
    }
}
