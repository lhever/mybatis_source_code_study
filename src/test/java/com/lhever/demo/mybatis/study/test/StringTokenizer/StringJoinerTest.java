package com.lhever.demo.mybatis.study.test.StringTokenizer;

import org.junit.Test;

import java.util.StringJoiner;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/4/13 12:21
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/4/13 12:21
 * @modify by reason:{方法名}:{原因}
 */
public class StringJoinerTest {

    @Test
    public void test() {
        StringJoiner joiner = new StringJoiner(":");
        joiner.add("aaa");
        joiner.add("bbb");
        System.out.println(joiner.toString());


        StringJoiner joiner1 = new StringJoiner(":", "(", ")");
        joiner1.add("aaa");
        joiner1.add("bbb");
        joiner1.add(null);
        System.out.println(joiner1.toString());
    }
}
