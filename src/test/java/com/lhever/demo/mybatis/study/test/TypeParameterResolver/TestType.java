package com.lhever.demo.mybatis.study.test.TypeParameterResolver;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/5/25 23:50
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/5/25 23:50
 * @modify by reason:{方法名}:{原因}
 */

import org.apache.ibatis.reflection.TypeParameterResolver;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

class classA<K, V> {
    protected HashMap<K, V> map;

    public HashMap<K, V> getMap() {
        return map;
    }

    public void setMap(HashMap<K, V> map) {
        this.map = map;
    }
}

class SubClassA<T> extends classA<T, T> {
}

class TestType {
    SubClassA<Long> aa = new SubClassA<>();

    public static void main(String[] args) throws Exception {
        Field field = classA.class.getDeclaredField("map");
        System.out.println(field.getGenericType());
        System.out.println(field.getGenericType() instanceof ParameterizedType);
        System.out.println("-----------------------------");

        // 解析SubA<Long> ( ParameterizedType 类型）中的map 字段，注意： ParameterizedTypeimpl 是
        // 在sun.reflect.generics.reflectObjects包下的ParameterizedType 接口实现
        Type type = TypeParameterResolver.resolveFieldType(field,
                ParameterizedTypeImpl.make(SubClassA.class, new Type[]{Long.class}, TestType.class));
        System.out.println(type.getClass());
        System.out.println(type);
        // 输出：class org.apache.ibatis.reflection.TypeParameterResolver$ParameterizedTypeImpl
        // 注意， TypeParameterResolver$ParameterizedTypeImpl是ParameterizedType 接口的实现

        // ~~~
        // 也可以使用下面的方式生成上述ParameterizedType 对象，
        // 并调用TypeParameterResolver.resolveFieldType （）方法：
        // Type aaa = TestType.class.getDeclaredField("aa").getGenericType();
        // // com.gyoomi.parameterresolver.SubClassA<java.lang.Long>
        // Type type2 = TypeParameterResolver.resolveFieldType(field, TestType.class.getDeclaredField("aa").getGenericType());
        // System.out.println(type2);
        // ~~~

        ParameterizedType pType = (ParameterizedType) type;
        System.out.println(pType);
        System.out.println("----------------------------");
        System.out.println("rawType：" + pType.getRawType());
        System.out.println("ownerType：" + pType.getOwnerType());
        Type[] actualTypeArguments = pType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument);
        }
    }
}


