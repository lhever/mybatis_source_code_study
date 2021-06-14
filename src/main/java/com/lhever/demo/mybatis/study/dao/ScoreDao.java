package com.lhever.demo.mybatis.study.dao;

import com.lhever.demo.mybatis.study.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreDao {

    public List<Score> selectScores(@Param("studentId") Integer studentId);
}
