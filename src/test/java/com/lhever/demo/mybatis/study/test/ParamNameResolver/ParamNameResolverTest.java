package com.lhever.demo.mybatis.study.test.ParamNameResolver;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/11 20:26
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/11 20:26
 * @modify by reason:{方法名}:{原因}
 */
public class ParamNameResolverTest {


    @Test
    public void testParamResolver() {


        Method[] declaredMethods = ParamNameResolverTestObj.class.getDeclaredMethods();

        Arrays.stream(declaredMethods).sorted((a, b) -> a.getName().compareTo(b.getName())).forEach( m -> {

            System.out.println("===================================");

            System.out.println("method name: " + m.getName());

            ParamNameResolverTestObj.ParamNameResolver resolver = new ParamNameResolverTestObj.ParamNameResolver(false, m);

            Map<Integer, String> names = resolver.getNames();
            names.forEach((k, v) -> {
                System.out.println("" + k + "  :  " + v);
            });


            if (m.getName().equals("aMethod")) {
                Object namedParams = resolver.getNamedParams(new Object[]{11, 22});
                System.out.println(namedParams);

            } else if (m.getName().equals("bMethod")) {
                Object namedParams = resolver.getNamedParams(new Object[]{11, 22});
                System.out.println(namedParams);
            } else if (m.getName().equals("cMethod")) {
                Object namedParams = resolver.getNamedParams(new Object[]{11, new RowBounds(), null, "lalala", true});
                System.out.println(namedParams);
            }

            System.out.println();
        });
    }
}
