package com.surveyor.mapper;

import java.util.List;

import com.surveyor.pojo.Question;
import com.surveyor.utils.MyMapper;

public interface QuestionMapper extends MyMapper<Question> {
	public Integer getTotalBySurvey(String surveyId);
	public List<Question> queryBySurvey(String surveyId);
	public String getQuestionIdBySurveyAndSequency(String surveyId, Integer sequence);

}