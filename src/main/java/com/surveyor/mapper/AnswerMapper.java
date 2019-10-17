package com.surveyor.mapper;

import com.surveyor.pojo.Answer;

import java.util.List;
import java.util.Map;

import com.surveyor.utils.MyMapper;
import org.springframework.data.repository.query.Param;

public interface AnswerMapper extends MyMapper<Answer> {
	public List<Answer> selectBySurveyIdAndUserId(String surveyId, String userId);
	List<Answer> selectByQuestionId(@Param("questionId")String questionId,@Param("type")String type);
	List<Map<String,Object>> getOne(String questionId);
}
