package com.surveyor.service;

import com.surveyor.pojo.Answer;
import com.surveyor.pojo.vo.StatisticsVO;
import com.surveyor.utils.PagedResult;

import java.util.List;

public interface StatisticsService {

    StatisticsVO getResult(String surveyId);
//    List<Answer> getAnswerContent(String questionId);

}
