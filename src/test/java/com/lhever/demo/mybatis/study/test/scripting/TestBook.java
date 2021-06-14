package com.lhever.demo.mybatis.study.test.scripting;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/6/4 11:39
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/6/4 11:39
 * @modify by reason:{方法名}:{原因}
 */
@Data
public class TestBook {
    private Integer id;
    private String name;
    private String detail;
    private Double price;
    private Date createTime;

    public TestBook(Integer id, String name, String detail, Double price, Date createTime) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.createTime = createTime;
    }

    public TestBook(String name, String detail, Double price, Date createTime) {
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.createTime = createTime;
    }
}
