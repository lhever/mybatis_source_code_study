package com.lhever.demo.mybatis.study.common;

import com.lhever.demo.mybatis.study.pojo.Book;
import ognl.MemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OgnlUtils {
    private final static Logger log = LoggerFactory.getLogger(OgnlUtils.class);

    public static final MemberAccess defaultMemberAccess = new DefaultMemberAccess(true);

    /**
     * 使用OGNL表达式给对象赋值
     *
     * @author lihong10 2018/9/29 14:18:00
     * return
     */
    public static void setValue(String expressiong, Object obj, Object value) {
        if (obj == null) {
            return;
        }
        OgnlContext context = new OgnlContext(null, null, defaultMemberAccess);

        try {
            Ognl.setValue(expressiong, context, obj, value);
        } catch (OgnlException e) {
            if (log.isErrorEnabled()) {
                log.error("对象:{}无字符串类型属性: {}", obj.getClass(), expressiong);
            }
        }

    }

    @Test
    public void test() throws OgnlException {
        OgnlContext context = new OgnlContext(null, null, defaultMemberAccess);
        Book book = new Book();
        book.setId(11);

        Object value = Ognl.getValue("id != null && id == 12", context, book);
        System.out.println(value);

        Object value1 = Ognl.getValue("id != null and id == 11", context, book);
        System.out.println(value1);

    }


}
