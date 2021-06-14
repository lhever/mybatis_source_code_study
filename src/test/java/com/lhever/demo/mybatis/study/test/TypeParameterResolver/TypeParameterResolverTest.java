package com.lhever.demo.mybatis.study.test.TypeParameterResolver;

import org.apache.ibatis.reflection.TypeParameterResolver;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/11 22:00
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/11 22:00
 * @modify by reason:{方法名}:{原因}
 */
public class TypeParameterResolverTest {

    @Test
    public void testReturnType() {
        Method[] declaredMethods = ConstructorTest.class.getDeclaredMethods();


        Arrays.stream(declaredMethods).filter(m -> !m.getName().equals("main")).forEach(
                m -> {
                    System.out.println(m.getName());

                    Type type = TypeParameterResolver.resolveReturnType(m, ConstructorTest.class);


                    System.out.println(type);

                }
        );


    }
}
