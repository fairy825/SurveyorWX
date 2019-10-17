package com.surveyor.service;

import java.util.List;

import com.surveyor.pojo.Question;
import com.surveyor.pojo.Survey;
import com.surveyor.utils.PagedResult;

public interface QuestionService {
	String one = "one";
	String many = "many";
	String fill = "fill";
	String scale = "scale";
	public int getTotalBySurvey(String surveyId);
    public PagedResult queryBySurvey(String surveyId ,Integer page,Integer pageSize);
    public PagedResult queryBySurveyWithDetect(String surveyId ,Integer page,Integer pageSize);
//	public List<Question> queryBySurvey(String surveyId);
    String add(Question question);
    Question get(String id);
    void update(Question question);
    void delete(String id);
    public String getQuestionIdBySurveyAndSequency(String surveyId,Integer sequence);

}
