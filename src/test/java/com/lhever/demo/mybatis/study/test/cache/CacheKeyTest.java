package com.lhever.demo.mybatis.study.test.cache;

import org.apache.ibatis.cache.CacheKey;
import org.junit.Test;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/4/13 14:47
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/4/13 14:47
 * @modify by reason:{方法名}:{原因}
 */
public class CacheKeyTest {

    @Test
    public void testCacheKey() {
        CacheKey cacheKey = new CacheKey();
        cacheKey.update(1586760582886L);
        cacheKey.update("aaa");
        cacheKey.update(123);
        cacheKey.update(12.34f);
        System.out.println(cacheKey);


        CacheKey cacheKey1 = new CacheKey();
        cacheKey1.update(1586760582886L);
        cacheKey1.update("aaa");
        cacheKey1.update(123);
        cacheKey1.update(12.34f);
        System.out.println(cacheKey1);

        boolean equals = cacheKey.equals(cacheKey1);
        System.out.println(equals);
    }


}
