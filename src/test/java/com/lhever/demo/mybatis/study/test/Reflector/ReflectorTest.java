package com.lhever.demo.mybatis.study.test.Reflector;

import org.apache.ibatis.reflection.Reflector;
import org.junit.Test;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/12 10:47
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/12 10:47
 * @modify by reason:{方法名}:{原因}
 */
public class ReflectorTest {

    @Test
    public void testReflector() {
        Reflector reflector = new Reflector(ReflectorBean.class);

    }
}
