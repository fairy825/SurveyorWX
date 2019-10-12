package com.surveyor.mapper;

import com.surveyor.pojo.Answer;
import java.util.List;
import com.surveyor.utils.MyMapper;

public interface AnswerMapper extends MyMapper<Answer> {
	public List<Answer> selectBySurveyIdAndUserId(String surveyId, String userId);
}