package com.lhever.demo.mybatis.study.test;

import org.junit.Test;

import java.util.StringTokenizer;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/1/13 18:00
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/1/13 18:00
 * @modify by reason:{方法名}:{原因}
 */
public class StringTokenizerTest {

    @Test
    public void test3() {
        String columnName = "{skuId=ID}{aaa=bbb}";
        StringTokenizer parser = new StringTokenizer(columnName, "{}, ", false);
        while (parser.hasMoreTokens()) {
            String property = parser.nextToken();
            String column = parser.nextToken();
            System.out.println(property + "  --- " + column);
        }
    }
}
