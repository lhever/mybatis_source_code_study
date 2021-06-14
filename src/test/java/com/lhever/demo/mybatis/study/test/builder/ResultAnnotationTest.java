package com.lhever.demo.mybatis.study.test.builder;

import com.sun.corba.se.spi.orbutil.fsm.Guard;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.binding.BoundBlogMapper;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/5/28 22:19
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/5/28 22:19
 * @modify by reason:{方法名}:{原因}
 */
public class ResultAnnotationTest {


    @Test
    public void test() throws NoSuchMethodException {


        Method method = BoundBlogMapper.class.getDeclaredMethod("selectBlogsWithAutorAndPostsEagerly", new Class[]{});
        Result[] annotations = method.getAnnotationsByType(Result.class);

        Arrays.stream(annotations).forEach(
                i -> {

                    System.out.println(i.property());
                    System.out.println(i.column());
                }
        );


    }


}
