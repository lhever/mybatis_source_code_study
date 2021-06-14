package com.lhever.demo.mybatis.study.test;

import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.junit.Test;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/1/20 20:24
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/1/20 20:24
 * @modify by reason:{方法名}:{原因}
 */
public class PropertyTokenizerTest {



    @Test
    public void test() {
        PropertyTokenizer tokenizer = new PropertyTokenizer("database[1].url.host");
        System.out.println(tokenizer.getIndex());
        System.out.println(tokenizer.getName());
        System.out.println(tokenizer.getIndexedName());
        System.out.println(tokenizer.getChildren());
    }
}
