package com.lhever.demo.mybatis.study.dao;

import com.lhever.demo.mybatis.study.pojo.Card;
import org.apache.ibatis.annotations.Param;

public interface CardDao {

    public Card findCardByStudentId(@Param("studentId") Integer studentId);

    
}
