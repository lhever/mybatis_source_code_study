package com.lhever.demo.mybatis.study.test;

import org.apache.ibatis.type.FloatTypeHandler;
import org.apache.ibatis.type.LongTypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.ibatis.type.TypeReference;
import org.junit.Test;

import java.lang.reflect.Type;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/13 11:01
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/13 11:01
 * @modify by reason:{方法名}:{原因}
 */
public class HandlerTest {


    @Test
    public void test() {


        TypeHandlerRegistry registry = new TypeHandlerRegistry();
        registry.register(new LongTypeHandler());

        ///////////////////////////////////////////////////


        FloatTypeHandler fh = new FloatTypeHandler();
        Type rawType = fh.getRawType();

        System.out.println(rawType.getClass());
        System.out.println(rawType.getTypeName());


        TypeRefObj obj = new TypeRefObj();
        System.out.println("TypeRefObj raw type is: " + obj.getRawType());


//        Enum.valueOf("")

    }


    public static  class TypeRefObj<HandlerTest> extends TypeReference<HandlerTest> {}
}






















