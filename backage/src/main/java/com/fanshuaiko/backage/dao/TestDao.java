package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.Test;
import com.fanshuaiko.backage.entity.VO.TestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestDao {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    int insertTest(TestVO testVO); //保存考试信息到test表

    int insertTestClass(TestVO testVO); //保存参与考试的班级到test_class表

    int insertTestQuestion(@Param("testNo") long testNo, @Param("questionNoList") List<Long> questionNoList,
                           @Param("score") int score, @Param("questionType") String questionType); //保存考试题目信息到test_question 表
}