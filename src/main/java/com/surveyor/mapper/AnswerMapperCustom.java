package com.surveyor.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.surveyor.pojo.Answer;
import com.surveyor.pojo.vo.AnswerVO;
import com.surveyor.utils.MyMapper;

public interface AnswerMapperCustom extends MyMapper<Answer> {
	public List<AnswerVO> queryMyAnsweredSurvey(@Param("userId")String userId);

}