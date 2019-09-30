package com.surveyor.service;

import java.util.List;

import com.surveyor.pojo.Question;
import com.surveyor.pojo.Survey;

public interface QuestionService {
	String one = "one";
	String many = "many";
	String fill = "fill";
	String scale = "scale";
	public int getTotalBySurvey(String surveyId);
	public List<Question> queryBySurvey(String surveyId);
    String add(Question question);
    Question get(String id);
    void update(Question question);

}
