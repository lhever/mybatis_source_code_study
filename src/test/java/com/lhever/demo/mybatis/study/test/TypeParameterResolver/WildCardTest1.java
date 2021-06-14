package com.lhever.demo.mybatis.study.test.TypeParameterResolver;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/12 9:46
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/12 9:46
 * @modify by reason:{方法名}:{原因}
 */
public class WildCardTest1 {

    public static class classA {
    }

    public <T> void test(List<? extends classA> a) {
    }

    public static void main(String[] args) throws NoSuchMethodException {

        Method method = WildCardTest1.class.getMethod("test", List.class);
        Type[] upperBounds = null;
        Type[] lowerBounds = null;
        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            System.out.println("-->" + type);
            Type[] actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments();
            for (Type t : actualTypeArgument) {
                WildcardType wildcardType = (WildcardType) t;
                lowerBounds = wildcardType.getLowerBounds();
                upperBounds = wildcardType.getUpperBounds();
                System.out.println("通配符表达式类型是：" + wildcardType);
                if (upperBounds.length != 0) {
                    System.out.println("表达式上边界：" + Arrays.asList(upperBounds));
                }
                if (lowerBounds.length != 0) {
                    System.out.println("表达式下边界：" + Arrays.asList(lowerBounds));
                }
            }
        }
//         //输出结果
//        通配符表达式类型是：? extends com.fcc.test.classA
//        表达式上边界：[class com.fcc.test.classA]

    }


}
