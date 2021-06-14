package com.lhever.demo.mybatis.study.test.TypeParameterResolver;

/**
 Type接口没有任何方法，只有一个接口声明
 //Type接口是java编程语言中所有类型的公共高级接口，它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
 public interface Type
 1
 2
 所有已知的实现类有GenericArrayType, ParameterizedType, TypeVariable, WildcardType；

 TypeVariable 是各种类型变量的公共高级接口
 //类型变量在反射方法首次需要时创建
 public interface TypeVariable<D extends GenericDeclaration> extends Type
 1
 2
 类型变量中有三个方法：
 getBounds：返回表示此类型变量上边界的 Type 对象的数组；
 getGenericDeclaration：返回 GenericDeclaration 对象，该对象表示声明此类型变量的一般声明；
 getName：返回此类型变量的名称，它出现在源代码中。
 ————————————————
 版权声明：本文为CSDN博主「随风yy」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 原文链接：https://blog.csdn.net/yaomingyang/article/details/81201817

 */

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ConstructorTest<T extends CharSequence, D extends  CharSequence> {


    public ConstructorTest<String, String> getParameterizedType() {
        return null;
    }

    public ConstructorTest<T, D> getTypeVar() {
        return null;
    }




    /*
    Type接口没有任何方法，只有一个接口声明
    //Type接口是java编程语言中所有类型的公共高级接口，它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
    public interface Type
1
        2
    所有已知的实现类有GenericArrayType, ParameterizedType, TypeVariable, WildcardType；

    TypeVariable 是各种类型变量的公共高级接口
    //类型变量在反射方法首次需要时创建
    public interface TypeVariable<D extends GenericDeclaration> extends Type
1
        2
    类型变量中有三个方法：
    getBounds：返回表示此类型变量上边界的 Type 对象的数组；
    getGenericDeclaration：返回 GenericDeclaration 对象，该对象表示声明此类型变量的一般声明；
    getName：返回此类型变量的名称，它出现在源代码中。
            ————————————————
    版权声明：本文为CSDN博主「随风yy」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/yaomingyang/article/details/81201817
    */

    public static void main(String[] args) {
        TypeVariable<Class<ConstructorTest>>[] t = ConstructorTest.class.getTypeParameters();
        System.out.println("length: " + t.length);
        for(TypeVariable<Class<ConstructorTest>> m : t) {
            /**
             * 获得类型变量在声明的时候的名称，此例中为T
             */
            System.out.println(m.getName());
            /**
             * 获得类型变量的上边界，若无显式的定义（extends）,默认为Object;类型变量的上边界可能不止一个，
             * 因为可以用&符号限定多个（这其中有且只能有一个为类或抽象类，且必须放在extends后的第一个，
             * 即若有多个上边界，则第一个&后必须为接口）
             *
             */
            Type[] bounds = m.getBounds();
            System.out.println("bounds start");
            for(Type t1 : bounds) {
                System.out.println(t1);
            }
            System.out.println("bounds end");
            /**
             * 获得声明这个类型变量的类型及名称
             * 类中：class reflect.ConstructorTest
             */
            System.out.println(m.getGenericDeclaration());
        }
    }
}