package com.lhever.demo.mybatis.study.dao;

import com.lhever.demo.mybatis.study.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BookDao
{
	/**
	 * @param book 
	 * @author lihong 2016年6月24日 下午2:12:39
	 * @since v1.0
	 */
	public int addBook(Book book);
	
	/**
	 * 与addBook功能相同，此处是为了测试两种插入数据并获取主键的方法
	 * @param book void
	 * @author lihong 2016年6月24日 下午2:58:33
	 * @since v1.0
	 */
	public int insert(Book book);
	
	/**
	 * @return List<Book>
	 * @author lihong 2016年6月24日 下午2:12:59
	 * @since v1.0
	 */
	public List<Book> getAll();

	public int insertInBatch(@Param("list") List<Book> list);

	public int addAll(@Param("list") List<Book> list);

	public List<Book> getBy(Book book);

}
