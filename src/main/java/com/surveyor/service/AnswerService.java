package com.surveyor.service;

import java.util.List;

import com.surveyor.pojo.Answer;
import com.surveyor.pojo.Survey;
import com.surveyor.utils.PagedResult;

public interface AnswerService {
    public PagedResult getMySurveys(String userId ,Integer page,Integer pageSize);
    String add(Answer a);
    void delete(String id);
    void update(Answer a);
    Answer get(String id);
	public List<Answer> queryBySurveyAndUser(String surveyId, String userId);

}
