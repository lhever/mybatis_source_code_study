package com.lhever.demo.mybatis.study.test;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/13 11:39
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/13 11:39
 * @modify by reason:{方法名}:{原因}
 */
public interface MybatisConfigXML {
    public static final String config_xml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n" +
            "<configuration>\n" +
            "\t<properties resource=\"/com/lhever/demo/mybatis/study/test/jdbc.properties\">\n" +
            "\t\t<property name=\"username\" value=\"postgres\" />\n" +
            "\t\t<property name=\"password\" value=\"postgres\" />\n" +
            "\t</properties>\n" +
            "\t<settings>\n" +
            "\t\t<setting name=\"localCacheScope\" value=\"STATEMENT\"/>\n" +
            "\t\t<setting name=\"cacheEnabled\" value=\"false\" />\n" +
            "\t\t<setting name=\"lazyLoadingEnabled\" value=\"true\" />\n" +
            "\t\t<setting name=\"multipleResultSetsEnabled\" value=\"true\" />\n" +
            "\t\t<setting name=\"useColumnLabel\" value=\"true\" />\n" +
            "\t\t<setting name=\"useGeneratedKeys\" value=\"false\" />\n" +
            "\t\t<setting name=\"defaultExecutorType\" value=\"REUSE\" />\n" +
            "\t\t<setting name=\"defaultStatementTimeout\" value=\"25000\" />\n" +
            "\t</settings>\n" +
            "\t<typeAliases>\n" +
            "\t\t<typeAlias alias=\"Student\" type=\"com.mybatis3.domain.Student\" />\n" +
            "\t\t<typeAlias alias=\"Teacher\" type=\"com.mybatis3.domain.Teacher\" />\n" +
            "\t</typeAliases>\n" +
            "\t<typeHandlers>\n" +
            "\t\t<typeHandler handler=\"com.mybatis3.typehandlers.PhoneTypeHandler\" />\n" +
            "\t</typeHandlers>\n" +
            "\t<environments default=\"development\">\n" +
            "\t\t<environment id=\"development\">\n" +
            "\t\t\t<transactionManager type=\"JDBC\" />\n" +
            "\t\t\t<dataSource type=\"POOLED\">\n" +
            "\t\t\t\t<property name=\"driver\" value=\"${driver}\" />\n" +
            "\t\t\t\t<property name=\"url\" value=\"${url}\" />\n" +
            "\t\t\t\t<property name=\"username\" value=\"${username}\" />\n" +
            "\t\t\t\t<property name=\"password\" value=\"${password}\" />\n" +
            "\t\t\t</dataSource>\n" +
            "\t\t</environment>\n" +
            "\t</environments>\n" +
            "\t<mappers>\n" +
            "\t\t<mapper resource=\"com/mybatis3/mappers/StudentMapper.xml\" />\n" +
            "\t\t<mapper resource=\"com/mybatis3/mappers/TeacherMapper.xml\" />\n" +
            "\t</mappers>\n" +
            "</configuration>";
}
