package com.surveyor.service;

import java.util.List;

import com.surveyor.pojo.Survey;
import com.surveyor.utils.PagedResult;

public interface AnswerService {
    public PagedResult getMySurveys(String userId ,Integer page,Integer pageSize);
//    String add(Survey s);
//    void delete(int id);
//    void update(Survey s);
//    Survey get(String id);

}
