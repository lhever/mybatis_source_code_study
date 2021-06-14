package com.lhever.demo.mybatis.study.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/13 11:38
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/13 11:38
 * @modify by reason:{方法名}:{原因}
 */
public class CfgBuilderTest {


    @Test
    public void test() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();


        SqlSessionFactory build = builder.build(new ByteArrayInputStream(XPathTest.getBytes(MybatisConfigXML.config_xml)));


    }


}
