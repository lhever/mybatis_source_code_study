package com.lhever.demo.mybatis.study.test;

import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/10 16:23
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/10 16:23
 * @modify by reason:{方法名}:{原因}
 */
public class XPathTest {

    private static String xml = "" +
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n" +
            "<configuration>\n" +
            "    <settings>\n" +
            "        <!-- 开启mybatis缓存设置,一般都是true -->\n" +
            "        <setting name=\"cacheEnabled\" value=\"true\"/>\n" +
            "        <!--延迟加载的全局开关-->\n" +
            "        <setting name=\"lazyLoadingEnabled\" value=\"false\"/>\n" +
            "        <!--设置超时时间-->\n" +
            "        <setting name=\"defaultStatementTimeout\" value=\"3000\"/>\n" +
            "        <!--本地缓存机制-->\n" +
            "        <setting name=\"localCacheScope\" value=\"STATEMENT\"/>\n" +
            "        <setting name=\"defaultExecutorType\" value=\"SIMPLE\"/>\n" +
            "        <setting name=\"mapUnderscoreToCamelCase\" value=\"true\"/>\n" +
            "        <!--允许JDBC支持自动生成主键,需要驱动的兼容-->\n" +
            "        <setting name=\"useGeneratedKeys\" value=\"true\"/>\n" +
            "        <setting name=\"logImpl\" value=\"SLF4J\"/>\n" +
            "        <setting name=\"logPrefix\" value=\"mapper\"/>\n" +
            "    </settings>\n" +
            "\t<mappers>\n" +
            "\t\t<mapper resource=\"com/mybatis3/mappers/StudentMapper.xml\"  aaa =\"xxx\" />\n" +
            "\t\t<mapper resource=\"com/mybatis3/mappers/TeacherMapper.xml\" />\n" +
            "\t</mappers>\n" +
            "    <plugins>\n" +
            "        <plugin interceptor=\"com.github.pagehelper.PageInterceptor\"/>\n" +
            "    </plugins>\n" +
            "</configuration>\n";

    @Test
    public void test() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        System.out.println(xml);

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setValidating(false);

        DocumentBuilder builder = builderFactory.newDocumentBuilder();
         builder.setEntityResolver(new XMLMapperEntityResolver());
//        InputStream resourceAsStream = new FileInputStream(new File("F:/mybatis.xml"));
        InputStream resourceAsStream = new ByteArrayInputStream(getBytes(xml));

        Document document = builder.parse(resourceAsStream);


        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        String value = (String) xpath.evaluate("/configuration/settings/setting[@name='defaultStatementTimeout']/@value", document, XPathConstants.STRING);

        System.out.println("defaultStatementTimeout=\"" + value + "\"");

        Node node = (Node) xpath.evaluate("/configuration/mappers/mapper[1]", document, XPathConstants.NODE);
        NamedNodeMap attributeNodes = node.getAttributes();
        for (int i = 0; i < attributeNodes.getLength(); i++) {
            Node n = attributeNodes.item(i);
            System.out.println(n.getNodeName() + "=\"" + n.getNodeValue() + "\"");
        }


        System.out.println("XPathParser test !!! \n");
        XPathParser xPathParser = new XPathParser(xml, false, new Properties(), new XMLMapperEntityResolver());
        XNode xNode = xPathParser.evalNode("/configuration/settings/setting[@name='defaultStatementTimeout']");
        System.out.println(xNode.getValueBasedIdentifier());
    }


    public static byte[] getBytes(String src, String charset) {
        try {
            return src.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getBytes(String src) {
        try {
            return src.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
