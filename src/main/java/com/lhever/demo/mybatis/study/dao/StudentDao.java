package com.lhever.demo.mybatis.study.dao;

import com.lhever.demo.mybatis.study.pojo.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentDao {

    public Student findStudentById(@Param("id") Integer id);


    public Student findStudentById_(@Param("id") Integer id);
}
