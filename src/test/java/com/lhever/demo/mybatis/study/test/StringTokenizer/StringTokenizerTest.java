package com.lhever.demo.mybatis.study.test.StringTokenizer;

import org.apache.ibatis.mapping.ResultMapping;
import org.junit.Test;

import java.util.*;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/21 21:57
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/21 21:57
 * @modify by reason:{方法名}:{原因}
 */
public class StringTokenizerTest {


    @Test
    public void test1() {
        String str = "100|66,55:200|567,90:102|43,54";

        StringTokenizer strToke = new StringTokenizer(str, ":,|");// 默认不打印分隔符
// StringTokenizer strToke=new StringTokenizer(str,":,|",true);//打印分隔符
// StringTokenizer strToke=new StringTokenizer(str,":,|",false);//不打印分隔符
        while(strToke.hasMoreTokens()){
            System.out.println(strToke.nextToken());
        }
    }

    @Test
    public void test2() {
        String columnName = "aaa,bbb,ccc";
        StringTokenizer parser = new StringTokenizer(columnName, ",", false);
        while (parser.hasMoreTokens()) {
            String property = parser.nextToken();
            System.out.println(property );
        }
    }


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


    @Test
    public void test4() {
        String columnName = "{skuId=ID}{aaa=bbb}";
        StringTokenizer parser = new StringTokenizer(columnName, "{}=, ", false);
        while (parser.hasMoreTokens()) {
            String property = parser.nextToken();
            String column = parser.nextToken();
            System.out.println(property + "  --- " + column);
        }
    }

    @Test
    public void test5() {
        String columnName = "{skuId=ID}";
        StringTokenizer parser = new StringTokenizer(columnName, "{}=, ", false);
        while (parser.hasMoreTokens()) {
            String property = parser.nextToken();
            String column = parser.nextToken();
            System.out.println(property + "  --- " + column);
        }
    }

    @Test
    public void test6() {
        Set<String> strings = parseMultipleColumnNames("{id,name,age}");
        System.out.println(strings);
    }

    @Test
    public void test7() {
//        <association property="title" column="title={title},content={content}" />
        parseCompositeColumnName("title={title1},content={content1}");
    }

    private void parseCompositeColumnName(String columnName) {
        List<ResultMapping> composites = new ArrayList<>();
        if (columnName != null && (columnName.indexOf('=') > -1 || columnName.indexOf(',') > -1)) {
            StringTokenizer parser = new StringTokenizer(columnName, "{}=, ", false);
            while (parser.hasMoreTokens()) {
                String property = parser.nextToken();
                String column = parser.nextToken();

                System.out.println("property: " + property);
                System.out.println("column: " + column);
            }
        }
    }



    private Set<String> parseMultipleColumnNames(String columnName) {
        Set<String> columns = new HashSet<>();
        if (columnName != null) {
            if (columnName.indexOf(',') > -1) {
                StringTokenizer parser = new StringTokenizer(columnName, "{}, ", false);
                while (parser.hasMoreTokens()) {
                    String column = parser.nextToken();
                    columns.add(column);
                }
            } else {
                columns.add(columnName);
            }
        }
        return columns;
    }

}
