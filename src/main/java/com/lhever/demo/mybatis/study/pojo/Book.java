package com.lhever.demo.mybatis.study.pojo;

import org.apache.ibatis.type.Alias;

import java.util.Date;


@Alias("Book")
public class Book implements java.io.Serializable
{

	private static final long serialVersionUID = -8019185784560242092L;
	private int id;
	private String name;
	private Float price;
	private Date createTime;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
