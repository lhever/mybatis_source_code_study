package com.lhever.demo.mybatis.study.test;

import org.junit.Test;

import java.util.*;

/**
 * <p>类说明：</p>
 *
 * @author lihong10 2021/4/22 21:35
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2021/4/22 21:35
 * @modify by reason:{方法名}:{原因}
 */
public class ForeachNodeTest {

    public static final String ITEM_PREFIX = "__frch_";

    private static String itemizeItem(String item, int i) {
        return ITEM_PREFIX + item + "_" + i;
    }

    @Test
    public void test22() {
        foreachReplace("item", "${item.aa}");
        foreachReplace("item", "${itemsss}");
        foreachReplace("item", "${item:aa}");
        foreachReplace("item", "${item,aa}");
        foreachReplace("item", "${item,aa}");
        foreachReplace("item", "${item aa}");
        foreachReplace("item", "${item}");
        foreachReplace("item", "item.aaa");
        foreachReplace("item", "item:aaa");
        foreachReplace("item", "item,aa");
        foreachReplace("item", "item aa");
        foreachReplace("item", "item");
        foreachReplace("item", "itemsss");
    }

    public void foreachReplace(String item, String content) {
        String s = content.replaceFirst("^\\s*" + item + "(?![^.,:\\s])", itemizeItem(item, 11));
        System.out.println(s);
    }


    private static List<String> parseOverrides(String overrides) {
        if (overrides != null) {
            final StringTokenizer parser = new StringTokenizer(overrides, "|", false);
            final List<String> list = new ArrayList<>(parser.countTokens());
            while (parser.hasMoreTokens()) {
                list.add(parser.nextToken().toUpperCase(Locale.ENGLISH));
            }
            return list;
        }
        return Collections.emptyList();
    }


    @Test
    public void testParseOverrides() {
        System.out.println(parseOverrides("aaa|bbb|ccc"));
        System.out.println(parseOverrides("aaa|bbb  |ccc"));
        System.out.println(parseOverrides("ddd"));
    }




}
