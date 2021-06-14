package com.lhever.demo.mybatis.study.test.scripting;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/6/4 11:43
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/6/4 11:43
 * @modify by reason:{方法名}:{原因}
 */
public interface TestBookMapper {

    public int addList(@Param("list") List<TestBook> list);

    public List<TestBook> getAll();
}
