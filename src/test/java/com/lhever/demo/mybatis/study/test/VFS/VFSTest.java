package com.lhever.demo.mybatis.study.test.VFS;

import org.apache.ibatis.io.ResolverUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/12 14:11
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/12 14:11
 * @modify by reason:{方法名}:{原因}
 */
public class VFSTest {


    @Test
    public void testVFS() throws IOException {
        ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<>();

        //查找包com.hikvision.sc.demo.pojo下面的所有类
        resolverUtil.find(new ResolverUtil.IsA(Object.class), "com.hikvision.sc.demo.pojo");

        Set<Class<? extends Class<?>>> classes = resolverUtil.getClasses();
        classes.stream().sorted((a, b) -> a.getName().compareTo(b.getName())).forEach(System.out::println);


    }
}
