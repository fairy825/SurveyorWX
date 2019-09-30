package com.surveyor.service.impl;
import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.surveyor.mapper.QuestionMapper;
import com.surveyor.mapper.SurveyMapper;
import com.surveyor.pojo.Question;
import com.surveyor.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired 
	private SurveyMapper surveyMapper;
	@Autowired 
	private QuestionMapper questionMapper;
	
	@Autowired
	private Sid sid;
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public int getTotalBySurvey(String surveyId) {
		return questionMapper.getTotalBySurvey(surveyId);
	}
	
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public String add(Question question) {
		String id = sid.nextShort();
		question.setId(id);
		questionMapper.insert(question);
		return id;
	}
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public List<Question> queryBySurvey(String surveyId) {
		return questionMapper.queryBySurvey(surveyId);
	}
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public Question get(String id) {
		return questionMapper.selectByPrimaryKey(id);
	}
	
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public void update(Question question) {
		questionMapper.updateByPrimaryKey(question);
	}
	

}
