package com.lhever.demo.mybatis.study.test.MetaClass;

import com.lhever.demo.mybatis.study.pojo.Book;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.junit.Test;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/23 18:39
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/23 18:39
 * @modify by reason:{方法名}:{原因}
 */
public class MetaClassTest {

    @Test
    public void testMetaClass() {

        MetaClass metaClass = forMetaClass(Book.class);
        System.out.println(metaClass.getGetterType("name"));
        System.out.println(metaClass.getSetterType("price"));

    }


    public static <T> MetaClass forMetaClass(Class<T> classZ) {
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaClass metaResultType = MetaClass.forClass(classZ, reflectorFactory);

        return metaResultType;

    }
}
