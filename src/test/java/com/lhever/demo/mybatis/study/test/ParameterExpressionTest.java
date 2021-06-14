package com.lhever.demo.mybatis.study.test;

import org.apache.ibatis.builder.ParameterExpression;
import org.junit.Test;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/21 19:43
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/21 19:43
 * @modify by reason:{方法名}:{原因}
 */
public class ParameterExpressionTest {


    @Test
    public void testParameter() {
//        #{item.price}
        ParameterExpression expression = new ParameterExpression("item.name");
        System.out.println(expression);

    }


    @Test
    public void testParameter1() {
//        #{item.price, jdbcType=TINYINT ,javaType=short}
        String exp = "item.price, jdbcType=TINYINT ,  javaType=short";

        ParameterExpression expression = new ParameterExpression(exp);
        System.out.println(expression);

    }


}
