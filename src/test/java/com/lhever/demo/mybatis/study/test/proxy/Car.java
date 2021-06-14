package com.lhever.demo.mybatis.study.test.proxy;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/6/1 15:31
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/6/1 15:31
 * @modify by reason:{方法名}:{原因}
 */
public class Car implements Vehicle {
    @Override
    public void run(String name) {
        System.out.println(name + "启动了！");
    }
}
